package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.workers.AsyncExporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Kateřina Vácová
 */
public class ExportDialog extends DialogBase {
    private File rides = null;
    private File vehicles = null;
    private File passengers = null;
    private File ridesCategories = null;
    private File passengersCategories = null;

    public ExportDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton exportButton = new JButton("Exportovat");
        onExportButton(exportButton);
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(exportButton);
        bottom.add(cancel);
        bottom.add(exportButton);
    }

    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIUtilities.formatWhiteTextBrownDialog(center);

        JButton exportRidesButton = new JButton("Vyberte složku pro export");
        JTextArea exportRidesPath = new JTextArea();
        exportRidesPath.setEditable(false);
        JScrollPane ridesPathScroll = new JScrollPane(exportRidesPath);
        exportRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                ridesFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = new File(ridesFileChooser.getSelectedFile() + "/rides.csv");
                    vehicles = new File(ridesFileChooser.getSelectedFile() + "/vehicles.csv");
                    passengers = new File(ridesFileChooser.getSelectedFile() + "/passengers.csv");
                    ridesCategories = new File(ridesFileChooser.getSelectedFile() + "/ridesCategories.csv");
                    passengersCategories = new File(ridesFileChooser.getSelectedFile() + "/passengersCategories.csv");
                }
                exportRidesPath.setText(String.valueOf(ridesFileChooser.getSelectedFile()));
            }
        });
        exportRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportRidesButton);
        center.add(ridesPathScroll);

        this.add(center);
        setSize(300, 160);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onExportButton(JButton exportButton) {
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passengers == null || vehicles == null || rides == null ||
                        passengersCategories == null || ridesCategories == null) {
                    //throw new ValidationException("Some files are missing");
                    new ErrorDialog(MainWindow.getFrame(), new ValidationException("Some files are missing"));
                } else {
                    AsyncExporter exporter = new AsyncExporter();
                    exporter.exportData(rides, vehicles, passengers, passengersCategories, ridesCategories);
                    dispose();
                }
            }
        });
    }
}

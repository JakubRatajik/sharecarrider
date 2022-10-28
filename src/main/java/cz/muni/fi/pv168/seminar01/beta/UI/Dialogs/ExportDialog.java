package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Kateřina Vácová
 */
public class ExportDialog extends DialogBase {
    private File rides;
    private File vehicles;
    private File passengers;

    public ExportDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton exportButton = new JButton("Exportovat");
        onExportButton(exportButton);
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(exportButton);
        bottom.add(cancel);
        bottom.add(exportButton);
    }

    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIConstants.formatWhiteTextBrownDialog(center);

        JButton exportRidesButton = new JButton("Kam exportovat jízdy");
        exportRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = ridesFileChooser.getSelectedFile();
                }
            }
        });
        exportRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportRidesButton);

        JButton exportVehiclesButton = new JButton("Kam exportovat vozidla");
        exportVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser vehiclesFileChooser = new JFileChooser();
                int dialogResult = vehiclesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    vehicles = vehiclesFileChooser.getSelectedFile();
                }
            }
        });
        exportVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportVehiclesButton);

        JButton exportPassengersButton = new JButton("Kam exportovat cestující");
        exportPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser passengersFileChooser = new JFileChooser();
                int dialogResult = passengersFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    passengers = passengersFileChooser.getSelectedFile();
                }
            }
        });
        exportPassengersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportPassengersButton);

        this.add(center);
        setSize(300, 160);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onExportButton(JButton exportButton) {
        //todo implement funkcionality
    }
}

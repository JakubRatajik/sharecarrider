package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.ImporterBase;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Kateřina Vácová
 */
public class ImportDialog extends DialogBase {
    private File rides;
    private File vehicles;
    private File passengers;

    public ImportDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton importButton = new JButton("Importovat");
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(importButton);
        bottom.add(cancel);
        bottom.add(importButton);
        try {
            onImportButton(importButton);
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), e);
        }
    }

    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        UIUtilities.formatWhiteTextBrownDialog(center);

        JButton importRidesButton = new JButton("Načíst jízdy");
        JTextArea importRidesPath = new JTextArea();
        importRidesPath.setEditable(false);
        JScrollPane ridesPathScroll = new JScrollPane(importRidesPath);
        importRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = ridesFileChooser.getSelectedFile();
                }
                importRidesPath.setText(String.valueOf(rides));
            }
        });
        importRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importRidesButton);
        center.add(ridesPathScroll);

        JButton importVehiclesButton = new JButton("Načíst vozidla");
        JTextArea importVehiclesPath = new JTextArea();
        importVehiclesPath.setEditable(false);
        JScrollPane vehiclesPathScroll = new JScrollPane(importVehiclesPath);
        importVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser vehiclesFileChooser = new JFileChooser();
                int dialogResult = vehiclesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    vehicles = vehiclesFileChooser.getSelectedFile();
                }
                importVehiclesPath.setText(String.valueOf(vehicles));
            }
        });
        importVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importVehiclesButton);
        center.add(vehiclesPathScroll);

        JButton importPassengersButton = new JButton("Načíst cestující");
        JTextArea importPassengersPath = new JTextArea();
        importPassengersPath.setEditable(false);
        JScrollPane passengersPathScroll = new JScrollPane(importPassengersPath);
        importPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser passengersFileChooser = new JFileChooser();
                int dialogResult = passengersFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    passengers = passengersFileChooser.getSelectedFile();
                }
                importPassengersPath.setText(String.valueOf(passengers));
            }
        });
        importPassengersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importPassengersButton);
        center.add(passengersPathScroll);

        this.add(center);
        setSize(300, 250);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onImportButton(JButton importAction) {
        importAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (passengers == null || vehicles == null || rides == null) {
                    //throw new ValidationException("Some files are missing");
                    new ErrorDialog(MainWindow.getFrame(), new ValidationException("Some files are missing"));
                } else {
                    ImporterBase.loadData(rides, vehicles, passengers);
                    dispose();
                }
            }
        });

    }
}

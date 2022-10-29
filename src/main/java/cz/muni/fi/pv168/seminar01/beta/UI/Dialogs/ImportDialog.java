package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Data.Manipulation.ImporterBase;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

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
        onImportButton(importButton);
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(importButton);
        bottom.add(cancel);
        bottom.add(importButton);
    }

    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIConstants.formatWhiteTextBrownDialog(center);

        JButton importRidesButton = new JButton("Načíst jízdy");
        importRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = ridesFileChooser.getSelectedFile();
                }
            }
        });
        importRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importRidesButton);

        JButton importVehiclesButton = new JButton("Načíst vozidla");
        importVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser vehiclesFileChooser = new JFileChooser();
                int dialogResult = vehiclesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    vehicles = vehiclesFileChooser.getSelectedFile();
                }
            }
        });
        importVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importVehiclesButton);

        JButton importPassengersButton = new JButton("Načíst cestující");
        importPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser passengersFileChooser = new JFileChooser();
                int dialogResult = passengersFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    passengers = passengersFileChooser.getSelectedFile();
                }
            }
        });
        importPassengersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(importPassengersButton);

        this.add(center);
        setSize(300, 160);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onImportButton(JButton importAction) {
        importAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (passengers == null || vehicles == null || rides == null) {
                    throw new NullPointerException("Some files are missing");
                }
                ImporterBase.loadData(rides, vehicles, passengers);
                dispose();
            }
        });

    }
}

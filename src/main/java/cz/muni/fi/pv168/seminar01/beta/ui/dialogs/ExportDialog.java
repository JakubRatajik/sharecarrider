package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.ExportPassengers;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.ExportRides;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.ExportVehicles;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Kateřina Vácová
 */
public class ExportDialog extends DialogBase {
    private File rides = null;
    private File vehicles = null;
    private File passengers = null;

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

        JButton exportRidesButton = new JButton("Kam exportovat jízdy");
        JTextArea exportRidesPath = new JTextArea();
        exportRidesPath.setEditable(false);
        JScrollPane ridesPathScroll = new JScrollPane(exportRidesPath);
        exportRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = ridesFileChooser.getSelectedFile();
                }
                exportRidesPath.setText(String.valueOf(rides));
            }
        });
        exportRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportRidesButton);
        center.add(ridesPathScroll);

        JButton exportVehiclesButton = new JButton("Kam exportovat vozidla");
        JTextArea exportVehiclesPath = new JTextArea();
        exportVehiclesPath.setEditable(false);
        JScrollPane vehiclesPathScroll = new JScrollPane(exportVehiclesPath);
        exportVehiclesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser vehiclesFileChooser = new JFileChooser();
                int dialogResult = vehiclesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    vehicles = vehiclesFileChooser.getSelectedFile();
                }
                exportVehiclesPath.setText(String.valueOf(vehicles));
            }
        });
        exportVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportVehiclesButton);
        center.add(vehiclesPathScroll);

        JButton exportPassengersButton = new JButton("Kam exportovat cestující");
        JTextArea exportPassengersPath = new JTextArea();
        exportPassengersPath.setEditable(false);
        JScrollPane passengersPathScroll = new JScrollPane(exportPassengersPath);
        exportPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser passengersFileChooser = new JFileChooser();
                int dialogResult = passengersFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    passengers = passengersFileChooser.getSelectedFile();
                }
                exportPassengersPath.setText(String.valueOf(passengers));
            }
        });
        exportPassengersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportPassengersButton);
        center.add(passengersPathScroll);

        this.add(center);
        setSize(300, 250);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onExportButton(JButton exportButton) {
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rides != null) {
                    ExportRides exportRides = new ExportRides();
                    exportRides.export((List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData(),
                            rides.getAbsolutePath());
                }
                if (vehicles != null) {
                    ExportVehicles exportVehicles = new ExportVehicles();
                    exportVehicles.export((List<Vehicle>) Shortcut.getTableModel(TableCategory.VEHICLES).getData(),
                            vehicles.getAbsolutePath());
                }
                if (passengers != null) {
                    ExportPassengers exportPassengers = new ExportPassengers();
                    exportPassengers.export((List<Passenger>) Shortcut.getTableModel(TableCategory.PASSENGERS).getData(),
                            passengers.getAbsolutePath());
                }
                dispose();
            }
        });
    }
}

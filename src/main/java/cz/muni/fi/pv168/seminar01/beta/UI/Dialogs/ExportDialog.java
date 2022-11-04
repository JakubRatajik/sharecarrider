package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Data.Manipulation.ExportPassengers;
import cz.muni.fi.pv168.seminar01.beta.Data.Manipulation.ExportRides;
import cz.muni.fi.pv168.seminar01.beta.Data.Manipulation.ExportVehicles;
import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.Shortcut;

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

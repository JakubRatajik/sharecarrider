package cz.muni.fi.pv168.seminar01.beta.ui.utils;

import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.DeleteDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterPassengersDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterRidesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterVehiclesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.TemporaryDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Macecek
 * Util class that load ActionListeners to Dialogs, Tabs, ...
 */
public final class ActionListenerProvider {
    /**
     * creates list of ActionListeners needed for specific tab
     *
     * @param category represents type of tab
     * @return list loaded with four ActionListeners, null if category == STATISTICS
     */
    public static List<ActionListener> getAddALs(TableCategory category) {
        return switch (category) {
            case RIDES -> getALsForRides();
            case VEHICLES -> getALsForVehicles();
            case PASSENGERS -> getALsForPassengers();
        };

    }

    /**
     * makes ActionListeners for rides
     *
     * @return list of rides ActionListeners
     */
    public static List<ActionListener> getALsForRides() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditRideDialog(MainWindow.getFrame(), "Přidat jízdu");
            }
        };

        ActionListener sort = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Řazení");
            }
        };

        ActionListener filter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new FilterRidesDialog(MainWindow.getFrame(), "Filtr");
            }
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.RIDES);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat jízdu/y",
                        TableCategory.RIDES, Shortcut.getTable(TableCategory.RIDES).getSelectedRows());
            }
        };

        List<ActionListener> rides = new ArrayList<>();
        rides.add(plus);
        rides.add(sort);
        rides.add(filter);
        rides.add(select);
        rides.add(delete);
        return rides;
    }

    /**
     * makes ActionListeners for vehicles
     *
     * @return list of vehicles ActionListeners
     */
    public static List<ActionListener> getALsForVehicles() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditVehicleDialog(MainWindow.getFrame(), "Přidat vozidlo");
            }
        };

        ActionListener sort = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Řazení");
            }
        };

        ActionListener filter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new FilterVehiclesDialog(MainWindow.getFrame(), "Filtr");
            }
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.VEHICLES);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat vozidlo/a",
                        TableCategory.VEHICLES, Shortcut.getTable(TableCategory.VEHICLES).getSelectedRows());
            }
        };
        List<ActionListener> vehicles = new ArrayList<>();
        vehicles.add(plus);
        vehicles.add(sort);
        vehicles.add(filter);
        vehicles.add(select);
        vehicles.add(delete);
        return vehicles;
    }

    /**
     * makes ActionListeners for passengers
     *
     * @return list of passengers ActionListeners
     */
    public static List<ActionListener> getALsForPassengers() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditPassengerDialog(MainWindow.getFrame(), "Přidat cestujícího");
            }
        };

        ActionListener sort = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Řazení");
            }
        };

        ActionListener filter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new FilterPassengersDialog(MainWindow.getFrame(), "Filtr");
            }
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.PASSENGERS);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener delete = e -> new DeleteDialog(MainWindow.getFrame(), "Smazat cestující/ho",
                TableCategory.PASSENGERS, Shortcut.getTable(TableCategory.PASSENGERS).getSelectedRows());
        List<ActionListener> passengers = new ArrayList<>();
        passengers.add(plus);
        passengers.add(sort);
        passengers.add(filter);
        passengers.add(select);
        passengers.add(delete);
        return passengers;
    }

    public static ActionListener deleteRow(TableCategory category, int[] rows, DeleteDialog dialog) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int firstRow = rows[0];
                for (int i = 0; i < rows.length; i++) {
                    Shortcut.getTableModel(category).deleteRow(firstRow);
                }
                ShareCarRiderTable table = Shortcut.getTable(category);
                table.clearSelection();
                dialog.dispose();
            }

        };
    }

}

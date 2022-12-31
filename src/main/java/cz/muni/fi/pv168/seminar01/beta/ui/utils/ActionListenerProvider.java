package cz.muni.fi.pv168.seminar01.beta.ui.utils;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.DeleteDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterPassengersDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterRidesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FilterVehiclesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.SortPassengersDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.SortRidesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.SortVehiclesDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.TemporaryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
            case PASSENGER_CATEGORY -> getALsForPassengerCategories();
            case RIDE_CATEGORY -> getALsForRideCategories();
        };

    }

    private static List<ActionListener> getALsForPassengerCategories() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditPassengerCategoryDialog(MainWindow.getFrame(), "Vytvořit kategorii cestujících");
            }
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.PASSENGER_CATEGORY);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                        TableCategory.PASSENGER_CATEGORY, Shortcut.getTable(TableCategory.PASSENGER_CATEGORY).getSelectedRows());
            }
        };

        List<ActionListener> categories = new ArrayList<>();
        categories.add(plus);
        categories.add(null);
        categories.add(null);
        categories.add(select);
        categories.add(delete);
        categories.add(null);
        return categories;
    }


    private static List<ActionListener> getALsForRideCategories() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditRideCategoryDialog(MainWindow.getFrame(), "Přidat kategorii jízd");
            }
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.RIDE_CATEGORY);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                        TableCategory.RIDE_CATEGORY, Shortcut.getTable(TableCategory.RIDE_CATEGORY).getSelectedRows());
            }
        };

        List<ActionListener> categories = new ArrayList<>();
        categories.add(plus);
        categories.add(null);
        categories.add(null);
        categories.add(select);
        categories.add(delete);
        categories.add(null);
        return categories;
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
                JDialog dial = new SortRidesDialog(MainWindow.getFrame(), "Řazení");
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

        ActionListener resetFilters = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterRidesDialog.clearAttributes();
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.RIDES);
                RowFilter<ShareCarRiderTableModel<Ride>, Integer> rf = RowFilter.regexFilter(".*");
                TableRowSorter<ShareCarRiderTableModel<Ride>> sorter
                        = new TableRowSorter<>((ShareCarRiderTableModel<Ride>) table.getModel());
                sorter.setRowFilter(rf);
                table.setRowSorter(sorter);
            }
        };

        List<ActionListener> rides = new ArrayList<>();
        rides.add(plus);
        rides.add(sort);
        rides.add(filter);
        rides.add(select);
        rides.add(delete);
        rides.add(resetFilters);
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
                JDialog dial = new SortVehiclesDialog(MainWindow.getFrame(), "Řazení");
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

        ActionListener resetFilters = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterVehiclesDialog.clearAttributes();
                RowFilter<ShareCarRiderTableModel<Vehicle>, Integer> rf = new RowFilter<>() {
                    @Override
                    public boolean include(Entry<? extends ShareCarRiderTableModel<Vehicle>, ? extends Integer> entry) {
                        return true;
                    }
                };
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.VEHICLES);

                TableRowSorter<ShareCarRiderTableModel<Vehicle>> sorter
                        = new TableRowSorter<>((ShareCarRiderTableModel<Vehicle>) table.getModel());
                sorter.setRowFilter(rf);
                table.setRowSorter(sorter);
            }
        };

        List<ActionListener> vehicles = new ArrayList<>();
        vehicles.add(plus);
        vehicles.add(sort);
        vehicles.add(filter);
        vehicles.add(select);
        vehicles.add(delete);
        vehicles.add(resetFilters);
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
                JDialog dial = new SortPassengersDialog(MainWindow.getFrame(), "Řazení");
            }
        };

        ActionListener filter = actionListener -> {
            JDialog dial = new FilterPassengersDialog(MainWindow.getFrame(), "Filtr");
        };

        ActionListener select = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.PASSENGERS);
                table.enableMultilineSelection(!table.isMultilineSelectionEnabled());
            }
        };

        ActionListener resetFilters = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterPassengersDialog.clearSelectedCategoryIndices();
                ShareCarRiderTable table = Shortcut.getTable(TableCategory.PASSENGERS);
                RowFilter<ShareCarRiderTableModel<Passenger>, Integer> rf = RowFilter.regexFilter(".*");
                TableRowSorter<ShareCarRiderTableModel<Passenger>> sorter
                        = new TableRowSorter<>((ShareCarRiderTableModel<Passenger>) table.getModel());
                sorter.setRowFilter(rf);
                table.setRowSorter(sorter);
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
        passengers.add(resetFilters);
        return passengers;
    }

    public static ActionListener deleteRow(TableCategory category, int[] rows, DeleteDialog dialog) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int[] modelRows = Arrays.stream(rows).map(Shortcut.getTable(category)::convertRowIndexToModel).toArray();

                int firstRow = modelRows[0];
                for (int i = 0; i < modelRows.length; i++) {
                    Shortcut.getTableModel(category).deleteRow(firstRow);
                }
                ShareCarRiderTable table = Shortcut.getTable(category);
                table.clearSelection();
                dialog.dispose();
            }
        };
    }
}

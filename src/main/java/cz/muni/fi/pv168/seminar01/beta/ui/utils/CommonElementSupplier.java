package cz.muni.fi.pv168.seminar01.beta.ui.utils;

import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;

import javax.swing.*;

/**
 * @author Jakub Ratajik
 */
public class CommonElementSupplier {
    private CommonElementSupplier() {
    }

    public static ShareCarRiderTableModel<?> getTableModel(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS ->
                    (ShareCarRiderTableModel<?>) MainWindow.getPassengersTabFrame().getTable().getModel();
            case RIDES ->
                    (ShareCarRiderTableModel<?>) MainWindow.getRidesTabFrame().getTable().getModel();
            case VEHICLES ->
                    (ShareCarRiderTableModel<?>) MainWindow.getVehiclesTabFrame().getTable().getModel();
            case PASSENGER_CATEGORY ->
                    (ShareCarRiderTableModel<?>) MainWindow.getPassengerCategoriesTabFrame().getTable().getModel();
            case RIDE_CATEGORY ->
                    (ShareCarRiderTableModel<?>) MainWindow.getRideCategoriesTabFrame().getTable().getModel();
        };
    }

    public static ShareCarRiderTable getTable(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS -> MainWindow.getPassengersTabFrame().getTable();
            case RIDES -> MainWindow.getRidesTabFrame().getTable();
            case VEHICLES -> MainWindow.getVehiclesTabFrame().getTable();
            case PASSENGER_CATEGORY ->
                    MainWindow.getPassengerCategoriesTabFrame().getTable();
            case RIDE_CATEGORY ->
                    MainWindow.getRideCategoriesTabFrame().getTable();
        };
    }

    public static JButton getSelectButton(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS ->
                    MainWindow.getPassengersTabFrame().getSelectButton();
            case RIDES -> MainWindow.getRidesTabFrame().getSelectButton();
            case VEHICLES ->
                    MainWindow.getVehiclesTabFrame().getSelectButton();
            case PASSENGER_CATEGORY ->
                    MainWindow.getPassengerCategoriesTabFrame().getSelectButton();
            case RIDE_CATEGORY ->
                    MainWindow.getRideCategoriesTabFrame().getSelectButton();
        };
    }
}

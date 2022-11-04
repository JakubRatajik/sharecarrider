package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

import cz.muni.fi.pv168.seminar01.beta.Main;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.ShareCarRiderTable;

import javax.swing.*;

/**
 * @author Jakub Ratajik
 */
public class Shortcut {
    private Shortcut() {}
    public static ShareCarRiderTableModel<?> getTableModel(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS ->
                    (ShareCarRiderTableModel<?>) MainWindow.getPassengersTabFrame().getTable().getModel();
            case RIDES ->
                    (ShareCarRiderTableModel<?>) MainWindow.getRidesTabFrame().getTable().getModel();
            case VEHICLES ->
                    (ShareCarRiderTableModel<?>) MainWindow.getVehiclesTabFrame().getTable().getModel();
        };
    }

    public static ShareCarRiderTable getTable(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS ->
                    MainWindow.getPassengersTabFrame().getTable();
            case RIDES ->
                    MainWindow.getRidesTabFrame().getTable();
            case VEHICLES ->
                    MainWindow.getVehiclesTabFrame().getTable();
        };
    }

    public static JButton getSelectButton(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS ->
                    MainWindow.getPassengersTabFrame().getSelectButton();
            case RIDES ->
                    MainWindow.getRidesTabFrame().getSelectButton();
            case VEHICLES ->
                    MainWindow.getVehiclesTabFrame().getSelectButton();
        };
    }
}

package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

import cz.muni.fi.pv168.seminar01.beta.Model.TabCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.FilterPassengersDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.FilterRidesDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.FilterVehiclesDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.TemporaryDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.TabFrame;

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
     * @param category represents type of tab
     * @return list loaded with four ActionListeners, null if category == STATISTICS
     */
    public static List<ActionListener> getAddALs(TabCategory category) {
        return switch (category) {
            case RIDES -> getALsForRides();
            case VEHICLES -> getALsForVehicles();
            case PASSENGERS -> getALsForPassengers();
            default -> null;
        };

    }

    /**
     * makes ActionListeners for rides
     * @return list of rides ActionListeners
     */
    public static List<ActionListener> getALsForRides() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddRideDialog(MainWindow.getFrame(), "Přidat jízdu");
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
                    JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Vybrat");
                }
        };
        List<ActionListener> rides = new ArrayList<>();
        rides.add(plus);
        rides.add(sort);
        rides.add(filter);
        rides.add(select);
        return rides;
    }

    /**
     * makes ActionListeners for vehicles
     * @return list of vehicles ActionListeners
     */
    public static List<ActionListener> getALsForVehicles() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddVehicleDialog(MainWindow.getFrame(), "Přidat vozidlo");
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
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Vybrat");
            }
        };
        List<ActionListener> vehicles = new ArrayList<>();
        vehicles.add(plus);
        vehicles.add(sort);
        vehicles.add(filter);
        vehicles.add(select);
        return vehicles;
    }

    /**
     * makes ActionListeners for passengers
     * @return list of passengers ActionListeners
     */
    public static List<ActionListener> getALsForPassengers() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddPassengerDialog(MainWindow.getFrame(), "Přidat cestujícího");
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
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Vybrat");
            }
        };
        List<ActionListener> passengers = new ArrayList<>();
        passengers.add(plus);
        passengers.add(sort);
        passengers.add(filter);
        passengers.add(select);
        return passengers;
    }

}

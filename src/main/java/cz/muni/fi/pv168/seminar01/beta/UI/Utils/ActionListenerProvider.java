package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.TemporaryDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.TabCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.TabFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Macecek
 */
public final class ActionListenerProvider {

    public static List<ActionListener> getAddALs(TabCategory category) {
        return switch (category) {
            case RIDES -> getALsForRides();
            case VEHICLES -> getALsForVehicles();
            case PASSENGERS -> getALsForPassengers();
            default -> null;
        };

    }
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
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Filtr");
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
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Filtr");
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

    public static List<ActionListener> getALsForPassengers() {
        ActionListener plus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddVehicleDialog(MainWindow.getFrame(), "Přidat cestujícího");
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
                JDialog dial = new TemporaryDialog(MainWindow.getFrame(), "Filtr");
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

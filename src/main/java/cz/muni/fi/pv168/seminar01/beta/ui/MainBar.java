package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ExportDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.FuelDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ImportDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainBar extends JMenuBar {

    public MainBar() {
        initialize();
    }

    private void initialize() {

        setBackground(UIUtilities.WHITE);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        var fuelSettings = new JMenu("Nastavení paliva");
        fuelSettings.setToolTipText("Otevřít nastavení paliva");
        fuelSettings.setMnemonic('f');
        UIUtilities.formatWhiteTextBrownMenu(fuelSettings);
        JMenuItem fuelSettingsItem = new JMenuItem(new AbstractAction("Ceny paliv") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new FuelDialog(MainWindow.getFrame());
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(fuelSettingsItem);
        fuelSettings.add(fuelSettingsItem);
        add(fuelSettings);

        var importExport = new JMenu("Import / Export");
        importExport.setToolTipText("Otevřít nastavení importu a exportu");
        importExport.setMnemonic('i');
        UIUtilities.formatWhiteTextBrownMenu(importExport);

        JMenuItem importItem = new JMenuItem(new AbstractAction("Import") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new ImportDialog(MainWindow.getFrame(), "Import");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(importItem);
        importExport.add(importItem);

        JMenuItem exportItem = new JMenuItem(new AbstractAction("Export") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new ExportDialog(MainWindow.getFrame(), "Export");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(exportItem);
        importExport.add(exportItem);

        add(importExport);


        var addMenuItem = new JMenu("Přidat");
        addMenuItem.setToolTipText("Přidat jízdu, vozidlo nebo pasažéra");
        addMenuItem.setMnemonic('a');
        UIUtilities.formatWhiteTextBrownMenu(addMenuItem);

        JMenuItem addRide = new JMenuItem(new AbstractAction("Přidat jízdu") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditRideDialog(MainWindow.getFrame(), "Přidat jízdu");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(addRide);
        addMenuItem.add(addRide);

        JMenuItem addVehicle = new JMenuItem(new AbstractAction("Přidat vozidlo") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditVehicleDialog(MainWindow.getFrame(), "Přidat Vozidlo");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(addVehicle);
        addMenuItem.add(addVehicle);

        JMenuItem addPassenger = new JMenuItem(new AbstractAction("Přidat cestujícího") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditPassengerDialog(MainWindow.getFrame(), "Přidat cestujícího");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(addPassenger);
        addMenuItem.add(addPassenger);

        JMenuItem addRideCategory = new JMenuItem(new AbstractAction("Přidat kategorii jízd") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditRideCategoryDialog(MainWindow.getFrame(), "Přidat kategorii jízdy");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(addRideCategory);
        addMenuItem.add(addRideCategory);

        JMenuItem addPassengerCategory = new JMenuItem(new AbstractAction("Přidat kategorii cestujícího") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddEditPassengerCategoryDialog(MainWindow.getFrame(), "Přidat kategorii cestujícího");
            }
        });
        UIUtilities.formatWhiteTextBrownMenu(addPassengerCategory);
        addMenuItem.add(addPassengerCategory);

        add(addMenuItem);

    }


    private void callFuelDialog(ActionEvent e) {
        Dialog dialog = new FuelDialog(MainWindow.getFrame());
    }
}

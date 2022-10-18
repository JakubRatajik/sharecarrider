package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.FuelDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainBar extends JMenuBar {

    public MainBar() {
        initialize();
    }

    private void initialize() {

        setBackground(UIConstants.WHITE);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        var fuelSettings = new JMenu("Nastavení paliva");
        fuelSettings.setToolTipText("Otevřít nastavení paliva");
        fuelSettings.setMnemonic('f');
        UIConstants.formatWhiteTextBrownMenu(fuelSettings);
        JMenuItem fuelSettingsItem = new JMenuItem(new AbstractAction("Ceny paliv") {
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new FuelDialog(MainWindow.getFrame());
            }
        });
        UIConstants.formatWhiteTextBrownMenu(fuelSettingsItem);
        fuelSettings.add(fuelSettingsItem);
        add(fuelSettings);

        var importExport = new JMenu("Import / Export");
        importExport.setToolTipText("Otevřít nastavení importu a exportu");
        importExport.setMnemonic('i');
        UIConstants.formatWhiteTextBrownMenu(importExport);

        JMenuItem importItem = new JMenuItem(new AbstractAction("Import") {
            public void actionPerformed(ActionEvent e) {
                // Button pressed logic goes here
            }
        });
        UIConstants.formatWhiteTextBrownMenu(importItem);
        importExport.add(importItem);

        JMenuItem exportItem = new JMenuItem(new AbstractAction("Export") {
            public void actionPerformed(ActionEvent e) {
                // Button pressed logic goes here
            }
        });
        UIConstants.formatWhiteTextBrownMenu(exportItem);
        importExport.add(exportItem);

        add(importExport);

        var categories = new JMenu("Kategorie");
        categories.setToolTipText("Otevřít menu pro kategorie");
        categories.setMnemonic('c');
        UIConstants.formatWhiteTextBrownMenu(categories);

        JMenuItem showCategoriesItem = new JMenuItem(new AbstractAction("Zobrazit kategorie") {
            public void actionPerformed(ActionEvent e) {
                // Button pressed logic goes here
            }
        });
        UIConstants.formatWhiteTextBrownMenu(showCategoriesItem);
        categories.add(showCategoriesItem);

        JMenuItem addCategoryItem = new JMenuItem(new AbstractAction("Přidat kategorii") {
            public void actionPerformed(ActionEvent e) {
                // Button pressed logic goes here
            }
        });
        UIConstants.formatWhiteTextBrownMenu(addCategoryItem);
        categories.add(addCategoryItem);



        add(categories);



    }


    private void callFuelDialog(ActionEvent e) {
        Dialog dialog = new FuelDialog(MainWindow.getFrame());
    }
}

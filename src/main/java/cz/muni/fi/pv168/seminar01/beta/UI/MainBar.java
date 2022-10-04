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
        var fuelSettings = new JMenu("Fuel Settings");
        fuelSettings.setToolTipText("Open Fuel Settings");
        fuelSettings.setMnemonic('f');
        fuelSettings.setBackground(UIConstants.WHITE);
        fuelSettings.setForeground(UIConstants.TEXT_BROWN);
        fuelSettings.setFont(UIConstants.fMenu);
        add(fuelSettings);

        var importExport = new JMenu("Import / Export");
        importExport.setToolTipText("Open Import and Export options");
        importExport.setMnemonic('i');
        importExport.setBackground(UIConstants.WHITE);
        importExport.setForeground(UIConstants.TEXT_BROWN);
        importExport.setFont(UIConstants.fMenu);
        add(importExport);

        var categories = new JMenu("Categories");
        categories.setToolTipText("Open Category menu");
        categories.setMnemonic('c');
        categories.setBackground(UIConstants.WHITE);
        categories.setForeground(UIConstants.TEXT_BROWN);
        categories.setFont(UIConstants.fMenu);
        add(categories);



    }


    private void callFuelDialog(ActionEvent e) {
        Dialog dialog = new FuelDialog(JFrameWindow.getFrame());
    }
}

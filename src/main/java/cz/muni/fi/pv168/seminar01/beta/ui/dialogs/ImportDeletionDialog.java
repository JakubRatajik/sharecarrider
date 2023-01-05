package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.workers.AsyncImporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ImportDeletionDialog extends DialogBase{
    private File rides;
    private File vehicles;
    private File passengers;
    private File passengersCategories;
    private File ridesCategories;

    public ImportDeletionDialog(Frame frame, String name, File rides, File vehicles, File passengers, File passengersCategories, File ridesCategories) {
        super(frame, name, true);
        this.rides = rides;
        this.vehicles = vehicles;
        this.passengers = passengers;
        this.passengersCategories = passengersCategories;
        this.ridesCategories = ridesCategories;
        initialize();
    }



    private void onImportButton(JButton button) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AsyncImporter importer = new AsyncImporter();
                importer.importData(rides, vehicles, passengers, passengersCategories, ridesCategories);
                dispose();
                }
            });

    }
    @Override
    protected void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Neimportovat");
        cancel.addActionListener(e -> dispose());
        JButton importButton = new JButton("Naimportovat");
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(importButton);
        bottom.add(cancel);
        bottom.add(importButton);
        try {
            onImportButton(importButton);
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), e);
        }
        dispose();
    }

    @Override
    protected void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        UIUtilities.formatWhiteTextBrownDialog(center);

        JLabel importRides = new JLabel("Veškeré předchozí záznamy budou smazány, chcete opravdu data naimportovat?");
        center.add(importRides);
        this.add(center);
        setSize(450, 120);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }
}

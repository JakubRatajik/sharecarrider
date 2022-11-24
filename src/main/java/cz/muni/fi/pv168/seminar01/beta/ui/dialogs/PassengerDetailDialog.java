package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class PassengerDetailDialog extends DetailDialog {
    Passenger passenger;

    public PassengerDetailDialog(Frame frame, String name, Passenger passenger) {
        super(frame, name, passenger);
    }


    @Override
    public void onEditButton(JButton edit) {
        edit.addActionListener(e -> new AddEditPassengerDialog(MainWindow.getFrame(), "Upravit cestujícího", passenger));
    }

    @Override
    public void initializeCenter(JPanel center) {
        int categoriesCount = passenger.getCategories().size();
        int height = 200;
        center.setLayout(new GridLayout(3 + categoriesCount, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(new JLabel(passenger.getFirstName()));
        center.add(new JLabel("•  Příjmení"));
        center.add(new JLabel(passenger.getLastName()));
        center.add(new JLabel("•  Telefon"));
        center.add(new JLabel(passenger.getPhoneNumber()));
        if (categoriesCount != 0) {
            height += 10;
            center.add(new JLabel("•  Kategorie:"));
            center.add(new JLabel(passenger.getCategories().stream().toList().get(0).getName()));
        }
        for (int i = 1; i < categoriesCount; i++) {
            height += 10;
            center.add(new JLabel(""));
            center.add(new JLabel(passenger.getCategories().stream().toList().get(i).getName()));
        }
        add(center, BorderLayout.CENTER);
        setSize(330, height);
    }

    @Override
    public void addAttribute(Object attribute) {
        passenger = (Passenger) attribute;
    }
}

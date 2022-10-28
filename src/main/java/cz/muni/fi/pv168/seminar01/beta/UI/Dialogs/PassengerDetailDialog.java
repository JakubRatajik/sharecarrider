package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class PassengerDetailDialog extends DetailDialog {
    Passenger passenger;

    public PassengerDetailDialog(Frame frame, String name, Passenger passenger) {
        super(frame, name, passenger);
    }


    @Override
    public void onEditButton(JButton edit) {

    }

    @Override
    public void initializeCenter(JPanel center) {
        int categoriesCount = passenger.getCategories().size();
        int height = 200;
        center.setLayout(new GridLayout(3 + categoriesCount, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(new JLabel(passenger.getFirstName()));
        center.add(new JLabel("•  Příjmení"));
        center.add(new JLabel(passenger.getLastName()));
        center.add(new JLabel("•  Telefon"));
        center.add(new JLabel(passenger.getPhoneNumber()));
        if (categoriesCount != 0) {
            height += 10;
            center.add(new JLabel("•  Kategorie:"));
            center.add(new JLabel(passenger.getCategories().stream().toList().get(0).toString()));
        }
        for (int i = 1; i < categoriesCount; i++) {
            height += 10;
            center.add(new JLabel(""));
            center.add(new JLabel(passenger.getCategories().stream().toList().get(i).toString()));
        }
        add(center, BorderLayout.CENTER);
        setSize(330, height);
    }

    @Override
    public void addAttribute(Object attribute) {
        passenger = (Passenger) attribute;
    }
}

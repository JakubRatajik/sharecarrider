package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Katerina Vacova
 */

public class RideDetailDialog extends DetailDialog {
    private Ride ride;

    public RideDetailDialog(Frame frame, String name, Ride ride) {
        super(frame, name, ride);
    }


    @Override
    public void onEditButton(JButton edit) {

    }

    @Override
    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIConstants.formatWhiteTextBrownDialog(center);

        JPanel nonChangeable = new JPanel();
        nonChangeable.setLayout(new GridLayout(7, 2));
        UIConstants.formatWhiteTextBrownDialog(nonChangeable);
        nonChangeable.add(new JLabel("•  Datum:"));
        nonChangeable.add(new JLabel(ride.getDate()));
        nonChangeable.add(new JLabel("•  Čas:"));
        nonChangeable.add(new JLabel(ride.getTime()));
        nonChangeable.add(new JLabel("•  Začátek:"));
        nonChangeable.add(new JLabel(ride.getFrom()));
        nonChangeable.add(new JLabel("•  Cíl:"));
        nonChangeable.add(new JLabel(ride.getTo()));
        nonChangeable.add(new JLabel("•  Vzdálenost:"));
        nonChangeable.add(new JLabel(ride.getDistance() + " km"));
        nonChangeable.add(new JLabel("•  Vozidlo:"));
        nonChangeable.add(new JLabel(ride.getVehicle().getBrand() + " " + ride.getVehicle().getType()));
        nonChangeable.add(new JLabel("•  Cena:"));
        nonChangeable.add(new JLabel(String.format("%.5g%n", ride.countPrice()) + " Kč"));
        center.add(nonChangeable);
        int height = 260;

        String empty = "";
        JPanel passengers = new JPanel();
        int passengersCount = ride.getPassengers().size();
        List<Passenger> passengerList = null;
        passengers.setLayout(new GridLayout(passengersCount, 2));
        UIConstants.formatWhiteTextBrownDialog(passengers);
        if (passengersCount != 0) {
            height += 10;
            passengers.add(new JLabel("•  Cestující:"));
            passengerList = ride.getPassengers().stream().toList();
            passengers.add(new JLabel(passengerList.get(0).getFirstName() + " " + passengerList.get(0).getLastName()));
        }
        for (int i = 1; i < passengersCount; i++) {
            height += 10;
            passengers.add(new JLabel(empty));
            passengers.add(new JLabel(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName()));
        }
        center.add(passengers);

        JPanel categories = new JPanel();
        int categoriesCount = ride.getCategories().size();
        List<RideCategory> categoryList = null;
        categories.setLayout(new GridLayout(categoriesCount, 2));
        UIConstants.formatWhiteTextBrownDialog(categories);
        if (categoriesCount != 0) {
            height += 10;
            categories.add(new JLabel("•  Kategorie:"));
            categoryList = ride.getCategories().stream().toList();
            categories.add(new JLabel(categoryList.get(0).getName()));
        }
        for (int i = 1; i < categoriesCount; i++) {
            height += 10;
            categories.add(new JLabel(empty));
            categories.add(new JLabel(categoryList.get(i).getName()));
        }
        center.add(categories);
        add(center, BorderLayout.CENTER);
        setSize(330, height);
    }


    @Override
    public void addAttribute(Object attribute) {
        ride = (Ride) attribute;
    }
}

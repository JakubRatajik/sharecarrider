package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCat;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Katerina Vacova
 */

public class RideDetailDialog extends DetailDialog {
    private Ride ride;
    private JLabel date;
    private JLabel departure;
    private JLabel arrival;
    private JLabel startDestination;
    private JLabel endDestination;
    private JLabel distance;
    private JLabel vehicle;
    private JLabel[] passengers;
    private JLabel[] categories;
    private JLabel repetition;
    private JLabel price;
    private JTextArea description;

    public RideDetailDialog(Frame frame, String name, Ride ride) {
        super(frame, name, ride);
    }


    @Override
    public void onEditButton(JButton edit) {
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddEditRideDialog(MainWindow.getFrame(), "Upravit jízdu", ride);
            }
        });
    }

    @Override
    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIUtilities.formatWhiteTextBrownDialog(center);

        setAttributes();

        JPanel nonChangeable = new JPanel();
        nonChangeable.setLayout(new GridLayout(9, 2));
        UIUtilities.formatWhiteTextBrownDialog(nonChangeable);
        nonChangeable.add(new JLabel("•  Datum:"));
        nonChangeable.add(date);
        nonChangeable.add(new JLabel("•  Čas odjezdu:"));
        nonChangeable.add(departure);
        nonChangeable.add(new JLabel("•  Čas příjezdu:"));
        nonChangeable.add(arrival);
        nonChangeable.add(new JLabel("•  Začátek:"));
        nonChangeable.add(startDestination);
        nonChangeable.add(new JLabel("•  Cíl:"));
        nonChangeable.add(endDestination);
        nonChangeable.add(new JLabel("•  Vzdálenost:"));
        nonChangeable.add(distance);
        nonChangeable.add(new JLabel("•  Vozidlo:"));
        nonChangeable.add(vehicle);
        nonChangeable.add(new JLabel("•  Cena:"));
        nonChangeable.add(price);
        nonChangeable.add(new JLabel("•  Opakování:"));
        nonChangeable.add(repetition);
        center.add(nonChangeable);
        int height = 330;

        String empty = "";
        JPanel passengers = new JPanel();
        int passengersCount = ride.getPassengers().size();
        List<Passenger> passengerList = null;
        passengers.setLayout(new GridLayout(passengersCount, 2));
        UIUtilities.formatWhiteTextBrownDialog(passengers);
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
        List<RideCat> categoryList = null;
        categories.setLayout(new GridLayout(categoriesCount, 2));
        UIUtilities.formatWhiteTextBrownDialog(categories);
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

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(1, 2));
        UIUtilities.formatWhiteTextBrownDialog(descriptionPanel);
        descriptionPanel.add(new JLabel("•  Popis:"));
        descriptionPanel.add(description);

        center.add(descriptionPanel);
        add(center, BorderLayout.CENTER);
        setSize(330, height);
    }

    public void setAttributes() {
        date = new JLabel(ride.getDate());
        departure = new JLabel(ride.getDeparture());
        startDestination = new JLabel(ride.getFrom());
        endDestination = new JLabel(ride.getTo());
        distance = new JLabel(ride.getDistance() + " km");
        vehicle = new JLabel(ride.getVehicle().getBrand() + " " + ride.getVehicle().getType());
        price = new JLabel(ride.countPrice().setScale(0, RoundingMode.DOWN) + " Kč");
        repetition = new JLabel(ride.getRepetition().getDescription());
        //arrival = new JLabel(ride.getArrival());
        arrival = new JLabel("");
        description = new JTextArea(ride.getDescription());
        description.setEditable(false);
        description.setBackground(Color.WHITE);
        description.setLineWrap(true);
    }


    @Override
    public void addAttribute(Object attribute) {
        ride = (Ride) attribute;
    }

    public void update() {
    }
}

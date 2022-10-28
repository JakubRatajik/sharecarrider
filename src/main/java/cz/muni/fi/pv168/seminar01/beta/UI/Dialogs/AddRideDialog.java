package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRideDialog extends AddDialog {

    private JDatePicker date;
    private JTextField time;
    private JTextField startDestination;
    private JTextField endDestination;
    private JTextField distance;
    private JComboBox<Vehicle> vehicle;
    private JScrollPane passengers;
    private JComboBox<Integer> repetition;

    public AddRideDialog(Frame frame, String name) {
        super(frame, name);
    }

    protected void setAttributes() {
        this.date = new JDatePicker();
        UIConstants.formatComponentDialog(date);
        this.time = UIConstants.createTextField();
        this.startDestination = UIConstants.createTextField();
        this.endDestination = UIConstants.createTextField();
        this.distance = UIConstants.createTextField();
        this.vehicle = new JComboBox<Vehicle>();
        UIConstants.formatComponentDialog(vehicle);

        // This should be for selecting multiple passengers
        JList<Passenger> passengerList = new JList<Passenger>();
        UIConstants.formatComponentDialog(passengerList);
        passengerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane passengersScroll = new JScrollPane();
        UIConstants.formatComponentDialog(passengersScroll);
        this.passengers = passengersScroll;

        this.repetition = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(repetition);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(8, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Datum:"));
        center.add(this.date);
        center.add(new JLabel("•  Čas:"));
        center.add(this.time);
        center.add(new JLabel("•  Začátek:"));
        center.add(this.startDestination);
        center.add(new JLabel("•  Cíl:"));
        center.add(this.endDestination);
        center.add(new JLabel("•  Vzdálenost:"));
        center.add(this.distance);
        center.add(new JLabel("•  Vozidlo:"));
        center.add(this.vehicle);
        center.add(new JLabel("•  Cestující:"));
        center.add(this.passengers);
        center.add(new JLabel("•  Opakování:"));
        center.add(this.repetition);
        setSize(330, 350);
    }


    protected void onCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO - there need to be save action, but is not implemented yet
                dispose();
            }
        });
    }


}

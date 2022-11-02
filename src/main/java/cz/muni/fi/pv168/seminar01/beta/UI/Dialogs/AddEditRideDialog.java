package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.JDatePickerDateGetter;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

public class AddEditRideDialog extends AddDialog {
    private Ride ride = null;
    private JDatePicker date;
    private JTextField time;
    private JTextField startDestination;
    private JTextField endDestination;
    private JTextField distance;
    private JComboBox<Vehicle> vehicle;
    private JScrollPane passengers;
    private JList<Passenger> passengersList;
    private JComboBox<RideCategory> category;
    private JComboBox<Repetition> repetition;

    public AddEditRideDialog(Frame frame, String name) {
        super(frame, name);
    }


    public AddEditRideDialog(JFrame frame, String name, Ride ride) {
        super(frame, name, ride);
    }

    @Override
    public void initializeBottom(JPanel bottom) {
        if (vehicle != null) {
            JButton cancel = new JButton("Zrušit");
            cancel.addActionListener(e -> dispose());
            UIConstants.formatComponentDialog(cancel);
            JButton save = new JButton("Uložit");
            onSaveEditButton(save);
            UIConstants.formatComponentDialog(save);
            bottom.add(cancel);
            bottom.add(save);
            return;
        }

        JButton create = new JButton("Vytvořit");
        onCreateButton(create);
        UIConstants.formatComponentDialog(create);
        bottom.add(create);
    }

    private void onSaveEditButton(JButton save) {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] parsedTime = (time.getText().split("[:.]"));
                LocalTime localTime = LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]));
                int parsedDistance = Integer.parseInt(distance.getText());
                RideTableModel tableModel = (RideTableModel) DialogBase.getTableModel(TableCategory.RIDES);
                ride.setDate(JDatePickerDateGetter.getLocalDate(date));
                ride.setTime(localTime);
                ride.setFrom(startDestination.getText());
                ride.setTo(endDestination.getText());
                ride.setDistance(parsedDistance);
                ride.setPassengers(passengersList.getSelectedValuesList());
                ride.setVehicle((Vehicle) vehicle.getSelectedItem());
                ride.setRepetition((Repetition) repetition.getSelectedItem());
                tableModel.updateRow(ride);
                dispose();
            }
        });

    }

    public AddEditRideDialog(Frame frame, String name, Ride ride) {
        super(frame, name, ride);
    }

    @Override
    protected void addAttribute(Object attribute) {
        ride = (Ride) attribute;
    }

    protected void setAttributes() {
        this.date = new JDatePicker();
        UIConstants.formatComponentDialog(date);
        this.time = UIConstants.createTextField();
        this.startDestination = UIConstants.createTextField();
        this.endDestination = UIConstants.createTextField();
        this.distance = UIConstants.createTextField();

        this.vehicle = new JComboBox<>();
        for (Vehicle v : (List<Vehicle>) DialogBase.getTableModel(TableCategory.VEHICLES).getData()) {
            vehicle.addItem(v);
        }
        UIConstants.formatComponentDialog(vehicle);

        // TODO - add category ComboBox
        this.category = new JComboBox<>();
        //for (RideCategory c : )
        UIConstants.formatComponentDialog(category);

        this.repetition = new JComboBox<>();
        for (Repetition rep : Repetition.values()) {
            repetition.addItem(rep);
        }
        UIConstants.formatComponentDialog(repetition);

        DefaultListModel<Passenger> l1 = new DefaultListModel<>();
        List<Passenger> passengers = (List<Passenger>) DialogBase.getTableModel(TableCategory.PASSENGERS).getData();
        l1.addAll(passengers);

        JList<Passenger> passengerList = new JList<>(l1);
        passengerList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                setText(((Passenger) value).getFullName());

                return component;
            }

        });
       // passengerList.getSelectedValuesList()
        UIConstants.formatComponentDialog(passengerList);
        passengerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane passengersScroll = new JScrollPane(passengerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        passengersScroll.setPreferredSize(new Dimension(40, 0));
        UIConstants.formatComponentDialog(passengersScroll);
        this.passengers = passengersScroll;
        this.passengersList = passengerList;

        if (ride != null) {
            time.setText(ride.getTime());
            startDestination.setText(ride.getFrom());
            endDestination.setText(ride.getTo());
            distance.setText(String.valueOf(ride.getDistance()));
            vehicle.setSelectedItem(ride.getVehicle());
            repetition.setSelectedItem(ride.getRepetition());
            for (Passenger passenger: ride.getPassengers()) {
                this.passengersList.setSelectedValue(passenger, true);
            }
        }
    }

    public void initializeContent(JPanel central) {
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(7, 2));
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
        center.add(new JLabel("•  Opakování:"));
        center.add(this.repetition);
        central.add(center);

        JPanel passengerPanel = new JPanel();
        passengerPanel.setLayout(new GridLayout(1, 2));
        passengerPanel.add(new JLabel("•  Cestující:"));
        passengerPanel.add(this.passengers);
        UIConstants.formatWhiteTextBrownDialog((passengerPanel));
        central.add(passengerPanel);
        setSize(330, 400);
        UIConstants.formatWhiteTextBrownDialog(central);


    }


    protected void onCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO - there need to be validation of data inserted

                String[] parsedTime = (time.getText().split("[:.]"));
                LocalTime localTime = LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]));
                int parsedDistance = Integer.parseInt(distance.getText());
                RideTableModel tableModel = (RideTableModel) DialogBase.getTableModel(TableCategory.RIDES);

                Ride ride = new Ride(
                        JDatePickerDateGetter.getLocalDate(date),
                        localTime,
                        startDestination.getText(),
                        endDestination.getText(),
                        parsedDistance,
                        new HashSet<>(),
                        passengersList.getSelectedValuesList(),
                        (Vehicle) vehicle.getSelectedItem(),
                        (Repetition) repetition.getSelectedItem()
                );
                tableModel.addRow(ride);
                dispose();
            }
        });
    }


}

package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.validation.RideValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.EnumRendererForComboBox;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.JDatePickerDateGetter;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;

public class AddEditRideDialog extends AddEditDialog {
    private Ride ride = null;
    private JDatePicker date;
    private JTextField departure;
    private JTextField arrival;
    private JTextField startDestination;
    private JTextField endDestination;
    private JTextField distance;
    private JComboBox<Vehicle> vehicle;
    private JScrollPane passengers;
    private JList<Passenger> passengersList;

    private JScrollPane categories;
    private JList<RideCategory> categoryList;
    private JComboBox<Repetition> repetition;
    private JTextArea description;


    public AddEditRideDialog(Frame frame, String name) {
        super(frame, name);
    }


    public AddEditRideDialog(Frame frame, String name, Ride ride) {
        super(frame, name, ride);
    }

    @Override
    public void initializeBottom(JPanel bottom) {
        if (ride != null) {
            JButton cancel = new JButton("Zrušit");
            cancel.addActionListener(e -> dispose());
            UIUtilities.formatDefaultComponent(cancel);
            JButton save = new JButton("Uložit");
            onSaveEditButton(save);
            UIUtilities.formatDefaultComponent(save);
            bottom.add(cancel);
            bottom.add(save);
            return;
        }

        JButton create = new JButton("Vytvořit");
        onCreateButton(create);
        UIUtilities.formatDefaultComponent(create);
        bottom.add(create);
    }

    private void onSaveEditButton(JButton save) {
        save.addActionListener(actionListener -> {
            trimAllTextComponents();
            if (!validateRideInput()) {
                return;
            }

            String[] departureParsedTime = (departure.getText().split("[:.]"));
            String[] arrivalParsedTime = arrival.getText().split("[:.]");
            LocalTime departureTime = LocalTime.of(Integer.parseInt(departureParsedTime[0]), Integer.parseInt(departureParsedTime[1]));
            LocalTime arrivalTime = LocalTime.of(Integer.parseInt(arrivalParsedTime[0]), Integer.parseInt(arrivalParsedTime[1]));
            int parsedDistance = Integer.parseInt(distance.getText());
            RideTableModel tableModel = (RideTableModel) CommonElementSupplier.getTableModel(TableCategory.RIDES);
            ride.setDate(JDatePickerDateGetter.getLocalDate(date));
            ride.setDeparture(departureTime);
            ride.setFrom(startDestination.getText());
            ride.setTo(endDestination.getText());
            ride.setDistance(parsedDistance);
            ride.setPassengers(passengersList.getSelectedValuesList());
            ride.setVehicle((Vehicle) vehicle.getSelectedItem());
            ride.setRepetition((Repetition) repetition.getSelectedItem());
            ride.setArrival(arrivalTime);
            ride.setCategories(categoryList.getSelectedValuesList());
            ride.setDescription(description.getText());

            tableModel.updateRow(ride);
            dispose();
            new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", ride);
        });

    }

    @Override
    protected void addAttribute(Object attribute) {
        ride = (Ride) attribute;
    }

    protected void setAttributes() {
        this.date = new JDatePicker();
        UIUtilities.formatDefaultComponent(date);
        this.departure = UIUtilities.createTextField();
        this.arrival = UIUtilities.createTextField();
        this.startDestination = UIUtilities.createTextField();
        this.endDestination = UIUtilities.createTextField();
        this.distance = UIUtilities.createTextField();
        this.description = new JTextArea();
        UIUtilities.formatDefaultComponent(description);
        description.setBorder(new MatteBorder(3, 3, 3, 3, UIUtilities.LIGHT_BEIGE));
        description.setLineWrap(true);

        this.vehicle = new JComboBox<>();
        for (Vehicle v : (List<Vehicle>) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getData()) {
            vehicle.addItem(v);
        }
        UIUtilities.formatDefaultJComboBox(vehicle);

        this.repetition = new JComboBox<>();
        for (Repetition rep : Repetition.values()) {
            repetition.addItem(rep);
        }
        UIUtilities.formatDefaultJComboBox(repetition, new EnumRendererForComboBox());


        // Passengers
        DefaultListModel<Passenger> l1 = new DefaultListModel<>();
        List<Passenger> passengers = (List<Passenger>) CommonElementSupplier.getTableModel(TableCategory.PASSENGERS).getData();
        l1.addAll(passengers);

        JList<Passenger> passengerList = new JList<>(l1);
        passengerList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean hasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

                if (isSelected) {
                    component.setBackground(UIUtilities.MIDDLE_BROWN);
                }

                setText(((Passenger) value).getFullName());

                return component;
            }
        });

        UIUtilities.formatDefaultComponent(passengerList);
        passengerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane passengersScroll = new JScrollPane(passengerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        passengersScroll.setPreferredSize(new Dimension(40, 0));
        UIUtilities.formatDefaultComponent(passengersScroll);
        this.passengers = passengersScroll;
        this.passengersList = passengerList;

        // Categories
        DefaultListModel<RideCategory> r1 = new DefaultListModel<>();
        List<RideCategory> rides = (List<RideCategory>) CommonElementSupplier.getTableModel(TableCategory.RIDE_CATEGORY).getData();
        r1.addAll(rides);

        JList<RideCategory> rideList = new JList<>(r1);
        rideList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean hasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

                if (isSelected) {
                    component.setBackground(UIUtilities.MIDDLE_BROWN);
                }

                setText(((RideCategory) value).getName());

                return component;
            }
        });

        UIUtilities.formatDefaultComponent(rideList);
        rideList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane ridesScroll = new JScrollPane(rideList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ridesScroll.setPreferredSize(new Dimension(40, 5));
        UIUtilities.formatDefaultComponent(ridesScroll);
        this.categories = ridesScroll;
        this.categoryList = rideList;


        if (ride != null) {
            departure.setText(ride.getDepartureFormatted());
            arrival.setText(ride.getArrivalFormatted());
            startDestination.setText(ride.getFrom());
            endDestination.setText(ride.getTo());
            distance.setText(String.valueOf(ride.getDistance()));
            vehicle.setSelectedItem(ride.getVehicle());
            repetition.setSelectedItem(ride.getRepetition());
            for (Passenger passenger : ride.getPassengers()) {
                this.passengersList.setSelectedValue(passenger, true);
            }
        }
    }

    public void initializeContent(JPanel central) {
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(10, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Datum:"));
        center.add(this.date);
        center.add(new JLabel("•  Čas odjezdu:"));
        center.add(this.departure);
        center.add(new JLabel("•  Čas příjezdu"));
        center.add(this.arrival);
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

        JPanel descrptionPanel = new JPanel();
        descrptionPanel.setLayout(new GridLayout(1, 2));
        descrptionPanel.add(new JLabel("• Popis:"));
        JScrollPane descriptionScroll = new JScrollPane(description); // Added to keep size of layout the same
        descrptionPanel.add(descriptionScroll); // Added to keep size of layout the same
        UIUtilities.formatWhiteTextBrownDialog(descrptionPanel);
        central.add(descrptionPanel);

        JPanel passengerPanel = new JPanel();
        passengerPanel.setLayout(new GridLayout(1, 2));
        passengerPanel.add(new JLabel("•  Cestující:"));
        passengerPanel.add(this.passengers);
        UIUtilities.formatWhiteTextBrownDialog(passengerPanel);
        central.add(passengerPanel);

        central.add(new JLabel(" "));
        central.add(new JLabel(" "));

        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(1, 2));
        categoriesPanel.add(new JLabel("•  Kategorie:"));
        categoriesPanel.add(this.categories);
        UIUtilities.formatWhiteTextBrownDialog(categoriesPanel);
        central.add(categoriesPanel);


        setSize(500, 600);
        UIUtilities.formatWhiteTextBrownDialog(central);
    }

    protected void onCreateButton(JButton create) {
        create.addActionListener(actionListener -> {
            trimAllTextComponents();
            if (!validateRideInput()) {
                return;
            }

            String[] departureParsedTime = (departure.getText().split("\\s*:\\s*"));
            LocalTime departureTime = LocalTime.of(Integer.parseInt(departureParsedTime[0]), Integer.parseInt(departureParsedTime[1]));
            String[] arrivalParsedTime = arrival.getText().split("\\s*:\\s*");
            LocalTime arrivalTime = LocalTime.of(Integer.parseInt(arrivalParsedTime[0]), Integer.parseInt(arrivalParsedTime[1]));
            int parsedDistance = Integer.parseInt(distance.getText());
            RideTableModel tableModel = (RideTableModel) CommonElementSupplier.getTableModel(TableCategory.RIDES);

            Ride ride = new Ride(
                    JDatePickerDateGetter.getLocalDate(date),
                    departureTime,
                    arrivalTime,
                    startDestination.getText(),
                    endDestination.getText(),
                    parsedDistance,
                    categoryList.getSelectedValuesList(),
                    passengersList.getSelectedValuesList(),
                    (Vehicle) vehicle.getSelectedItem(),
                    (Repetition) repetition.getSelectedItem(),
                    description.getText()
            );
            tableModel.addRow(ride);
            dispose();
        });
    }

    public boolean validateRideInput() {
        try {
            String idString = null;
            if (ride != null) {
                idString = ride.getId()+"";
            }
            RideValidator.validateRide(idString, JDatePickerDateGetter.getLocalDate(date), departure.getText(), arrival.getText(), startDestination.getText(), endDestination.getText(), distance.getText(), description.getText());
            return true;
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), e);
            return false;
        }
    }

    private void trimAllTextComponents() {
        List<JTextComponent> textComponents = List.of(departure, arrival, startDestination, endDestination, distance, description);

        for (JTextComponent textComponent : textComponents) {
            textComponent.setText(textComponent.getText().trim());
        }
    }
}

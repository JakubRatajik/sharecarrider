package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    @Override
    protected void addAttribute(Object attribute) {}

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

        this.repetition = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(repetition);
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
                //TODO - there need to be save action, but is not implemented yet
                dispose();
            }
        });
    }


}

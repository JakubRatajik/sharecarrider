package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class AddRideDialog extends JDialog {

    public AddRideDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setLayout(new GridLayout(8,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Datum:"));
        JDatePicker datePicker = new JDatePicker();
        UIConstants.formatComponentDialog(datePicker);
        center.add(datePicker);
        center.add(new JLabel("•  Čas:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Začátek:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Cíl:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Vzdálenost:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Vozidlo:"));
        JComboBox<Integer> vehicles = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(vehicles);
        center.add(vehicles);
        center.add(new JLabel("•  Cestující:"));
        JComboBox<Integer> passengers = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(passengers);
        center.add(passengers);
        center.add(new JLabel("•  Opakování:"));
        JComboBox<Integer> repetition = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(repetition);
        center.add(repetition);

        JPanel bottom = new JPanel();
        JButton create = new JButton("Vytvořit");
        UIConstants.formatComponentDialog(create);
        bottom.add(create);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(330,350);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}

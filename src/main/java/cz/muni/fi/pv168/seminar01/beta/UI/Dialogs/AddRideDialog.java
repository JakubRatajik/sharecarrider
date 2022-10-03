package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.JFrameWindow;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class AddRideDialog extends JDialog {

    public AddRideDialog() {
        initialize();
    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 30));
        center.setLayout(new GridLayout(10,2));
        center.add(new JLabel("•  Date:"));
        center.add(new JDatePicker());
        center.add(new JLabel("•  Time:"));
        center.add(new JTextField());
        center.add(new JLabel("•  Start:"));
        center.add(new JTextField());
        center.add(new JLabel("•  Finish:"));
        center.add(new JTextField());
        center.add(new JLabel("•  Distance:"));
        center.add(new JTextField());
        center.add(new JLabel("•  Vehicle:"));
        center.add(new JComboBox<Integer>());
        center.add(new JLabel("•  Passengers:"));
        center.add(new JComboBox<Integer>());
        center.add(new JLabel("•  Repetition:"));
        center.add(new JComboBox<Integer>());
        setLocationRelativeTo(JFrameWindow.getFrame());


        add(center, BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,600);
        setVisible(true);

    }


}

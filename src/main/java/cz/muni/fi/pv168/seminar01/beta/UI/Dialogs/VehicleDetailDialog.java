package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Kateřina Vácová
 */
public class VehicleDetailDialog extends DetailDialog{
    private Vehicle vehicle;

    public VehicleDetailDialog(Frame frame, String name, Vehicle vehicle) {
        super(frame, name, vehicle);
    }


    @Override
    public void onEditButton(JButton edit) {

    }

    @Override
    public void initializeCenter(JPanel center) {
        center.setLayout(new GridLayout(4, 2));
        UIConstants.formatWhiteTextBrownDialog((center));
        center.add(new JLabel("•  Značka:"));
        center.add(new JLabel(vehicle.getBrand()));
        center.add(new JLabel("•  Typ:"));
        center.add(new JLabel(vehicle.getType()));
        center.add(new JLabel("•  Kapacita:"));
        center.add(new JLabel(vehicle.getCapacityString()));
        center.add(new JLabel("•  Spotřeba:"));
        center.add(new JLabel(vehicle.getConsumption() + " l/100 km"));
        add(center, BorderLayout.CENTER);
        setSize(220,180);
    }

    @Override
    public void addAttribute(Object attribute) {
        vehicle = (Vehicle) attribute;
    }
}

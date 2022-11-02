package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public class AddVehicleDialog extends AddDialog {
    private JTextField licensePlate;
    private JTextField brand;
    private JTextField model;
    private JTextField capacity;
    private JTextField consumption;
    private JComboBox<FuelType> fuelType;

    public AddVehicleDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    @Override
    protected void setAttributes() {
        this.licensePlate = UIConstants.createTextField();
        this.brand = UIConstants.createTextField();
        this.model = UIConstants.createTextField();
        this.capacity = UIConstants.createTextField();
        this.consumption = UIConstants.createTextField();
        this.fuelType = new JComboBox<>();
        for (FuelType ft : FuelType.values()) {
            fuelType.addItem(ft);
        }
        UIConstants.formatComponentDialog(fuelType);
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(6, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  SPZ:"));
        center.add(this.licensePlate);
        center.add(new JLabel("•  Značka:"));
        center.add(this.brand);
        center.add(new JLabel("•  Typ:"));
        center.add(this.model);
        center.add(new JLabel("•  Počet míst:"));
        center.add(this.capacity);
        center.add(new JLabel("•  Spotřeba:"));
        center.add(this.consumption);
        center.add(new JLabel("•  Typ paliva:"));
        center.add(this.fuelType);

        setSize(300, 220);
    }

    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isValidAddVehicleInputValid()) {
                    return;
                }

                Vehicle vehicle = new Vehicle(
                        licensePlate.getText(),
                        brand.getText(),
                        model.getText(),
                        Integer.parseInt(capacity.getText().trim()),
                        Float.parseFloat(consumption.getText()),
                        (FuelType) fuelType.getSelectedItem());

                VehicleTableModel tableModel = (VehicleTableModel) DialogBase.getTableModel(TableCategory.VEHICLES);
                tableModel.addRow(vehicle);
                dispose();
            }
        });
    }

    private boolean isValidAddVehicleInputValid() {
        if (!Validator.isValidIntegerNumberInput(capacity.getText())) {
            new ErrorDialog(MainWindow.getFrame(), "Kapacita musí být celý číslo.");
            return false;
        }

        if (!Validator.isValidRealNumberInput(consumption.getText())) {
            new ErrorDialog(MainWindow.getFrame(), "Spotřeba musí být reální číslo.");
        }

        return true;
    }
}

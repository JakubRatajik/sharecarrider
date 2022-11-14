package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.UIConstants;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public class AddEditVehicleDialog extends AddEditDialog {
    private Vehicle vehicle = null;
    private JTextField licensePlate;
    private JTextField brand;
    private JTextField type;
    private JTextField capacity;
    private JTextField consumption;
    private JComboBox<FuelType> fuelType;

    public AddEditVehicleDialog(Frame frame, String name) {
        super(frame, name);
    }

    public AddEditVehicleDialog(Frame frame, String name, Vehicle vehicle) {
        super(frame, name, vehicle);
    }

    @Override
    protected void addAttribute(Object attribute) {
        this.vehicle = (Vehicle) attribute;
    }

    @Override
    protected void setAttributes() {
        this.licensePlate = UIConstants.createTextField();
        this.brand = UIConstants.createTextField();
        this.type = UIConstants.createTextField();
        this.capacity = UIConstants.createTextField();
        this.consumption = UIConstants.createTextField();
        this.fuelType = new JComboBox<>();
        for (FuelType ft : FuelType.values()) {
            fuelType.addItem(ft);
        }
        UIConstants.formatComponentDialog(fuelType);

        if (vehicle != null) {
            licensePlate.setText(vehicle.getLicensePlate());
            brand.setText(vehicle.getBrand());
            type.setText(vehicle.getType());
            capacity.setText(String.valueOf(vehicle.getCapacity()));
            consumption.setText(String.valueOf(vehicle.getConsumption()));
            fuelType.setSelectedItem(vehicle.getFuelType());
        }
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
        center.add(this.type);
        center.add(new JLabel("•  Počet míst:"));
        center.add(this.capacity);
        center.add(new JLabel("•  Spotřeba:"));
        center.add(this.consumption);
        center.add(new JLabel("•  Typ paliva:"));
        center.add(this.fuelType);

        setSize(300, 220);
    }

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
                VehicleTableModel tableModel = (VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES);
                vehicle.setLicensePlate(licensePlate.getText());
                vehicle.setBrand(brand.getText());
                vehicle.setType(type.getText());
                vehicle.setCapacity(Integer.parseInt(capacity.getText().trim()));
                vehicle.setConsumption(Float.parseFloat(consumption.getText()));
                vehicle.setFuelType((FuelType) fuelType.getSelectedItem());
                tableModel.updateRow(vehicle);
                dispose();
            }
        });
    }


    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!Validator.isValidAddVehicleInputValid()) {
                //    return;
                //}

                VehicleTableModel tableModel = (VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES);
                Vehicle vehicle = new Vehicle(
                        licensePlate.getText(),
                        brand.getText(),
                        type.getText(),
                        Integer.parseInt(capacity.getText().trim()),
                        Float.parseFloat(consumption.getText()),
                        (FuelType) fuelType.getSelectedItem());

                tableModel.addRow(vehicle);
                dispose();
            }
        });
    }


    //if (!Validator.isValidRealNumberInput(consumption.getText())) {
    //    new ErrorDialog(MainWindow.getFrame(), "Spotřeba musí být reální číslo.");
    //}

    //return true;
    //}
}

package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.EnumRendererForComboBox;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        this.licensePlate = UIUtilities.createTextField();
        this.brand = UIUtilities.createTextField();
        this.type = UIUtilities.createTextField();
        this.capacity = UIUtilities.createTextField();
        this.consumption = UIUtilities.createTextField();
        this.fuelType = new JComboBox<>();
        for (FuelType ft : FuelType.values()) {
            fuelType.addItem(ft);
        }
        UIUtilities.formatDefaultJComboBox(fuelType, new EnumRendererForComboBox());

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
        UIUtilities.formatWhiteTextBrownDialog(center);
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
            trimAllTextFields();
            if (!validateVehicleInput()) {
                return;
            }

            VehicleTableModel tableModel = (VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES);
            vehicle.setLicensePlate(licensePlate.getText());
            vehicle.setBrand(brand.getText());
            vehicle.setType(type.getText());
            vehicle.setCapacity(Integer.parseInt(capacity.getText()));
            vehicle.setConsumption(Float.parseFloat(consumption.getText()));
            vehicle.setFuelType((FuelType) fuelType.getSelectedItem());
            tableModel.updateRow(vehicle);
            dispose();
        });
    }

    private void trimAllTextFields() {
        List<JTextField> textFields = List.of(licensePlate, brand, type, capacity, consumption);

        for (JTextField textField : textFields) {
            textField.setText(textField.getText().trim());
        }
    }

    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(actionListener -> {
            trimAllTextFields();
            if (!validateVehicleInput()) {
                return;
            }

            VehicleTableModel tableModel = (VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES);
            Vehicle vehicle = new Vehicle(
                    licensePlate.getText(),
                    brand.getText(),
                    type.getText(),
                    Integer.parseInt(capacity.getText()),
                    Float.parseFloat(consumption.getText()),
                    (FuelType) fuelType.getSelectedItem());

            tableModel.addRow(vehicle);
            dispose();
        });
    }

    public boolean validateVehicleInput() {
        try {
            VehicleValidator.validateVehicle(licensePlate.getText(), brand.getText(), type.getText(), capacity.getText(), consumption.getText());
            return true;
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), e);
            return false;
        }
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.validation.PassengerValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AddEditPassengerDialog extends AddEditDialog {
    private Passenger passenger = null;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField phoneNumber;
    private Map<PassengerCategory, JCheckBox> categories;

    public AddEditPassengerDialog(Frame frame, String firstName) {
        super(frame, firstName);
    }

    public AddEditPassengerDialog(Frame frame, String firstName, Passenger passenger) {
        super(frame, firstName, passenger);
    }

    @Override
    protected void addAttribute(Object attribute) {
        passenger = (Passenger) attribute;
    }

    @Override
    public void initializeBottom(JPanel bottom) {
        if (passenger != null) {
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
            if (!validatePassengerInput()) {
                return;
            }

            PassengerTableModel tableModel = (PassengerTableModel) Shortcut.getTableModel(TableCategory.PASSENGERS);
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String phoneNumberText = phoneNumber.getText();

            passenger.setFirstName(firstNameText);
            passenger.setLastName(lastNameText);
            passenger.setPhoneNumber(phoneNumberText);
            passenger.setCategories(getSelectedCategories());
            tableModel.updateRow(passenger);
        });
    }

    @Override
    protected void setAttributes() {
        firstName = UIUtilities.createTextField();
        lastName = UIUtilities.createTextField();
        phoneNumber = UIUtilities.createTextField();
        categories = new HashMap<>();
        for (PassengerCategory category : PassengerCategory.values()) {
            categories.put(category, new JCheckBox(" " + category));
        }

        if (passenger != null) {
            firstName.setText(passenger.getFirstName());
            lastName.setText(passenger.getLastName());
            phoneNumber.setText(passenger.getPhoneNumber());
            for (PassengerCategory category : passenger.getCategories()) {
                categories.get(category).setSelected(true);
            }
        }
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(this.firstName);
        center.add(new JLabel("•  Příjmení:"));
        center.add(this.lastName);
        center.add(new JLabel("•  Telefon:"));
        center.add(this.phoneNumber);
        center.add(new JLabel("•  Kategorie:"));
        center.add(categories.get(PassengerCategory.WORK));
        center.add(new JLabel(" "));
        center.add(categories.get(PassengerCategory.FAMILY));
        center.add(new JLabel(" "));
        center.add(categories.get(PassengerCategory.FRIENDS));
        center.add(new JLabel(" "));
        center.add(categories.get(PassengerCategory.OTHER));
        setSize(330, 220);
    }

    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(actionListener -> {
            trimAllTextFields();
            if (!validatePassengerInput()) {
                return;
            }

            Passenger passenger = new Passenger(
                    firstName.getText(),
                    lastName.getText(),
                    phoneNumber.getText(),
                    getSelectedCategories());

            PassengerTableModel tableModel = (PassengerTableModel) Shortcut.getTableModel(TableCategory.PASSENGERS);
            tableModel.addRow(passenger);
            dispose();
        });
    }

    public boolean validatePassengerInput() {
        try {
            PassengerValidator.validatePassenger(firstName.getText(), lastName.getText(), phoneNumber.getText());
            return true;
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), e);
            return false;
        }
    }

    public Set<PassengerCategory> getSelectedCategories() {
        return categories.keySet().stream()
                .filter(key -> categories.get(key).isSelected())
                .collect(Collectors.toSet());
    }

    private void trimAllTextFields() {
        List<JTextField> textFields = List.of(firstName, lastName, phoneNumber);

        for (JTextField textField : textFields) {
            textField.setText(textField.getText().trim());
        }
    }
}

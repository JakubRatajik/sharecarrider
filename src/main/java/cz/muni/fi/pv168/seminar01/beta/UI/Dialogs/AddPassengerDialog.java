package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AddPassengerDialog extends AddDialog {

    private JTextField name;
    private JTextField surname;
    private JTextField phoneNumber;
    private Map<PassengerCategory,JCheckBox> categories;

    public AddPassengerDialog(Frame frame, String name) {
        super(frame, name);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }


    @Override
    protected void setAttributes() {
        name = UIConstants.createTextField();
        surname = UIConstants.createTextField();
        phoneNumber = UIConstants.createTextField();
        categories = new HashMap<>();
        for (PassengerCategory category : PassengerCategory.values()) {
            categories.put(category, new JCheckBox(" " + category));
        }
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(this.name);
        center.add(new JLabel("•  Příjmení:"));
        center.add(this.surname);
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
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<PassengerCategory> selectedCategories = categories.keySet().stream()
                        .filter(key -> categories.get(key).isSelected())
                        .collect(Collectors.toSet());

                try {
                    Passenger passenger = new Passenger(
                            name.getText(),
                            surname.getText(),
                            phoneNumber.getText(),
                            selectedCategories
                    );

                    PassengerTableModel tableModel = (PassengerTableModel) DialogBase.getTableModel(TableCategory.PASSENGERS);
                    tableModel.addRow(passenger);
                    dispose();
                } catch (IllegalArgumentException exception) {
                    new ErrorDialog(MainWindow.getFrame(), exception.getMessage());
                }
            }
        });
    }
}

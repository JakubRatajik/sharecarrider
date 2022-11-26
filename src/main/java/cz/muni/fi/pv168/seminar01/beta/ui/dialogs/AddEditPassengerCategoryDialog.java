package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;

public class AddEditPassengerCategoryDialog extends AddEditDialog{
    private PassengerCategory category = null;
    private JTextField name;

    public AddEditPassengerCategoryDialog(Frame frame, String name) {
        super(frame, name);
    }

    public AddEditPassengerCategoryDialog(Frame frame, String name, PassengerCategory category) {
        super(frame, name, category);
    }

    @Override
    public void initializeBottom(JPanel bottom) {
        if (category != null) {
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
        save.addActionListener(e -> {
            category.setName(name.getText());
            PassengerCategoryTableModel tableModel = (PassengerCategoryTableModel) Shortcut.getTableModel(TableCategory.PASSENGER_CATEGORY);
            tableModel.updateRow(category);
            dispose();
            new CategoryDetailDialog(MainWindow.getFrame(), "Detail kategorie", category);
        });
    }

    @Override
    protected void addAttribute(Object attribute) {
        category = (PassengerCategory) attribute;
    }

    @Override
    protected void setAttributes() {
        name = new JTextField();
        UIUtilities.formatDefaultComponent(name);

        if (category != null) {
            name.setText(category.getName());
        }
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(1, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Název:"));
        center.add(name);
        this.add(center);
        setSize(270, 100);
    }

    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(actionListener -> {
            PassengerCategory category = new PassengerCategory(name.getText());
            PassengerCategoryTableModel tableModel = (PassengerCategoryTableModel) Shortcut.getTableModel(TableCategory.PASSENGER_CATEGORY);
            tableModel.addRow(category);
            dispose();
        });
    }
}

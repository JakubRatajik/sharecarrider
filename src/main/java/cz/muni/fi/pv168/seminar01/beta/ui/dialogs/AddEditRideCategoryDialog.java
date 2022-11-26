package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditRideCategoryDialog extends AddEditDialog{
    private RideCategory category = null;
    private JTextField name;

    public AddEditRideCategoryDialog(Frame frame, String name) {
        super(frame, name);
    }

    public AddEditRideCategoryDialog(Frame frame, String name, Ride ride) {
        super(frame, name, ride);
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
            RideCategoryTableModel tableModel = (RideCategoryTableModel) Shortcut.getTableModel(TableCategory.RIDE_CATEGORY);
            tableModel.updateRow(category);
            dispose();
        });
    }

    @Override
    protected void addAttribute(Object attribute) {
        category = (RideCategory) attribute;
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
            RideCategory category = new RideCategory(name.getText());
            RideCategoryTableModel tableModel = (RideCategoryTableModel) Shortcut.getTableModel(TableCategory.RIDE_CATEGORY);
            tableModel.updateRow(category);
            dispose();
        });
    }
}

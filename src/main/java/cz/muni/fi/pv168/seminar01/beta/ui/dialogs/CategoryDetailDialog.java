package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class CategoryDetailDialog extends DetailDialog {
    private Category category;
    private JLabel name;

    public CategoryDetailDialog(Frame frame, String name, Category category) {
        super(frame, name, category);
    }


    @Override
    public void onEditButton(JButton edit) {
        edit.addActionListener(e -> {
            dispose();
            if (category instanceof PassengerCategory) {
                new AddEditPassengerCategoryDialog(MainWindow.getFrame(), "Upravit kategorii", (PassengerCategory) category);
            } else {
                new AddEditRideCategoryDialog(MainWindow.getFrame(), "Upravit kategorii", (RideCategory) category);
            }
        });
    }

    @Override
    public void initializeCenter(JPanel center) {
        name = new JLabel(category.getName());
        center.setLayout(new GridLayout(1, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("• Název:"));
        center.add(name);
        this.add(center);
        setSize(300, 120);
    }

    @Override
    public void addAttribute(Object attribute) {
        category = (Category) attribute;
    }
}

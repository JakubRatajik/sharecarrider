package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilterPassengersDialog extends SortFilterDialog {
    private JScrollPane categories;
    private JList<PassengerCategory> categoriesList;

    public FilterPassengersDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    public void setAttributes() {
        DefaultListModel<PassengerCategory> l1 = new DefaultListModel<>();
        java.util.List<PassengerCategory> categoriesL = (List<PassengerCategory>) Shortcut.getTableModel(TableCategory.PASSENGER_CATEGORY).getData();
        l1.addAll(categoriesL);
        categoriesList = new JList<PassengerCategory>(l1);
        categoriesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean hasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

                if (isSelected) {
                    component.setBackground(UIUtilities.MIDDLE_BROWN);
                }

                setText(value.toString());

                return component;
            }
        });
        UIUtilities.formatDefaultComponent(categoriesList);
        categoriesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        categories = new JScrollPane(categoriesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIUtilities.formatDefaultComponent(categories);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(1, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Kategorie:"));
        center.add(categories);
        this.add(center);
        setSize(300, 180);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }
}

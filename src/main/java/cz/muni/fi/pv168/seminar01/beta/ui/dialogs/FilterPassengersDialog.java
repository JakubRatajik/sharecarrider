package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class FilterPassengersDialog extends SortFilterDialog {
    private JScrollPane categories;
    private JList<PassengerCategory> categoryList;
    private static int[] selectedCategoryIndices = {};

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
        categoryList = new JList<>(l1);
        categoryList.setCellRenderer(new DefaultListCellRenderer() {
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
        UIUtilities.formatDefaultComponent(categoryList);
        categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        categories = new JScrollPane(categoryList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIUtilities.formatDefaultComponent(categories);

        for (int index : selectedCategoryIndices) {
            categoryList.addSelectionInterval(index, index);
        }
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
        ok.addActionListener(actionListener -> {
        List<PassengerCategory> selectedValuesList = categoryList.getSelectedValuesList();
        selectedCategoryIndices = categoryList.getSelectedIndices();

        StringBuilder sb = new StringBuilder("^.*$");
        for (PassengerCategory category: selectedValuesList) {
            sb.insert(1, String.format("(?=.*\\b%s\\b)", category.getName()));
        }

        RowFilter<ShareCarRiderTableModel<Passenger>, Integer> rf = RowFilter.regexFilter(sb.toString());
        ShareCarRiderTable table = Shortcut.getTable(TableCategory.PASSENGERS);
        TableRowSorter<ShareCarRiderTableModel<Passenger>> sorter
                = new TableRowSorter<>((ShareCarRiderTableModel<Passenger>) table.getModel());
        sorter.setRowFilter(rf);
        table.setRowSorter(sorter);

        dispose();
        });
    }
}

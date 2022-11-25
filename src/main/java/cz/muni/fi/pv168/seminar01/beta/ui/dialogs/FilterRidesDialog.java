package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilterRidesDialog extends FilterDialog {
    private JCheckBox dateFilter;
    private JCheckBox distanceFilter;
    private JDatePicker dateFrom;
    private JDatePicker dateTo;
    private JTextField distanceFrom;
    private JTextField distanceTo;
    private JScrollPane categories;
    private JList<RideCategory> categoriesList;

    public FilterRidesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {
    }

    public void setAttributes() {
        dateFilter = new JCheckBox(" Datum");
        distanceFilter = new JCheckBox(" Vzdálenost");
        dateFrom = new JDatePicker();
        UIUtilities.formatDefaultComponent(dateFrom);
        dateTo = new JDatePicker();
        UIUtilities.formatDefaultComponent(dateTo);
        distanceFrom = UIUtilities.createTextField();
        distanceTo = UIUtilities.createTextField();

        DefaultListModel<RideCategory> l1 = new DefaultListModel<>();
        List<RideCategory> categoriesL = (List<RideCategory>) Shortcut.getTableModel(TableCategory.RIDE_CATEGORY).getData();
        l1.addAll(categoriesL);
        categoriesList = new JList<RideCategory>(l1);
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
        center.setLayout(new GridLayout(7, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        String paragraph = "      ";
        center.add(dateFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(dateFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(dateTo);
        center.add(distanceFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(distanceFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(distanceTo);
        center.add(new JLabel("  •  Kategorie"));
        center.add(categories);
        this.add(center);
        setSize(450, 430);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }

}

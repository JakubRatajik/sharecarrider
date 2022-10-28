package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FilterVehiclesDialog extends FilterDialog {
    private JCheckBox capacityFilter;
    private JCheckBox consumptionFilter;
    private JTextField capacityFrom;
    private JTextField capacityTo;
    private JTextField consumptionFrom;
    private JTextField consumptionTo;
    private JComboBox<Integer> brand;

    public FilterVehiclesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    public void setAttributes() {
        capacityFilter = new JCheckBox("  Kapacita:");
        consumptionFilter = new JCheckBox("  Spotřeba:");
        capacityFrom = UIConstants.createTextField();
        capacityTo = UIConstants.createTextField();
        consumptionFrom = UIConstants.createTextField();
        consumptionTo = UIConstants.createTextField();
        brand = new JComboBox<>();
        UIConstants.formatComponentDialog(brand);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        String paragraph = "      ";
        center.add(capacityFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(capacityFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(capacityTo);
        center.add(consumptionFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(consumptionFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(consumptionTo);
        center.add(new JLabel("  •  Značka"));
        center.add(brand);
        this.add(center);
        setSize(450, 350);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }
}

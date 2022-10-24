package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.FilterDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FilterVehiclesDialog extends FilterDialog {
    private JCheckBox capacityFilter;
    private JCheckBox consumptionFilter;
    private JSlider capacityFrom;
    private JSlider capacityTo;
    private JSlider consumptionFrom;
    private JSlider consumptionTo;
    private JComboBox<Integer> brand;

    public FilterVehiclesDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void setAttributes() {
        capacityFilter = new JCheckBox("  Kapacita:");
        consumptionFilter = new JCheckBox("  Spotřeba:");
        capacityFrom = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
        capacityFrom.setMajorTickSpacing(3);
        capacityFrom.setMinorTickSpacing(1);
        capacityFrom.setPaintTicks(true);
        capacityFrom.setPaintLabels(true);
        capacityTo = new JSlider(JSlider.HORIZONTAL, 0, 15, 12);
        capacityTo.setMajorTickSpacing(3);
        capacityTo.setMinorTickSpacing(1);
        capacityTo.setPaintTicks(true);
        capacityTo.setPaintLabels(true);
        consumptionFrom = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        consumptionFrom.setMajorTickSpacing(4);
        consumptionFrom.setMinorTickSpacing(1);
        consumptionFrom.setPaintTicks(true);
        consumptionFrom.setPaintLabels(true);
        consumptionTo = new JSlider(JSlider.HORIZONTAL, 0, 20, 20);
        consumptionTo.setMajorTickSpacing(4);
        consumptionTo.setMinorTickSpacing(1);
        consumptionTo.setPaintTicks(true);
        consumptionTo.setPaintLabels(true);
        brand = new JComboBox<>();
        UIConstants.formatComponentDialog(brand);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7,2));
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
        setSize(550, 400);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }
}

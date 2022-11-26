package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class SortVehiclesDialog extends SortFilterDialog{
    private JRadioButton capacityMinMax;
    private JRadioButton capacityMaxMin;
    private JRadioButton consumptionMinMax;
    private JRadioButton consumptionMaxMin;

    public SortVehiclesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {
    }

    @Override
    protected void onOkButton(JButton ok) {
        //TODO - set sorting on ok button
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(6, 1));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Spotřeba:"));
        center.add(consumptionMinMax);
        center.add(consumptionMaxMin);
        center.add(new JLabel("•  Kapacita:"));
        center.add(capacityMinMax);
        center.add(capacityMaxMin);
        this.add(center);
        setSize(280, 200);
    }

    @Override
    protected void setAttributes() {
        capacityMaxMin = new JRadioButton("Od nejvíce míst k nejméně místům");
        capacityMinMax = new JRadioButton("Od nejméně míst k nejvíce místům");
        consumptionMinMax = new JRadioButton("Od nejnižší k nejvyšší");
        consumptionMaxMin = new JRadioButton("Od nejvyšší k nejnižší");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(capacityMaxMin);
        buttonGroup.add(capacityMinMax);
        buttonGroup.add(consumptionMaxMin);
        buttonGroup.add(consumptionMinMax);
    }
}

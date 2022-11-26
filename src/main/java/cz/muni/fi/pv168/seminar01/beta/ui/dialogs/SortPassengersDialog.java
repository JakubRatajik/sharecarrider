package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class SortPassengersDialog extends SortFilterDialog{
    private JRadioButton nameMinMax;
    private JRadioButton nameMaxMin;
    private JRadioButton surnameMinMax;
    private JRadioButton surnameMaxMin;

    public SortPassengersDialog(Frame frame, String name) {
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
        center.add(new JLabel("•  Jméno"));
        center.add(nameMinMax);
        center.add(nameMaxMin);
        center.add(new JLabel("•  Příjmení"));
        center.add(surnameMinMax);
        center.add(surnameMaxMin);
        this.add(center);
        setSize(280, 200);
    }

    @Override
    protected void setAttributes() {
        nameMinMax = new JRadioButton("A - Z");
        nameMaxMin = new JRadioButton("Z - A");
        surnameMinMax = new JRadioButton("A - Z");
        surnameMaxMin = new JRadioButton("Z - A");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nameMaxMin);
        buttonGroup.add(nameMinMax);
        buttonGroup.add(surnameMaxMin);
        buttonGroup.add(surnameMinMax);
    }
}

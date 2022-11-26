package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class SortRidesDialog extends SortFilterDialog {
    private JRadioButton dateMinMax;
    private JRadioButton dateMaxMin;
    private JRadioButton distanceMinMax;
    private JRadioButton distanceMaxMin;

    public SortRidesDialog(Frame frame, String name) {
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
        center.add(new JLabel("•  Datum"));
        center.add(dateMinMax);
        center.add(dateMaxMin);
        center.add(new JLabel("•  Vzdálenost"));
        center.add(distanceMinMax);
        center.add(distanceMaxMin);
        this.add(center);
        setSize(280, 200);
    }

    @Override
    protected void setAttributes() {
        dateMinMax = new JRadioButton("Od nejnovějších k nejstarším");
        dateMaxMin = new JRadioButton("Od nejstarších k nejnovějším");
        distanceMinMax = new JRadioButton("Od nejkratších k nejdelším");
        distanceMaxMin = new JRadioButton("Od nejdelších k nejkratším");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dateMaxMin);
        buttonGroup.add(dateMinMax);
        buttonGroup.add(distanceMaxMin);
        buttonGroup.add(distanceMinMax);
    }
}

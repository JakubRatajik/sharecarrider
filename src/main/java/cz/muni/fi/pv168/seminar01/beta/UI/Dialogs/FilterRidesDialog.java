package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class FilterRidesDialog extends FilterDialog {
    private JCheckBox dateFilter;
    private JCheckBox distanceFilter;
    private JDatePicker dateFrom;
    private JDatePicker dateTo;
    private JSlider distanceFrom;
    private JSlider distanceTo;
    // TODO categories
    private int categoriesCount = 0; //temporary

    public FilterRidesDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void setAttributes() {
        dateFilter = new JCheckBox(" Datum");
        distanceFilter = new JCheckBox(" Vzdálenost");
        dateFrom = new JDatePicker();
        UIConstants.formatComponentDialog(dateFrom);
        dateTo = new JDatePicker();
        UIConstants.formatComponentDialog(dateTo);
        distanceFrom = new JSlider(JSlider.HORIZONTAL, 0, 500, 0);
        distanceFrom.setMajorTickSpacing(100);
        distanceFrom.setMinorTickSpacing(10);
        distanceFrom.setPaintTicks(true);
        distanceFrom.setPaintLabels(true);
        distanceTo = new JSlider(JSlider.HORIZONTAL, 0, 500, 500);
        distanceTo.setMajorTickSpacing(100);
        distanceTo.setMinorTickSpacing(10);
        distanceTo.setPaintTicks(true);
        distanceTo.setPaintLabels(true);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7 + categoriesCount,2));
        UIConstants.formatWhiteTextBrownDialog(center);
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
        this.add(center);
        setSize(550,400);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }

}

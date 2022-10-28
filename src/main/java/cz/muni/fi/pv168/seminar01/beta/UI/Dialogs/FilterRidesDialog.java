package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class FilterRidesDialog extends FilterDialog {
    // TODO categories
    private final int categoriesCount = 0; //temporary
    private JCheckBox dateFilter;
    private JCheckBox distanceFilter;
    private JDatePicker dateFrom;
    private JDatePicker dateTo;
    private JTextField distanceFrom;
    private JTextField distanceTo;

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
        UIConstants.formatComponentDialog(dateFrom);
        dateTo = new JDatePicker();
        UIConstants.formatComponentDialog(dateTo);
        distanceFrom = UIConstants.createTextField();
        distanceTo = UIConstants.createTextField();
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7 + categoriesCount, 2));
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
        setSize(450, 350);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }

}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FilterPassengersDialog extends FilterDialog{
    private JCheckBox work;
    private JCheckBox family;
    private JCheckBox friends;
    private JCheckBox other;

    public FilterPassengersDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void setAttributes() {
        work = new JCheckBox("  Práce");
        family = new JCheckBox("  Rodina");
        friends = new JCheckBox("  Přátelé");
        other = new JCheckBox("  Další");
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Kategorie:"));
        center.add(work);
        center.add(family);
        center.add(friends);
        center.add(other);
        this.add(center);
        setSize(300, 180);
    }

    public void onOkButton(JButton ok) {
        //TODO - set filter on ok button
    }
}

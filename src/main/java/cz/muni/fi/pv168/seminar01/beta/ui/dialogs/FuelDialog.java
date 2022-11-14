package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public class FuelDialog extends DialogBase {
    private JTextField nafta;
    private JTextField benzin;
    private JTextField lpg;
    private JTextField cng;
    private JTextField elektro;

    public FuelDialog(Frame frame) {
        super(frame, "Ceny paliv");
    }

    public void initializeBottom(JPanel bottom) {
        JButton create = new JButton("Zadat");
        UIUtilities.formatComponentDialog(create);
        UIUtilities.formatBeigeTextBrownDialog(create);
        bottom.add(create);
    }

    public void initializeCenter(JPanel center) {
        setAttributes();
        center.setLayout(new GridLayout(5, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Nafta (litr)"));
        center.add(nafta);
        center.add(new JLabel("•  Benzín (litr)"));
        center.add(benzin);
        center.add(new JLabel("•  LPG (litr)"));
        center.add(lpg);
        center.add(new JLabel("•  CNG (litr)"));
        center.add(cng);
        center.add(new JLabel("•  Elektřina (kWh)"));
        center.add(elektro);
        this.add(center);
        setSize(330, 220);
    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    public void setAttributes() {
        nafta = UIUtilities.createTextField();
        benzin = UIUtilities.createTextField();
        lpg = UIUtilities.createTextField();
        cng = UIUtilities.createTextField();
        elektro = UIUtilities.createTextField();
    }


}

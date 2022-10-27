package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

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
        UIConstants.formatComponentDialog(create);
        UIConstants.formatBeigeTextBrownDialog(create);
        bottom.add(create);
    }

    public void initializeCenter(JPanel center) {
        setAttributes();
        center.setLayout(new GridLayout(5,2));
        UIConstants.formatWhiteTextBrownDialog(center);
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
        setSize(330,220);
    }

    public void setAttributes() {
        nafta = UIConstants.createTextField();
        benzin = UIConstants.createTextField();
        lpg = UIConstants.createTextField();
        cng = UIConstants.createTextField();
        elektro = UIConstants.createTextField();
    }


}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import javax.swing.*;
import java.awt.*;

public class FuelDialog extends JDialog {
    JLabel label1 = new JLabel("Diesel price (l)");
    JLabel label2 = new JLabel("Gas price (l)");
    JLabel label3 = new JLabel("LPG price (l)");
    JLabel label4 = new JLabel("CNG price (l)");
    JLabel label5 = new JLabel("Electricity price (kWH)");

    JTextField text1 = new JTextField(10);
    JTextField text2 = new JTextField(10);
    JTextField text3 = new JTextField(10);
    JTextField text4 = new JTextField(10);
    JTextField text5 = new JTextField(10);
    public FuelDialog(Frame owner) {
        super(owner);
        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(text1);
        panel.add(new JToolBar.Separator());
        panel.add(label2);
        panel.add(text2);
        panel.add(label3);
        panel.add(text3);
        panel.add(label4);
        panel.add(text4);
        panel.add(label5);
        panel.add(text5);
        this.add(panel);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600,800);
        setVisible(true);
    }


}

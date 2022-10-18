package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class FuelDialog extends JDialog {


    public FuelDialog(Frame owner) {
        super(owner, "Ceny paliv");
        JPanel panel = new JPanel();
        setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        panel.setLayout(new GridLayout(5,2));
        UIConstants.formatWhiteTextBrownDialog(panel);

        JLabel label1 = new JLabel("•  Nafta (litr)");
        JLabel label2 = new JLabel("•  Benzín (litr)");
        JLabel label3 = new JLabel("•  LPG (litr)");
        JLabel label4 = new JLabel("•  CNG (litr)");
        JLabel label5 = new JLabel("•  Elektřina (kWh)");

        JTextField text1 = UIConstants.createTextField();
        JTextField text2 = UIConstants.createTextField();
        JTextField text3 = UIConstants.createTextField();
        JTextField text4 = UIConstants.createTextField();
        JTextField text5 = UIConstants.createTextField();

        panel.add(label1);
        panel.add(text1);
        //panel.add(new JToolBar.Separator());
        panel.add(label2);
        panel.add(text2);
        panel.add(label3);
        panel.add(text3);
        panel.add(label4);
        panel.add(text4);
        panel.add(label5);
        panel.add(text5);
        this.add(panel);

        JPanel bottom = new JPanel();
        UIConstants.formatWhiteTextBrownDialog(bottom);
        JButton create = new JButton("Zadat");
        UIConstants.formatComponentDialog(create);
        UIConstants.formatBeigeTextBrownDialog(create);
        bottom.add(create);

        add(bottom, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setSize(330,220);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPassengerDialog extends AddDialog {

    private JTextField name;
    private JTextField surname;
    private JTextField phoneNumber;
    private JCheckBox workCategory;
    private JCheckBox familyCategory;
    private JCheckBox friendsCategory;
    private JCheckBox otherCatgory;

    public AddPassengerDialog(Frame frame, String name) {
        super(frame, name);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }


    @Override
    protected void setAttributes() {
        name = UIConstants.createTextField();
        surname = UIConstants.createTextField();
        phoneNumber = UIConstants.createTextField();
        workCategory = new JCheckBox(" Práce");
        familyCategory = new JCheckBox(" Rodina");
        friendsCategory = new JCheckBox(" Přátelé");
        otherCatgory = new JCheckBox(" Jiné");
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(7, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(this.name);
        center.add(new JLabel("•  Příjmení:"));
        center.add(this.surname);
        center.add(new JLabel("•  Telefon:"));
        center.add(this.phoneNumber);
        center.add(new JLabel("•  Kategorie:"));
        center.add(workCategory);
        center.add(new JLabel(" "));
        center.add(familyCategory);
        center.add(new JLabel(" "));
        center.add(friendsCategory);
        center.add(new JLabel(" "));
        center.add(otherCatgory);
        setSize(330, 220);
    }

    @Override
    protected void onCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO - there need to be save action, but is not implemented yet
                dispose();
            }
        });
    }
}

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
    private JComboBox<Integer> category;

    public AddPassengerDialog(Frame frame, String name) {
        super(frame, name);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }


    @Override
    protected void setAttributes() {
        this.name = UIConstants.createTextField();
        this.surname = UIConstants.createTextField();
        this.phoneNumber = UIConstants.createTextField();
        this.category = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(category);
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(4, 2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(this.name);
        center.add(new JLabel("•  Příjmení:"));
        center.add(this.surname);
        center.add(new JLabel("•  Telefon:"));
        center.add(this.phoneNumber);
        center.add(new JLabel("•  Kategorie:"));
        center.add(this.category);
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

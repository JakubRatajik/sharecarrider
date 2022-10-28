package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public class AddVehicleDialog extends AddDialog{
    private JTextField brand;
    private JTextField model;
    private JTextField seats;
    private JTextField consumption;

    public AddVehicleDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void setAttributes() {
        this.brand = UIConstants.createTextField();
        this.model = UIConstants.createTextField();
        this.seats = UIConstants.createTextField();
        this.consumption = UIConstants.createTextField();
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(4,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Značka:"));
        center.add(this.brand);
        center.add(new JLabel("•  Typ:"));
        center.add(this.model);
        center.add(new JLabel("•  Počet míst:"));
        center.add(this.seats);
        center.add(new JLabel("•  Spotřeba:"));
        center.add(this.consumption);
        setSize(300,220);
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

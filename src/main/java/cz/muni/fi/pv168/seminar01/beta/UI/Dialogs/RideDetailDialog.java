package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Katerina Vacova
 */

public class RideDetailDialog extends JDialog {

    public RideDetailDialog(Frame frame, String name) {
        super(frame, name);
        initialize();
    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setLayout(new GridLayout(8,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Datum:"));
        center.add(new JLabel("•  Čas:"));
        center.add(new JLabel("•  Začátek:"));
        center.add(new JLabel("•  Cíl:"));
        center.add(new JLabel("•  Vzdálenost:"));
        center.add(new JLabel("•  Vozidlo:"));
        center.add(new JLabel("•  Cestující:"));
        center.add(new JLabel("•  Kategorie:"));
        center.add(new JLabel("•  Cena:"));
        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton edit = new JButton("Upravit");
        /*
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xxx
            }
        }); */
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        UIConstants.formatComponentDialog(edit);
        UIConstants.formatComponentDialog(ok);
        bottom.add(edit);
        bottom.add(ok);
        bottom.setBackground(UIConstants.WHITE);
        add(bottom, BorderLayout.SOUTH);

        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(330, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

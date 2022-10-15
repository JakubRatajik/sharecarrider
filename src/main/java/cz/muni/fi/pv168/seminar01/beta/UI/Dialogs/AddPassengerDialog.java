package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

public class AddPassengerDialog extends JDialog {

    public AddPassengerDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setLayout(new GridLayout(4,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Příjmení:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Telefon:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Kategorie:"));
        JComboBox<Integer> categories = new JComboBox<Integer>();
        UIConstants.formatComponentDialog(categories);
        center.add(categories);
        JPanel bottom = new JPanel();
        JButton create = new JButton("Vytvořit");
        UIConstants.formatComponentDialog(create);
        bottom.add(create);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(330,220);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}

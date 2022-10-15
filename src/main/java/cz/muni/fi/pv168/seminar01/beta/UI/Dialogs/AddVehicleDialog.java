package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jan Macecek
 */
public class AddVehicleDialog extends JDialog{

    public AddVehicleDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        center.setLayout(new GridLayout(4,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Značka:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Typ:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Počet míst:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Spotřeba:"));
        center.add(UIConstants.createTextField());

        JPanel bottom = new JPanel();
        UIConstants.formatWhiteTextBrownDialog(bottom);
        JButton create = new JButton("Vytvořit");
        UIConstants.formatComponentDialog(create);
        UIConstants.formatBeigeTextBrownDialog(create);
        bottom.add(create);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        setResizable(false);
        setSize(300,220);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}

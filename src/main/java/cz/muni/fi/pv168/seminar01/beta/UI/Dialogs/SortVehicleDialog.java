package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jan Macecek
 */
public class SortVehicleDialog extends JDialog {

    public SortVehicleDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setBackground(UIConstants.WHITE);
        JPanel bottom = new JPanel();
        JButton cancel = new JButton("Cancel");
        JButton ok = new JButton("Ok");
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(ok);
        bottom.add(cancel);
        bottom.add(ok);
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

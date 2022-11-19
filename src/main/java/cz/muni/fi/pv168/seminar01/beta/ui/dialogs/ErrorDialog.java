package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author Kateřina Vácová
 */
public class ErrorDialog extends JDialog {

    public ErrorDialog(Frame frame, String errorMessage) {
        super(frame, "Chyba");

        JPanel center = new JPanel();
        setLayout(new BorderLayout());

        JLabel message = new JLabel(errorMessage);
        center.add(message);
        this.add(center);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> dispose());
        UIUtilities.formatDefaultComponent(ok);
        bottom.add(ok);
        bottom.setBackground(UIUtilities.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(300, 150);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}

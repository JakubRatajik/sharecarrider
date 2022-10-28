package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

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
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JLabel message = new JLabel(errorMessage);
        center.add(message);
        this.add(center);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> dispose());
        UIConstants.formatComponentDialog(ok);
        bottom.add(ok);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(300, 280);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}

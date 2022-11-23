package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @author Kateřina Vácová
 */
public class ErrorDialog extends JDialog {
    public ErrorDialog(Frame frame, Exception exception) {
        this(frame, exception.getMessage());
    }

    public ErrorDialog(Frame frame, String errorMessage) {
        super(frame, "Chyba");

        JPanel center = new JPanel();
        setLayout(new BorderLayout());

        JTextArea message = new JTextArea(errorMessage);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setSize(270, 130);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setEditable(false);
        message.setBackground(Color.WHITE);
        center.setBackground(Color.WHITE);
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

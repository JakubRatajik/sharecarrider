package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public class TemporaryDialog extends JDialog {

    public TemporaryDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setBackground(UIConstants.WHITE);
        JPanel bottom = new JPanel();
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JButton ok = new JButton("Ok");
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(ok);
        bottom.add(cancel);
        bottom.add(ok);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(330,220);
        setLocationRelativeTo(null);

        setVisible(true);

    }
}

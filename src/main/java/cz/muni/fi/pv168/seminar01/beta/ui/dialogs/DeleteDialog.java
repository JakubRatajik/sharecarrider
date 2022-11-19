package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.ActionListenerProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {

    public DeleteDialog(Frame frame, String name, TableCategory category, int[] rows) {
        super(frame, name);
        initialize(category, rows);

    }

    private void initialize(TableCategory category, int[] rows) {
        JPanel bottom = new JPanel();
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JButton ok = new JButton("Ok");
        ok.addActionListener(ActionListenerProvider.deleteRow(category, rows, this));
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(ok);
        bottom.add(cancel);
        bottom.add(ok);
        bottom.setBackground(UIUtilities.WHITE);
        add(bottom, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(200, 80);
        setLocationRelativeTo(null);

        setVisible(true);

    }
}

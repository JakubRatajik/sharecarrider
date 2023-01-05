package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.ActionListenerProvider;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import javax.swing.*;
import java.awt.*;

public class DeleteDialog extends JDialog {

    public DeleteDialog(Frame frame, String name, TableCategory category, int[] rows) {
        super(frame, name);
        initialize(category, rows);

    }

    private void initialize(TableCategory category, int[] rows) {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        JTextArea message = new JTextArea("Opravdu si přejete smazat vybrané položky?\nTato akce je nevratná.");
        message.setEditable(false);
        center.add(message);
        message.setBackground(Color.WHITE);
        center.setBackground(Color.WHITE);

        JPanel bottom = new JPanel();
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton ok = new JButton("Ok");
        if (CommonElementSupplier.getTable(category).getSelectedRowCount() == 0) {
            ok.setEnabled(false);
        }
        ok.addActionListener(ActionListenerProvider.deleteRow(category, rows, this));
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(ok);
        bottom.add(cancel);
        bottom.add(ok);
        bottom.setBackground(UIUtilities.WHITE);
        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(280, 120);
        setLocationRelativeTo(null);

        setVisible(true);

    }
}

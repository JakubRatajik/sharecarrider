package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public abstract class AddDialog extends JDialog {


    public AddDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        setAttributes();

        loadData(center);

        JPanel bottom = new JPanel();
        JButton create = new JButton("Vytvořit");

        onCreateButton(create);

        UIConstants.formatComponentDialog(create);
        bottom.add(create);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected abstract void setAttributes();

    protected abstract void loadData(JPanel center);
    protected abstract void onCreateButton(JButton create);





}

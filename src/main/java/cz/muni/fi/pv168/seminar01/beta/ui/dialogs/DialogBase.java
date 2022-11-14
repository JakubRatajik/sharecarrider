package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * author @Kateřina Vácová
 */

public abstract class DialogBase extends JDialog {

    public DialogBase(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    public DialogBase(Frame frame, String name, Object attribute) {
        super(frame, name);
        addAttribute(attribute);
        initialize();
    }

    public void initialize() {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        initializeCenter(center);

        JPanel bottom = new JPanel();
        bottom.setBackground(UIUtilities.WHITE);
        initializeBottom(bottom);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected abstract void initializeBottom(JPanel bottom);

    protected abstract void initializeCenter(JPanel center);

    protected abstract void addAttribute(Object attribute);
}

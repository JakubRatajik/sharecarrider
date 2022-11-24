package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents both add and edit dialogs.
 * If it is called with two attributes only, it acts like an add dialog.
 * With three attributes, it acts like an edit dialog.
 */
public abstract class AddEditDialog extends DialogBase {


    public AddEditDialog(Frame frame, String name) {
        super(frame, name);
    }

    public AddEditDialog(Frame frame, String name, Object attribute) {
        super(frame, name, attribute);
    }

    public abstract void initializeBottom(JPanel bottom);

    public void initializeCenter(JPanel center) {
        setAttributes();
        initializeContent(center);
    }

    /**
     * sets all attributes correct type
     */
    protected abstract void setAttributes();

    /**
     * create dialog content that differs in each dialog
     *
     * @param center JPanel, that will be filled with data
     */
    protected abstract void initializeContent(JPanel center);

    /**
     * set correct ActionListener
     * TODO - will be bind to ActionListenerProvider
     *
     * @param create button that need to have ActionListener (Create button)
     */
    protected abstract void onCreateButton(JButton create);

}


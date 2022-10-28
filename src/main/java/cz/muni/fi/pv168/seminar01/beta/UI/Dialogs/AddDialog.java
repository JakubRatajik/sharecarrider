package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jan Macecek
 */
public abstract class AddDialog extends DialogBase {


    public AddDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton create = new JButton("Vytvořit");
        onCreateButton(create);
        UIConstants.formatComponentDialog(create);
        bottom.add(create);
    }

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

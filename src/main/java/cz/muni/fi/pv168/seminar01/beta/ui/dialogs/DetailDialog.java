package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;

public abstract class DetailDialog extends DialogBase {

    public DetailDialog(Frame frame, String name, Object attribute) {
        super(frame, name, attribute);
    }

    public void initializeBottom(JPanel bottom) {
        JButton edit = new JButton("Upravit");
        onEditButton(edit);
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> dispose());
        UIUtilities.formatDefaultComponent(edit);
        UIUtilities.formatDefaultComponent(ok);
        bottom.add(edit);
        bottom.add(ok);
    }

    public abstract void onEditButton(JButton edit);

    @Override
    public abstract void initializeCenter(JPanel center);

    public abstract void addAttribute(Object attribute);

}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

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
        UIConstants.formatComponentDialog(edit);
        UIConstants.formatComponentDialog(ok);
        bottom.add(edit);
        bottom.add(ok);
    }

    public abstract void onEditButton(JButton edit);

    @Override
    public abstract void initializeCenter(JPanel center);

    public abstract void addAttribute(Object attribute);

}

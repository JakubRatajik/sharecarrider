package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.UIConstants;

import javax.swing.*;
import java.awt.*;

public abstract class FilterDialog extends DialogBase {

    public FilterDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton ok = new JButton("Ok");
        onOkButton(ok);
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(ok);
        bottom.add(cancel);
        bottom.add(ok);
    }

    @Override
    public void initializeCenter(JPanel center) {
        setAttributes();
        initializeContent(center);
    }

    protected abstract void onOkButton(JButton ok);

    protected abstract void initializeContent(JPanel center);

    protected abstract void setAttributes();

}

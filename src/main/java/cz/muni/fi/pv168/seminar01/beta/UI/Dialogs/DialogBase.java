package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * author @Kateřina Vácová
 */

public abstract class DialogBase extends JDialog {

    public DialogBase(Frame frame, String name) {
        super(frame, name);
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        initializeCenter(center);

        JPanel bottom = new JPanel();
        bottom.setBackground(UIConstants.WHITE);
        initializeBottom(bottom);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected abstract void initializeBottom(JPanel bottom);

    protected abstract void initializeCenter(JPanel center);


    public ShareCarRiderTableModel getTableModel(TableCategory tableCategory) {
        return switch (tableCategory) {
            case PASSENGERS -> (ShareCarRiderTableModel) MainWindow.getPassengersTabFrame().getTable().getModel();
            case RIDES -> (ShareCarRiderTableModel) MainWindow.getRidesTabFrame().getTable().getModel();
            case VEHICLES -> (ShareCarRiderTableModel) MainWindow.getVehiclesTabFrame().getTable().getModel();
        };
    }
}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.JFrameWindow;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jan Macecek
 */
public class AddVehicleDialog extends JDialog{

    public AddVehicleDialog(Frame frame, String name) {
        super(frame, name);
        initialize();

    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        center.setLayout(new GridLayout(4,2));
        UIConstants.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Brand:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Type:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Capacity:"));
        center.add(UIConstants.createTextField());
        center.add(new JLabel("•  Consumption:"));
        center.add(UIConstants.createTextField());

        JPanel bottom = new JPanel();
        UIConstants.formatWhiteTextBrownDialog(bottom);
        JButton create = new JButton("Create");
        UIConstants.formatComponentDialog(create);
        UIConstants.formatBeigeTextBrownDialog(create);
        bottom.add(create);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(JFrameWindow.getVehicles().getPlus());

        setResizable(false);
        setSize(300,220);
        setVisible(true);

    }


}

package cz.muni.fi.pv168.seminar01.beta.UI.Dialogs;

import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class FilterRidesDialog extends JDialog {
    int categoriesCount = 0;

    public FilterRidesDialog(Frame frame, String name) {
        super(frame, name);
        initialize();
    }

    private void initialize() {
        JPanel center = new JPanel();
        setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        center.setBackground(UIConstants.WHITE);

        center.setLayout(new GridLayout(7 + categoriesCount,2));
        UIConstants.formatWhiteTextBrownDialog(center);

        String paragraph = "      ";
        JLabel empty1 = new JLabel(" ");
        JLabel empty2 = new JLabel(" ");
        JCheckBox date = new JCheckBox(" Datum");
        JLabel dateFrom = new JLabel(paragraph + "•  Od:");
        JLabel dateTo = new JLabel(paragraph + "•  Do:");
        JCheckBox distance = new JCheckBox(" Vzdálenost");
        JLabel distanceFrom = new JLabel(paragraph + "•  Od:");
        JLabel distanceTo = new JLabel(paragraph + "•  Do:");
        JLabel categories = new JLabel("  •  Kategorie");

        JDatePicker dateFromPicker = new JDatePicker();
        UIConstants.formatComponentDialog(dateFromPicker);
        JDatePicker dateToPicker = new JDatePicker();
        UIConstants.formatComponentDialog(dateToPicker);
        JSlider distanceFromSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 50);
        distanceFromSlider.setMajorTickSpacing(100);
        distanceFromSlider.setMinorTickSpacing(10);
        distanceFromSlider.setPaintTicks(true);
        distanceFromSlider.setPaintLabels(true);
        JSlider distanceToSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 50);
        distanceToSlider.setMajorTickSpacing(100);
        distanceToSlider.setMinorTickSpacing(10);
        distanceToSlider.setPaintTicks(true);
        distanceToSlider.setPaintLabels(true);

        center.add(date);
        center.add(empty1);
        center.add(dateFrom);
        center.add(dateFromPicker);
        center.add(dateTo);
        center.add(dateToPicker);
        center.add(distance);
        center.add(empty2);
        center.add(distanceFrom);
        center.add(distanceFromSlider);
        center.add(distanceTo);
        center.add(distanceToSlider);
        center.add(categories);
        this.add(center);


        JPanel bottom = new JPanel();
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton ok = new JButton("Ok");
        UIConstants.formatComponentDialog(cancel);
        UIConstants.formatComponentDialog(ok);
        bottom.add(cancel);
        bottom.add(ok);
        bottom.setBackground(UIConstants.WHITE);

        add(bottom, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setSize(550,400);
        setLocationRelativeTo(null);

        setVisible(true);

    }
}

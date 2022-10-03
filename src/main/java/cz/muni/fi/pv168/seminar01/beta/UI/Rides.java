package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rides {

    private final JPanel main;

    public Rides() {
        main = new JPanel();
        initialize();
    }

    public JPanel setTop() {
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddRideDialog();
            }
        });
        plus.setBackground(UIConstants.OCHER);
        plus.setFont(UIConstants.fTab);
        plus.setForeground(UIConstants.TEXT_BROWN);
        JButton sortBy = new JButton("Sort by");
        JButton filter = new JButton("Filter");
        JButton select = new JButton("Select");
        sortBy.setBackground(UIConstants.LIGHT_BEIGE);
        filter.setBackground(UIConstants.LIGHT_BEIGE);
        select.setBackground(UIConstants.LIGHT_BEIGE);

        sortBy.setForeground(UIConstants.TEXT_BROWN);
        filter.setForeground(UIConstants.TEXT_BROWN);
        select.setForeground(UIConstants.TEXT_BROWN);

        sortBy.setFont(UIConstants.fMenu);
        filter.setFont(UIConstants.fMenu);
        select.setFont(UIConstants.fMenu);
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 100));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        topPanel.add(plus);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(sortBy);
        topPanel.add(filter);
        topPanel.add(select);

        return topPanel;
    }
    private void initialize() {

        main.setLayout(new BorderLayout());
        JScrollPane table = new JScrollPane();
        main.add(setTop(), BorderLayout.NORTH);


        table.add(new JTable(4, 2));
        main.add(table);
    }

    public JPanel getMain() {
        return main;
    }
}

package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jan Macecek
 */
public class TabFrame {
    private final JPanel main;
    private JButton plus;
    private JButton sortBy;
    private JButton filter;
    private JButton select;

    public TabFrame() {
        main = new JPanel();
        initialize();
    }

    public JPanel setTop() {
        plus = new JButton("+");
        plus.setBackground(UIConstants.OCHER);
        plus.setFont(UIConstants.fTab);
        plus.setForeground(UIConstants.TEXT_BROWN);
        sortBy = new JButton("Sort by");
        filter = new JButton("Filter");
        select = new JButton("Select");
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

    public JButton getPlus() {
        return plus;
    }

    public JButton getSortBy() {
        return sortBy;
    }

    public JButton getFilter() {
        return filter;
    }
}


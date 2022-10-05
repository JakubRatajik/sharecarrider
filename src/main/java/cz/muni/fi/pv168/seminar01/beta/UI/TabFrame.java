package cz.muni.fi.pv168.seminar01.beta.UI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

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
        main.setBackground(UIConstants.WHITE);
        main.setBorder(javax.swing.BorderFactory.createEmptyBorder());
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
        UIConstants.formatBeigeTextBrownDialog(sortBy);
        UIConstants.formatBeigeTextBrownDialog(filter);
        UIConstants.formatBeigeTextBrownDialog(select);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 20, 100));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        topPanel.add(plus);
        topPanel.setBackground(UIConstants.WHITE);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(sortBy);
        topPanel.add(filter);
        topPanel.add(select);

        return topPanel;
    }
    private void initialize() {

        main.setLayout(new BorderLayout());
        JPanel table = new JPanel();
        main.add(setTop(), BorderLayout.NORTH);

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        table.setBackground(UIConstants.WHITE);
        main.add(table, BorderLayout.CENTER);
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


package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Model.TabCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.TableModel;

import javax.swing.*;
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
    private JPanel tabPanel;

    private TabCategory category;

    // By writing tableModel.getTable(), JTable object can be accessed
    private TableModel table;

    public TabFrame(TabCategory category) {
        this.category = category;
        main = new JPanel();
        main.setBackground(UIConstants.WHITE);
        main.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tabPanel = new JPanel();
        table = new TableModel(this.category);
        initialize();
    }

    public JPanel topPanelInit() {
        plus = new JButton("+");
        plus.setBackground(UIConstants.OCHER);
        plus.setFont(UIConstants.fTab);
        plus.setForeground(UIConstants.TEXT_BROWN);
        sortBy = new JButton("Řadit");
        filter = new JButton("Filtrovat");
        select = new JButton("Vybrat");
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
        main.add(topPanelInit(), BorderLayout.NORTH);
        tabPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tabPanel.setBackground(UIConstants.WHITE);
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.PAGE_AXIS));
        main.add(tabPanel, BorderLayout.CENTER);
        // TODO - for KUBIK... creation of table was moved here
        table.initializeFrame(this);
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

    public JPanel getTabPanel() {
        return tabPanel;
    }
}

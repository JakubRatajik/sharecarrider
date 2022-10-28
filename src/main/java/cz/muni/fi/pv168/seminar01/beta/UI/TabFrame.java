package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.ActionListenerProvider;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Jan Macecek, no zase jako prrrrrr, my sme ti s tym pomohli
 */
public class TabFrame {
    private final JPanel mainPanel;
    private final JPanel tablePanel;
    private final ShareCarRiderTable table;
    private JButton plusButton;
    private JButton sortByButton;
    private JButton filterButton;
    private JButton selectButton;

    public TabFrame(TableCategory category) {
        tablePanel = new JPanel();

        mainPanel = new JPanel();
        mainPanel.setBackground(UIConstants.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder());

        table = new ShareCarRiderTable(category);
        initialize();
    }

    private void initialize() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanelInit(), BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        tablePanel.add(initTableScrollPane());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, UIConstants.LEFT_FRAME_INDENT, 20, UIConstants.RIGHT_FRAME_INDENT));
        tablePanel.setBackground(UIConstants.WHITE);
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));

        // this set actions to all buttons
        setButtonDialogs();
    }

    public JPanel topPanelInit() {
        plusButton = new JButton("+");
        plusButton.setBackground(UIConstants.OCHER);
        plusButton.setFont(UIConstants.fTab);
        plusButton.setForeground(UIConstants.TEXT_BROWN);
        sortByButton = new JButton("Řadit");
        filterButton = new JButton("Filtrovat");
        selectButton = new JButton("Vybrat");
        UIConstants.formatBeigeTextBrownDialog(sortByButton);
        UIConstants.formatBeigeTextBrownDialog(filterButton);
        UIConstants.formatBeigeTextBrownDialog(selectButton);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder());
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, UIConstants.LEFT_FRAME_INDENT, 10, UIConstants.RIGHT_FRAME_INDENT));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        topPanel.add(plusButton);
        topPanel.setBackground(UIConstants.WHITE);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(sortByButton);
        topPanel.add(filterButton);
        topPanel.add(selectButton);

        return topPanel;
    }

    private JScrollPane initTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, UIConstants.MIDDLE_BROWN));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        scrollPane.setLayout(new ScrollPaneLayout());

        return scrollPane;
    }

    private void setButtonDialogs() {
        List<ActionListener> actions = ActionListenerProvider.getAddALs(table.getTableCategory());
        if (actions == null) {
            return;
        }
        plusButton.addActionListener(actions.get(0));
        sortByButton.addActionListener(actions.get(1));
        filterButton.addActionListener(actions.get(2));
        selectButton.addActionListener(actions.get(3));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getPlusButton() {
        return plusButton;
    }

    public JButton getSortByButton() {
        return sortByButton;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public ShareCarRiderTable getTable() {
        return table;
    }
}

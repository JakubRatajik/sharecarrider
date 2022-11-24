package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.ActionListenerProvider;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private JButton deleteButton;

    public TabFrame(TableCategory category) {
        tablePanel = new JPanel();

        mainPanel = new JPanel();
        mainPanel.setBackground(UIUtilities.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder());

        table = new ShareCarRiderTable(category);
        initialize();
    }

    private void initialize() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanelInit(), BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        tablePanel.add(initTableScrollPane());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, UIUtilities.LEFT_FRAME_INDENT, 20, UIUtilities.RIGHT_FRAME_INDENT));
        tablePanel.setBackground(UIUtilities.WHITE);
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));

        // this set actions to all buttons
        setButtonDialogs();
    }

    public JPanel topPanelInit() {
        plusButton = new JButton("+");
        plusButton.setBackground(UIUtilities.OCHER);
        plusButton.setFont(UIUtilities.fTab);
        plusButton.setForeground(UIUtilities.TEXT_BROWN);
        selectButton = new JButton("Vybrat");
        sortByButton = new JButton("Řadit");
        filterButton = new JButton("Filtrovat");
        deleteButton = new JButton("Smazat");
        deleteButton.setMnemonic(KeyEvent.VK_BACK_SPACE);
        UIUtilities.formatBeigeTextBrownDialog(selectButton);
        UIUtilities.formatBeigeTextBrownDialog(sortByButton);
        UIUtilities.formatBeigeTextBrownDialog(filterButton);
        UIUtilities.formatBeigeTextBrownDialog(deleteButton);

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder());
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, UIUtilities.LEFT_FRAME_INDENT, 10, UIUtilities.RIGHT_FRAME_INDENT));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        topPanel.add(plusButton);
        topPanel.setBackground(UIUtilities.WHITE);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(selectButton);
        topPanel.add(sortByButton);
        topPanel.add(filterButton);
        topPanel.add(deleteButton);

        return topPanel;
    }

    private JScrollPane initTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, UIUtilities.MIDDLE_BROWN));
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
        deleteButton.addActionListener(actions.get(4));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getPlusButton() {
        return plusButton;
    }

    public JButton getSelectButton() {
        return selectButton;
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

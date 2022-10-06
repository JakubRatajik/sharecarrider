package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

import cz.muni.fi.pv168.seminar01.beta.UI.Model.TabCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.TabFrame;
import cz.muni.fi.pv168.seminar01.beta.UI.UIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Jakub Ratajik
 */
public final class TableInitializer {
    private TableInitializer() {
    }

    public static void initializeTab(TabFrame tabFrame, TabCategory tabCategory) {
        JTable table;

        switch (tabCategory) {
            case RIDES -> table = initializeRidesTable();
            case VEHICLES -> table = initializeVehiclesTable();
            case PASSENGERS -> table = initializePassengersTable();
            case STATISTICS -> table = initializeStatisticsTable();
            default -> table = new JTable();
        }

        initializeCommonComponents(table, tabFrame);
    }

    private static void initializeCommonComponents(JTable table, TabFrame tabFrame) {
        tabFrame.getTabPanel().add(table);

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBackground(UIConstants.MIDDLE_BROWN);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(UIConstants.MIDDLE_BROWN);
        tabFrame.getTabPanel().add(scrollPane);
//        table.setGridColor(UIConstants.MIDDLE_BROWN);
//        table.setShowGrid(true);
        table.setFont(UIConstants.fTable);
        table.getTableHeader().setBackground(UIConstants.OCHER);

        table.setAutoCreateRowSorter(true);

        scrollPane.setMaximumSize(new Dimension(20, 20));
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object object, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component component = super.getTableCellRendererComponent(table,
                        object, isSelected, hasFocus, row, column);
                component.setBackground(row % 2 == 0 ? UIConstants.WHITE : UIConstants.LIGHT_BEIGE);
                if (isSelected) {
                    component.setBackground(UIConstants.MIDDLE_BROWN);
                }
                if (hasFocus) {
                    component.setBackground(UIConstants.LIGHT_BEIGE);
                }
                
                table.setGridColor(UIConstants.MIDDLE_BROWN);
                table.setShowGrid(true);
                return component;
            }
        });


    }

    private static JTable initializeStatisticsTable() {
        return new JTable();
    }

    private static JTable initializeRidesTable() {
        String[] columnNames = {"Date", "Departure", "From", "To", "Distance", "Categories"};
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2022, 5, 26);
        LocalTime chillTime = LocalTime.of(4, 20);
        LocalTime deadline = LocalTime.of(10, 0);

        Object[][] data = {
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                {birthday, deadline, "Skybar", "Kino Scala", 3, "Work"},
                {today, chillTime, "Vranov nad Topľou", "Supíkovce", 987, "Party"}
        };

        JTable table = new JTable(data, columnNames);

        // this is terrible temporary solution
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setMinWidth(data[0][i].toString().length() * 15);
        }
        table.getTableHeader().setReorderingAllowed(false);
        return table;
    }

    private static JTable initializePassengersTable() {
        List<String> columnNames = List.of("Forename", "Surname", "Phone", "Category");
        return new JTable();
    }

    private static JTable initializeVehiclesTable() {
        List<String> columnNames = List.of("Brand", "Type", "Capacity", "Average consumption");
        return new JTable();
    }

}

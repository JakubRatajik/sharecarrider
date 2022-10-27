package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.VehicleTableModel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jakub Ratajik
 */
public class ShareCarRiderTable extends JTable {
    private TabFrame tabFrame;
    private TableCategory tableCategory;
    private JMenuItem detailItem;
    private JMenuItem editItem;

    public ShareCarRiderTable(TabFrame tabFrame, TableCategory tableCategory) {
        this.tabFrame = tabFrame;
        this.tableCategory = tableCategory;

        switch (tableCategory) {
            case RIDES -> setModel(new RideTableModel());
            case VEHICLES -> setModel(new VehicleTableModel());
            case PASSENGERS -> setModel(new PassengerTableModel());
            default -> setModel(new DefaultTableModel());
        }

        initializeTable();
    }

    private void initializeTable() {
//        getModel().addTableModelListener(this);
        getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        tabFrame.getTabPanel().add(this);
        getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, UIConstants.MIDDLE_BROWN));
        tabFrame.getTabPanel().setBorder(BorderFactory.createEmptyBorder(0, 60, 20, 100));
        setMinimumSize(new Dimension(590, 400));
//        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
//        verticalScrollBar.setPreferredSize(new Dimension(15, 0));

        addDoubleClickListener();
        addPopupMenu();
        setColumnSelectionAllowed(false);
        scrollPane.setBackground(UIConstants.WHITE);

        scrollPane.setBackground(UIConstants.MIDDLE_BROWN);
//        setMinimumSize(new Dimension(7000, 7000));
//        tabFrame.getMain().setMinimumSize(new Dimension(7000, 7000));
        tabFrame.getTabPanel().add(scrollPane);
//        setGridColor(UIConstants.MIDDLE_BROWN);
        setFont(UIConstants.fTable);
        getTableHeader().setBackground(UIConstants.OCHER);

        int[] minWidths = {100, 50, 150, 150, 40, 100};
        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setMinWidth(minWidths[i]);
            column.setMaxWidth(400);


        }


        setAutoCreateRowSorter(true);
//        setBorder(new MatteBorder(2, 0, 0, 0, UIConstants.MIDDLE_BROWN));
//        setBorder(BorderFactory.createEmptyBorder());
        setRowHeight(25);
        getTableHeader().setFont(UIConstants.fTable);

//        scrollPane.setMaximumSize(new Dimension(20, 20));
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object object, boolean isSelected, boolean hasFocus,
                                                           int row, int col) {

                Component component = super.getTableCellRendererComponent(table,
                        object, isSelected, hasFocus, row, col);

                component.setBackground(row % 2 == 0 ? UIConstants.WHITE : UIConstants.LIGHT_BEIGE);

                if (isSelected) {
                    component.setBackground(UIConstants.MIDDLE_BROWN);
                }
                if (hasFocus) {
                    setBorder(BorderFactory.createEmptyBorder());
                }

                setGridColor(UIConstants.MIDDLE_BROWN);
                return component;
            }
        });
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        var count = selectionModel.getSelectedItemsCount();

        detailItem.setEnabled(count == 1);
//        .setEnabled(selectedItemsCount >= 1);
    }

//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        //TODO
//    }


//    @Override
//    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int col = e.getColumn();
//        if (row < 0 || col < 0) {
//            return;
//        }
//        TableModel model = (TableModel) e.getSource();
//        String columnName = model.getColumnName(col);
//        Object data = model.getValueAt(row, col);
//        // TODO what should happen to data
//    }

    private void addPopupMenu() {
        JPopupMenu jPopupMenu = new JPopupMenu();
        this.detailItem = new JMenuItem("Detail");
        detailItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel tableModel = (ShareCarRiderTableModel) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var entity = tableModel.getEntity(modelRow);

                new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy");
//            switch(table.tableCategory) {
//                case VEHICLES -> new VehicleDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//                case PASSENGERS -> new PassengerDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//                case RIDES -> new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//            }

            }
        });
        jPopupMenu.add(detailItem);
        setComponentPopupMenu(jPopupMenu);

        detailItem.setEnabled(getSelectedRow() == 1);
    }

    private void addDoubleClickListener() {
    addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            ShareCarRiderTable table = (ShareCarRiderTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            if (mouseEvent.getClickCount() != 2 || table.getSelectedRow() == -1 || mouseEvent.getButton() != MouseEvent.BUTTON1) {
                return;
            }

            ShareCarRiderTableModel tableModel = (ShareCarRiderTableModel) table.getModel();
            int modelRow = table.convertRowIndexToModel(row);
            var entity = tableModel.getEntity(modelRow);

            new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy");
//            switch(table.tableCategory) {
//                case VEHICLES -> new VehicleDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//                case PASSENGERS -> new PassengerDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//                case RIDES -> new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", entity);
//            }
        }
    });
}
    public void hideColumn(int col) {
        getColumnModel().getColumn(col).setMinWidth(0);
        getColumnModel().getColumn(col).setMaxWidth(0);
        getColumnModel().getColumn(col).setWidth(0);
    }

    public void hideCheckboxColumn() {
        hideColumn(0);
    }

    public TableCategory getTableCategory() {
        return tableCategory;
    }

    public TabFrame getTabFrame() {
        return tabFrame;
    }

    //TODO: add tableRowSorter
//    MyTableModel model = new MyTableModel();
//    sorter = new TableRowSorter<MyTableModel>(model);
//    table = new JTable(model);
//    setRowSorter(sorter);
//    List <RowSorter.SortKey> sortKeys
//            = new ArrayList<RowSorter.SortKey>();
//    sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
//    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//    sorter.setSortKeys(sortKeys);
}

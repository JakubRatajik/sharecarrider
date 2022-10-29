package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.*;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.VehicleTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jakub Ratajik
 */
public class ShareCarRiderTable extends JTable {
    private final TableCategory tableCategory;
    private JMenuItem detailPopupMenuItem;
    private JMenuItem editPopupMenuItem;
    private JMenuItem deletePopupMenuItem;
    private JMenuItem addPopupMenuItem;

    public ShareCarRiderTable(TableCategory tableCategory) {
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
        setFont(UIConstants.fTable);

        getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBackground(UIConstants.OCHER);
        getTableHeader().setFont(UIConstants.fTable);

        setAutoCreateRowSorter(true);
        setRowHeight(25);
        setColumnSelectionAllowed(false);

        setColumnWidths();
        changeDefaultRenderer();
        addDoubleClickListener();
        addPopupMenu();
    }

    private void setColumnWidths() {
        int[] minWidths = {100, 80, 150, 150, 100, 100};

        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setMaxWidth(400);
            column.setMinWidth(minWidths[i]);
            column.setPreferredWidth(minWidths[i]);
        }
    }

    private void changeDefaultRenderer() {
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object object, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                Component component = super.getTableCellRendererComponent(table,
                        object, isSelected, hasFocus, row, col);

                component.setBackground(row % 2 == 0 ? UIConstants.WHITE : UIConstants.LIGHT_BEIGE);

                if (isSelected) {
                    component.setBackground(UIConstants.MIDDLE_BROWN);
                }
                if (hasFocus) {
                    setBorder(BorderFactory.createEmptyBorder());
                }

                return component;
            }
        });
    }

    private void addPopupMenu() {
        JPopupMenu jPopupMenu = new JPopupMenu();
        detailPopupMenuItem = new JMenuItem("Detail");
        deletePopupMenuItem = new JMenuItem("Smazat");
        editPopupMenuItem = new JMenuItem("Upravit");
        addPopupMenuItem = new JMenuItem("Přidat");

        jPopupMenu.add(detailPopupMenuItem);
        jPopupMenu.add(editPopupMenuItem);
        jPopupMenu.add(deletePopupMenuItem);
        jPopupMenu.add(addPopupMenuItem);

        detailPopupMenuItem.setEnabled(false);
        editPopupMenuItem.setEnabled(false);
        deletePopupMenuItem.setEnabled(false);
        addPopupMenuItem.setEnabled(true);

        switch (tableCategory) {
            case VEHICLES -> addVehicleTableActionListeners();
            case RIDES -> addRideTableActionListeners();
            case PASSENGERS -> addPassengerTableActionListeners();
        }

        setComponentPopupMenu(jPopupMenu);
    }

    private void addRideTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var ride = tableModel.getEntity(modelRow);

                new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", (Ride) ride);
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var ride = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Upravit jízdu");
//                new RideEditDialog(MainWindow.getFrame(), "Upravit jízdu");
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var ride = tableModel.getEntity(modelRow);

                new DeleteDialog(MainWindow.getFrame(), "Smazat jízdu/y",
                        TableCategory.RIDES, DialogBase.getTable(TableCategory.RIDES).getSelectedRows());

            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var ride = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Přidat jízdu");
//                new RideAddDialog(MainWindow.getFrame(), "Přidat jízdu");
            }
        });
    }

    private void addVehicleTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var vehicle = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Detail vozidla");
//                new VehicleDetailDialog(MainWindow.getFrame(), "Detail vozidla");
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var vehicle = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Upravit vozidlo");
//                new VehicleEditDialog(MainWindow.getFrame(), "Upravit vozidlo");
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var vehicle = tableModel.getEntity(modelRow);

                new DeleteDialog(MainWindow.getFrame(), "Smazat vozidlo/a",
                        TableCategory.VEHICLES, DialogBase.getTable(TableCategory.VEHICLES).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var vehicle = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Přidat vozidlo");
//                new VehicleAddDialog(MainWindow.getFrame(), "Přidat vozidlo");
            }
        });
    }

    private void addPassengerTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var passenger = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Detail cestujícího");
//                new PassengerDetailDialog(MainWindow.getFrame(), "Detail cestujícího");
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var passenger = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Upravit cestujícího");
//                new PassengerEditDialog(MainWindow.getFrame(), "Upravit cestujícího");
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var passenger = tableModel.getEntity(modelRow);

                new DeleteDialog(MainWindow.getFrame(), "Smazat cestující/ho",
                        TableCategory.PASSENGERS, DialogBase.getTable(TableCategory.PASSENGERS).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
                int modelRow = convertRowIndexToModel(getSelectedRow());
                var passenger = tableModel.getEntity(modelRow);

                new TemporaryDialog(MainWindow.getFrame(), "Přidat cestujícího");
//                new PassengerAddDialog(MainWindow.getFrame(), "Přidat cestujícího");
            }
        });
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

                ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) table.getModel();
                int modelRow = table.convertRowIndexToModel(row);
                var entity = tableModel.getEntity(modelRow);
                switch (tableCategory) {
                    case VEHICLES ->
                            new VehicleDetailDialog(MainWindow.getFrame(), "Detail vozidla", (Vehicle) entity);
                    case PASSENGERS ->
                            new PassengerDetailDialog(MainWindow.getFrame(), "Detail cestujícího", (Passenger) entity);
                    case RIDES ->
                            new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", (Ride) entity);
                }
            }
        });
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        var count = selectionModel.getSelectedItemsCount();

        detailPopupMenuItem.setEnabled(count == 1);
        editPopupMenuItem.setEnabled(count == 1);
        deletePopupMenuItem.setEnabled(count >= 1);
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

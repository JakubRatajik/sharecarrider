package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.Main;
import cz.muni.fi.pv168.seminar01.beta.model.HasID;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.DeleteDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.PassengerDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.VehicleDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

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
import java.util.stream.IntStream;

/**
 * @author Jakub Ratajik
 */
public class ShareCarRiderTable extends JTable {
    private final TableCategory tableCategory;
    private JMenuItem detailPopupMenuItem;
    private JMenuItem editPopupMenuItem;
    private JMenuItem deletePopupMenuItem;
    private JMenuItem addPopupMenuItem;
    private boolean isMultilineSelectionEnabled;
    private MouseAdapter doubleClickListener;

    public ShareCarRiderTable(TableCategory tableCategory) {
        this.tableCategory = tableCategory;

        switch (tableCategory) {
            case RIDES -> setModel(new RideTableModel());
            case VEHICLES -> setModel(new VehicleTableModel());
            case PASSENGERS -> setModel(new PassengerTableModel());
            case PASSENGERCATEGORY -> setModel(new PassengerCategoryTableModel());
            case RIDECATEGORY -> setModel(new RideCategoryTableModel());
            default -> setModel(new DefaultTableModel());
        }

        initializeTable();
    }

    private void initializeTable() {
        setFont(UIUtilities.fTable);

        getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBackground(UIUtilities.OCHER);
        getTableHeader().setFont(UIUtilities.fTable);

        setAutoCreateRowSorter(true);
        setRowHeight(25);
        setColumnSelectionAllowed(false);

        setColumnWidths();
        changeDefaultRenderer();
        initDoubleClickListener();
        addMouseListener(doubleClickListener);
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

                component.setBackground(row % 2 == 0 ? UIUtilities.WHITE : UIUtilities.LIGHT_BEIGE);

                if (isSelected) {
                    component.setBackground(UIUtilities.MIDDLE_BROWN);
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
            case PASSENGERCATEGORY -> addPassengerCategoryTableActionListeners();
            case RIDECATEGORY -> addRideCategoryTableActionListeners();
        }

        setComponentPopupMenu(jPopupMenu);
    }

    private HasID getObjectFromTable() {
        ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
        int modelRow = convertRowIndexToModel(getSelectedRow());
        return tableModel.getEntity(modelRow);
    }

    private void addRideTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ride = getObjectFromTable();

                new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", (Ride) ride);
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ride = getObjectFromTable();

                new AddEditRideDialog(MainWindow.getFrame(), "Upravit jízdu", (Ride) ride);
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ride = getObjectFromTable();

                new DeleteDialog(MainWindow.getFrame(), "Smazat jízdu/y",
                        TableCategory.RIDES, Shortcut.getTable(TableCategory.RIDES).getSelectedRows());

            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddEditRideDialog(MainWindow.getFrame(), "Přidat jízdu");
            }
        });
    }

    private void addVehicleTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var vehicle = getObjectFromTable();

                new VehicleDetailDialog(MainWindow.getFrame(), "Detail vozidla", (Vehicle) vehicle);
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var vehicle = getObjectFromTable();

                new AddEditVehicleDialog(MainWindow.getFrame(), "Upravit vozidlo", (Vehicle) vehicle);
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat vozidlo/a",
                        TableCategory.VEHICLES, Shortcut.getTable(TableCategory.VEHICLES).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditVehicleDialog(MainWindow.getFrame(), "Přidat vozidlo");
            }
        });
    }

    private void addPassengerTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var passenger = getObjectFromTable();

                new PassengerDetailDialog(MainWindow.getFrame(), "Detail cestujícího", (Passenger) passenger);
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var passenger = getObjectFromTable();

                new AddEditPassengerDialog(MainWindow.getFrame(), "Upravit cestujícího", (Passenger) passenger);
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat cestující/ho",
                        TableCategory.PASSENGERS, Shortcut.getTable(TableCategory.PASSENGERS).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditPassengerDialog(MainWindow.getFrame(), "Přidat cestujícího");
            }
        });
    }


    private void addPassengerCategoryTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ErrorDialog(MainWindow.getFrame(), "Tento objekt nemá detail k dispozici");
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var passengerCategory = getObjectFromTable();

                new ErrorDialog(MainWindow.getFrame(), "Kategorii prozatím není možné měnit");
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                        TableCategory.PASSENGERCATEGORY, Shortcut.getTable(TableCategory.PASSENGERCATEGORY).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ErrorDialog(MainWindow.getFrame(), "Kategorie nemohou prozatím být vytvořeny");
            }
        });
    }



    private void addRideCategoryTableActionListeners() {
        detailPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ErrorDialog(MainWindow.getFrame(), "Tento objekt nemá detail k dispozici");
            }
        });

        editPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var rideCategory = getObjectFromTable();

                new ErrorDialog(MainWindow.getFrame(), "Kategorii prozatím není možné měnit");
            }
        });

        deletePopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                        TableCategory.RIDECATEGORY, Shortcut.getTable(TableCategory.RIDECATEGORY).getSelectedRows());
            }
        });

        addPopupMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ErrorDialog(MainWindow.getFrame(), "Kategorie nemohou prozatím být vytvořeny");
            }
        });
    }






    private void initDoubleClickListener() {
        doubleClickListener = new MouseAdapter() {
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
        };
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

    public boolean isMultilineSelectionEnabled() {
        return isMultilineSelectionEnabled;
    }

    void setMultilineSelectionEnabled(boolean wantToEnable) {
        isMultilineSelectionEnabled = wantToEnable;
    }

    public void enableMultilineSelection(boolean enable) {
        isMultilineSelectionEnabled = enable;
        clearSelection();
        JButton selectButton = Shortcut.getSelectButton(tableCategory);

        if (enable) {
            removeMouseListener(doubleClickListener);
            UIUtilities.formatInverseBeigeTextBrownComponent(selectButton);
        } else {
            addMouseListener(doubleClickListener);
            UIUtilities.formatBeigeTextBrownDialog(selectButton);
        }
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

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        if (isMultilineSelectionEnabled) {
            if (IntStream.of(getSelectedRows()).anyMatch(row -> row == rowIndex)) {
                removeRowSelectionInterval(rowIndex, rowIndex);
            } else {
                addRowSelectionInterval(rowIndex, rowIndex);
            }
        } else {
            super.changeSelection(rowIndex, columnIndex, toggle, extend);
        }
    }
}

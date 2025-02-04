package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.DateTimeUtils;
import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.HasID;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideCategoryDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditRideDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.AddEditVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.CategoryDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.DeleteDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.PassengerDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.VehicleDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;
import cz.muni.fi.pv168.seminar01.beta.wiring.ProductionDependencyProvider;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
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

    public ShareCarRiderTable(TableCategory tableCategory, ProductionDependencyProvider provider) {
        this.tableCategory = tableCategory;

        switch (tableCategory) {
            case RIDES -> setModel(new RideTableModel(provider.getRideRepository()));
            case VEHICLES -> setModel(new VehicleTableModel(provider.getVehicleRepository()));
            case PASSENGERS -> setModel(new PassengerTableModel(provider.getPassengerRepository()));
            case PASSENGER_CATEGORY ->
                    setModel(new PassengerCategoryTableModel(provider.getPassengerCategoryRepository()));
            case RIDE_CATEGORY -> setModel(new RideCategoryTableModel(provider.getRideCategoryRepository()));
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
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        getRowSorter().setSortKeys(sortKeys);
        setRowHeight(25);
        setColumnSelectionAllowed(false);

        setColumnWidths();
        changeDefaultCellRenderers();
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

    private void changeDefaultCellRenderers() {
        TableCellRenderer defaultCellRenderer = new DefaultTableCellRenderer() {
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
        };

        TableCellRenderer defaultTimeRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object object, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                String time = ((LocalTime) object).format(DateTimeUtils.TIME_FORMATTER);
                Component component = defaultCellRenderer.getTableCellRendererComponent(table,
                        time, isSelected, hasFocus, row, col);

                return component;
            }
        };

        setDefaultRenderer(Object.class, defaultCellRenderer);
        setDefaultRenderer(Integer.class, defaultCellRenderer);
        setDefaultRenderer(Double.class, defaultCellRenderer);
        setDefaultRenderer(LocalTime.class, defaultTimeRenderer);

        changeLocalDateRenderer(true);
    }

    public void changeLocalDateRenderer(boolean shouldSeparateWeeks) {
        TableCellRenderer defaultCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object object, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                String date = ((LocalDate) object).format(DateTimeUtils.DATE_FORMATTER);
                Component component = getDefaultRenderer(Object.class).getTableCellRendererComponent(table,
                        date, isSelected, hasFocus, row, col);

                if (shouldSeparateWeeks && isLastDateOfWeek(table, (LocalDate) object, row)) {
                    component.setFont(UIUtilities.fTableWeek);
                }

                return component;
            }
        };
        setDefaultRenderer(LocalDate.class, defaultCellRenderer);
    }

    private boolean isLastDateOfWeek(JTable table, LocalDate date, int row) {
        if (row == table.getRowCount() - 1) {
            return true;
        }

        LocalDate then = (LocalDate) table.getValueAt(row + 1, 0);

        return date.getYear() > then.getYear() ||
            date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) > then.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
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
            case PASSENGER_CATEGORY ->
                    addPassengerCategoryTableActionListeners();
            case RIDE_CATEGORY -> addRideCategoryTableActionListeners();
        }

        setComponentPopupMenu(jPopupMenu);
    }

    private HasID getObjectFromTable() {
        ShareCarRiderTableModel<?> tableModel = (ShareCarRiderTableModel<?>) getModel();
        int modelRow = convertRowIndexToModel(getSelectedRow());
        return tableModel.getEntity(modelRow);
    }

    private void addRideTableActionListeners() {
        detailPopupMenuItem.addActionListener(actionListener -> {
            var ride = getObjectFromTable();

            new RideDetailDialog(MainWindow.getFrame(), "Detail jízdy", (Ride) ride);
        });

        editPopupMenuItem.addActionListener(actionListener -> {
            var ride = getObjectFromTable();

            new AddEditRideDialog(MainWindow.getFrame(), "Upravit jízdu", (Ride) ride);
        });

        deletePopupMenuItem.addActionListener(actionListener ->
                new DeleteDialog(MainWindow.getFrame(), "Smazat jízdu/y",
                        TableCategory.RIDES, CommonElementSupplier.getTable(TableCategory.RIDES).getSelectedRows()));

        addPopupMenuItem.addActionListener(actionListener -> new AddEditRideDialog(MainWindow.getFrame(), "Přidat jízdu"));
    }

    private void addVehicleTableActionListeners() {
        detailPopupMenuItem.addActionListener(actionListener -> {
            var vehicle = getObjectFromTable();

            new VehicleDetailDialog(MainWindow.getFrame(), "Detail vozidla", (Vehicle) vehicle);
        });

        editPopupMenuItem.addActionListener(actionListener -> {
            var vehicle = getObjectFromTable();

            new AddEditVehicleDialog(MainWindow.getFrame(), "Upravit vozidlo", (Vehicle) vehicle);
        });

        deletePopupMenuItem.addActionListener(actionListener -> new DeleteDialog(MainWindow.getFrame(), "Smazat vozidlo/a",
                TableCategory.VEHICLES, CommonElementSupplier.getTable(TableCategory.VEHICLES).getSelectedRows()));

        addPopupMenuItem.addActionListener(actionListener -> new AddEditVehicleDialog(MainWindow.getFrame(), "Přidat vozidlo"));
    }

    private void addPassengerTableActionListeners() {
        detailPopupMenuItem.addActionListener(actionListener -> {
            var passenger = getObjectFromTable();

            new PassengerDetailDialog(MainWindow.getFrame(), "Detail cestujícího", (Passenger) passenger);
        });

        editPopupMenuItem.addActionListener(actionListener -> {
            var passenger = getObjectFromTable();

            new AddEditPassengerDialog(MainWindow.getFrame(), "Upravit cestujícího", (Passenger) passenger);
        });

        deletePopupMenuItem.addActionListener(actionListener -> new DeleteDialog(MainWindow.getFrame(), "Smazat cestující/ho",
                TableCategory.PASSENGERS, CommonElementSupplier.getTable(TableCategory.PASSENGERS).getSelectedRows()));

        addPopupMenuItem.addActionListener(actionListener -> new AddEditPassengerDialog(MainWindow.getFrame(), "Přidat cestujícího"));
    }


    private void addPassengerCategoryTableActionListeners() {
        detailPopupMenuItem.addActionListener(actionListener -> {
            var passengerCategory = getObjectFromTable();
            new CategoryDetailDialog(MainWindow.getFrame(), "Detail kategorie", (Category) passengerCategory);
        });

        editPopupMenuItem.addActionListener(actionListener -> {
            var passengerCategory = getObjectFromTable();

            new AddEditPassengerCategoryDialog(MainWindow.getFrame(), "Upravit kategorii", (PassengerCategory) passengerCategory);
        });

        deletePopupMenuItem.addActionListener(actionListener -> new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                TableCategory.PASSENGER_CATEGORY, CommonElementSupplier.getTable(TableCategory.PASSENGER_CATEGORY).getSelectedRows()));

        addPopupMenuItem.addActionListener(actionListener -> new AddEditPassengerCategoryDialog(MainWindow.getFrame(), "Vytvořit kategorii"));
    }


    private void addRideCategoryTableActionListeners() {
        detailPopupMenuItem.addActionListener(actionListener -> {
            var category = getObjectFromTable();
            new CategoryDetailDialog(MainWindow.getFrame(), "Detail kategorie", (Category) category);
        });

        editPopupMenuItem.addActionListener(actionListener -> {
            var rideCategory = getObjectFromTable();

            new AddEditRideCategoryDialog(MainWindow.getFrame(), "Upravit kategorii", (RideCategory) rideCategory);
        });

        deletePopupMenuItem.addActionListener(actionListener -> new DeleteDialog(MainWindow.getFrame(), "Smazat kategorii/e",
                TableCategory.RIDE_CATEGORY, CommonElementSupplier.getTable(TableCategory.RIDE_CATEGORY).getSelectedRows()));

        addPopupMenuItem.addActionListener(actionListener -> new AddEditRideCategoryDialog(MainWindow.getFrame(), "Vytvořit kategorii jízdy"));
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
                    case RIDE_CATEGORY, PASSENGER_CATEGORY ->
                            new CategoryDetailDialog(MainWindow.getFrame(), "Detail kategorie", (Category) entity);
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
        JButton selectButton = CommonElementSupplier.getSelectButton(tableCategory);

        if (enable) {
            removeMouseListener(doubleClickListener);
            UIUtilities.formatInverseBeigeTextBrownComponent(selectButton);
        } else {
            addMouseListener(doubleClickListener);
            UIUtilities.formatBeigeTextBrownDialog(selectButton);
        }
    }

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

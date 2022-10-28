package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

/**
 * @author Jakub Ratajik
 */
public abstract class ShareCarRiderTableModel<T> extends AbstractTableModel {
    protected final List<T> data;
    private final String[] columnNames;

    public ShareCarRiderTableModel(String[] columnNames, List<T> data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public T getEntity(int modelRow) {
        return data.get(modelRow);
    }

    public List<T> getData() {
        return Collections.unmodifiableList(data);
    }

    public void deleteRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(T object) {
        int newRowIndex = data.size();
        data.add(object);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(T employee) {
        int rowIndex = data.indexOf(employee);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
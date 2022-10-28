package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import javax.swing.table.AbstractTableModel;

/**
 * @author Jakub Ratajik
 */
public abstract class ShareCarRiderTableModel extends AbstractTableModel {
    private final Object[][] data;
    private final String[] columnNames;

    public ShareCarRiderTableModel(String[] columnNames, Object[][] data) {
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
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public abstract Object getEntity(int modelRow);
}

package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.HasID;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jakub Ratajik
 */
public abstract class ShareCarRiderTableModel<T extends HasID> extends AbstractTableModel {
    protected List<T> data;
    protected final Repository<T> repository;
    private final String[] columnNames;

    public ShareCarRiderTableModel(String[] columnNames, List<T> data, Repository<T> repository) {
        this.columnNames = columnNames;
        this.data = data;
        this.repository = repository;
        for (T record: data) {
            repository.create(record);
        }

    }

    public ShareCarRiderTableModel(String[] columnNames, Repository<T> repository) {
        this.columnNames = columnNames;
        this.repository = repository;

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
        return repository.getSize();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public T getEntity(int modelRow) {
        return repository.findByIndex(modelRow).orElse(null);
    }

    public List<T> getData() {
        return repository.findAll();
    }

    public void deleteRow(int rowIndex) {
        repository.deleteByIndex(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(T object) {
        int newRowIndex = repository.getSize();
        repository.create(object);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(T object) {
        int rowIndex = repository.findIndexByEntity(object);
        repository.update(object);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public T getObjectById(long id) {
        return repository.findById(id).orElse(null);
    }
}

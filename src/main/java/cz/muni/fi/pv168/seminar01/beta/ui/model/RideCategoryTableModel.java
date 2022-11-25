package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Macecek
 */
public class RideCategoryTableModel extends ShareCarRiderTableModel<RideCategory>{


    public RideCategoryTableModel(Repository<RideCategory> repository) {
        super(new String[] {"Název"}, SampleUsage.getRideCategories(), repository);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        RideCategory category = repository.findByIndex(row).orElse(null);
        if (category == null) {
            throw new NullPointerException("Vehicle cannot be null at this point (RCTM -> getValueAt)");
        }

        switch (col) {
            case 0 -> value = category.getName();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        RideCategory rideCategory = data.get(row);

        switch (col) {
            case 0 -> rideCategory.setName((String) attribute);

        }
    }

    public List<RideCategory> getCategories() {
        List<RideCategory> list = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            list.add(data.get(i));
        }
        return list;
    }

    public RideCategory[] getCategoriesArray() {
        RideCategory[] array = new RideCategory[getCategories().size()];
        getCategories().toArray(array);
        return array;
    }

    public RideCategory getCategoryByID(long wantedID) {
        return getCategories().stream().filter(x -> x.getId() == wantedID).findFirst().orElse(null);
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.RideCat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Macecek
 */
public class RideCategoryTableModel extends ShareCarRiderTableModel<RideCat>{


    public RideCategoryTableModel() {
        super(new String[] {"Název"}, SampleUsage.getRideCategories());
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        RideCat rideCategory = data.get(row);

        switch (col) {
            case 0 -> value = rideCategory.getName();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        RideCat rideCategory = data.get(row);

        switch (col) {
            case 0 -> rideCategory.setName((String) attribute);

        }
    }

    public List<RideCat> getCategories() {
        List<RideCat> list = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            list.add(data.get(i));
        }
        return list;
    }

    public RideCat[] getCategoriesArray() {
        RideCat[] array = new RideCat[getCategories().size()];
        getCategories().toArray(array);
        return array;
    }

    public RideCat getCategoryByID(long wantedID) {
        return getCategories().stream().filter(x -> x.getId() == wantedID).findFirst().orElse(null);
    }
}

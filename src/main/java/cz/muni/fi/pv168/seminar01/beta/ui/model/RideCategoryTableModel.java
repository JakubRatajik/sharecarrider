package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCat;
import cz.muni.fi.pv168.seminar01.beta.model.RideCat;

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
}

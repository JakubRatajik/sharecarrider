package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCat;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.List;
import java.util.Set;

/**
 * @author Jan Macecek
 */
public class PassengerCategoryTableModel extends ShareCarRiderTableModel<PassengerCat>{


    public PassengerCategoryTableModel() {
        super(new String[] {"Název"},SampleUsage.getPassengerCategories());
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        PassengerCat passengerCategory = data.get(row);

        switch (col) {
            case 0 -> value = passengerCategory.getName();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        PassengerCat passengerCategory = data.get(row);

        switch (col) {
            case 0 -> passengerCategory.setName((String) attribute);

        }
    }
}

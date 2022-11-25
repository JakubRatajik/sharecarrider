package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Macecek
 */
public class PassengerCategoryTableModel extends ShareCarRiderTableModel<PassengerCategory> {


    public PassengerCategoryTableModel(Repository<PassengerCategory> repository) {
        super(new String[] {"Název"},SampleUsage.getPassengerCategories(), repository);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        PassengerCategory passengerCategory = data.get(row);

        switch (col) {
            case 0 -> value = passengerCategory.getName();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        PassengerCategory passengerCategory = data.get(row);

        switch (col) {
            case 0 -> passengerCategory.setName((String) attribute);

        }
    }

    public List<PassengerCategory> getCategories() {
        List<PassengerCategory> list = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            list.add(data.get(i));
        }
        return list;
    }

}

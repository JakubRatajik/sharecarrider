package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Jakub Ratajik
 */
public class PassengerTableModel extends ShareCarRiderTableModel<Passenger> {
    public static final int COLUMN_FIRSTNAME = 0;
    public static final int COLUMN_LASTNAME = 1;
    public static final int COLUMN_PHONE_NUMBER = 2;
    public static final int COLUMN_CATEGORIES = 3;

    public PassengerTableModel() {
        super(new String[]{"Jméno", "Příjmení", "Telefon", "Kategorie"}, SampleUsage.getPassengers());
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == COLUMN_CATEGORIES) {
            return Set.class;
        }

        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Passenger passenger = data.get(row);

        return switch (col) {
            case COLUMN_FIRSTNAME -> passenger.getFirstName();
            case COLUMN_LASTNAME -> passenger.getLastName();
            case COLUMN_PHONE_NUMBER -> passenger.getPhoneNumber();
            case COLUMN_CATEGORIES -> passenger.getCategoryNames();
            default -> throw new IllegalStateException("Unexpected value: " + col);
        };
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Passenger passenger = data.get(row);

        switch (col) {
            case COLUMN_FIRSTNAME -> passenger.setFirstName((String) attribute);
            case COLUMN_LASTNAME -> passenger.setLastName((String) attribute);
            case COLUMN_PHONE_NUMBER -> passenger.setPhoneNumber((String) attribute);
            case COLUMN_CATEGORIES -> passenger.setCategories((ArrayList<PassengerCategory>) attribute);
        }
    }
}

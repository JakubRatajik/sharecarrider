package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.Set;

/**
 * @author Jakub Ratajik
 */
public class PassengerTableModel extends ShareCarRiderTableModel<Passenger> {

    public PassengerTableModel(Repository<Passenger> repository) {
        super(new String[]{"Jméno", "Příjmení", "Telefon", "Kategorie"}, SampleUsage.getPassengers(), repository);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 3) {
            return Set.class;
        }

        return String.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        Passenger passenger = data.get(row);

        switch (col) {
            case 0 -> value = passenger.getFirstName();
            case 1 -> value = passenger.getLastName();
            case 2 -> value = passenger.getPhoneNumber();
            case 3 -> value = passenger.getCategoryNames();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Passenger passenger = data.get(row);

        switch (col) {
            case 0 -> passenger.setFirstName((String) attribute);
            case 1 -> passenger.setLastName((String) attribute);
            case 2 -> passenger.setPhoneNumber((String) attribute);
            case 3 ->
                    passenger.setCategories((Set<PassengerCategory>) attribute);
        }
    }

}

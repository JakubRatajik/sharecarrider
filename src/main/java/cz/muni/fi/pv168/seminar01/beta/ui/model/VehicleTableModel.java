package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

/**
 * @author Jakub Ratajik
 */
public class VehicleTableModel extends ShareCarRiderTableModel<Vehicle> {
    public static final int COLUMN_BRAND = 0;
    public static final int COLUMN_TYPE = 1;
    public static final int COLUMN_CAPACITY = 2;
    public static final int COLUMN_AVERAGE_CONSUMPTION = 3;

    public VehicleTableModel(Repository<Vehicle> repository) {
        super(new String[]{"Značka", "Typ", "Počet míst", "Průměrná spotřeba"}, repository);
    }


    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case COLUMN_CAPACITY -> Integer.class;
            case COLUMN_AVERAGE_CONSUMPTION -> Double.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        Vehicle vehicle = repository.findByIndex(row).orElse(null);
        if (vehicle == null) {
            throw new NullPointerException("Vehicle cannot be null at this point (VTM -> getValueAt)");
        }

        return switch (col) {
            case COLUMN_BRAND -> vehicle.getBrand();
            case COLUMN_TYPE -> vehicle.getType();
            case COLUMN_CAPACITY -> vehicle.getCapacity();
            case COLUMN_AVERAGE_CONSUMPTION -> vehicle.getConsumption();
            default -> throw new IllegalStateException("Unexpected value: " + col);
        };
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Vehicle vehicle = data.get(row);

        switch (col) {
            case COLUMN_BRAND -> vehicle.setBrand((String) attribute);
            case COLUMN_TYPE -> vehicle.setType((String) attribute);
            case COLUMN_CAPACITY -> vehicle.setCapacity((int) attribute);
            case COLUMN_AVERAGE_CONSUMPTION -> vehicle.setConsumption((double) attribute);
        }
    }
}

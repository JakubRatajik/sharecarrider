package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

/**
 * @author Jakub Ratajik
 */
public class VehicleTableModel extends ShareCarRiderTableModel<Vehicle> {
    public static final int COLUMN_CAPACITY = 2;
    public static final int COLUMN_AVERAGE_CONSUMPTION = 3;
    public VehicleTableModel() {
        super(new String[]{"Značka", "Typ", "Počet míst", "Průměrná spotřeba"}, SampleUsage.getVehicles());
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case COLUMN_CAPACITY -> Integer.class;
            case COLUMN_AVERAGE_CONSUMPTION -> Float.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        Vehicle vehicle = data.get(row);

        switch (col) {
            case 0 -> value = vehicle.getBrand();
            case 1 -> value = vehicle.getType();
            case 2 -> value = vehicle.getCapacity();
            case 3 -> value = vehicle.getConsumption();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Vehicle vehicle = data.get(row);

        switch (col) {
            case 0 -> vehicle.setBrand((String) attribute);
            case 1 -> vehicle.setType((String) attribute);
            case 2 -> vehicle.setCapacity((int) attribute);
            case 3 -> vehicle.setConsumption((float) attribute);
        }
    }
}

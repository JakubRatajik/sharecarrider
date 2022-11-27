package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

/**
 * @author Jakub Ratajik
 */
public class VehicleTableModel extends ShareCarRiderTableModel<Vehicle> {
    public static final int COLUMN_BRAND = 0;
    public static final int COLUMN_TYPE = 1;
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
        Vehicle vehicle = data.get(row);

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
            case COLUMN_AVERAGE_CONSUMPTION -> vehicle.setConsumption((float) attribute);
        }
    }
}

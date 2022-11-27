package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


/**
 * @author Jakub Ratajik
 */
public class RideTableModel extends ShareCarRiderTableModel<Ride> {
    public static final int COLUMN_DATE = 0;
    public static final int DEPARTURE = 1;
    public static final int COLUMN_DISTANCE = 4;

    public RideTableModel() {
        super(new String[]{"Datum", "Odjezd", "Začátek", "Cíl", "Vzdálenost", "Kategorie"}, SampleUsage.getRides());

    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> columnClass;

        switch (col) {
            case COLUMN_DATE -> columnClass = LocalDate.class;
            case DEPARTURE -> columnClass = LocalTime.class;
            case COLUMN_DISTANCE -> columnClass = Integer.class;
            default -> columnClass = String.class;
        }

        return columnClass;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        Ride ride = data.get(row);

        switch (col) {
            case 0 -> value = ride.getDate();
            case 1 -> value = ride.getDeparture();
            case 2 -> value = ride.getFrom();
            case 3 -> value = ride.getTo();
            case 4 -> value = ride.getDistance();
            case 5 -> value = ride.getCategoryNames();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Ride ride = data.get(row);

        switch (col) {
            case 0 -> ride.setDate((LocalDate) attribute);
            case 1 -> ride.setDeparture((LocalTime) attribute);
            case 2 -> ride.setFrom((String) attribute);
            case 3 -> ride.setTo((String) attribute);
            case 4 -> ride.setDistance((Integer) attribute);
            case 5 -> ride.setCategories((Collection<RideCategory>) attribute);
        }
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui.model;

import cz.muni.fi.pv168.seminar01.beta.data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
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
    public static final int COLUMN_DEPARTURE = 1;
    public static final int COLUMN_FROM = 2;
    public static final int COLUMN_TO = 3;
    public static final int COLUMN_DISTANCE = 4;
    public static final int COLUMN_CATEGORIES = 5;

    public RideTableModel(Repository<Ride> repository) {
        super(new String[]{"Datum", "Odjezd", "Začátek", "Cíl", "Vzdálenost", "Kategorie"}, repository);

    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> columnClass;

        switch (col) {
            case COLUMN_DATE -> columnClass = LocalDate.class;
            case COLUMN_DEPARTURE -> columnClass = LocalTime.class;
            case COLUMN_DISTANCE -> columnClass = Integer.class;
            default -> columnClass = String.class;
        }

        return columnClass;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value;
        Ride ride = repository.findByIndex(row).orElse(null);
        if (ride == null) {
            throw new NullPointerException("Vehicle cannot be null at this point (RTM -> getValueAt)");
        }

        switch (col) {
            case COLUMN_DATE -> value = ride.getDate();
            case COLUMN_DEPARTURE -> value = ride.getDepartureFormatted();
            case COLUMN_FROM -> value = ride.getFrom();
            case COLUMN_TO -> value = ride.getTo();
            case COLUMN_DISTANCE -> value = ride.getDistance();
            case COLUMN_CATEGORIES -> value = ride.getCategoryNames();
            default -> value = null;
        }

        return value;
    }

    @Override
    public void setValueAt(Object attribute, int row, int col) {
        Ride ride = data.get(row);

        switch (col) {
            case COLUMN_DATE -> ride.setDate((LocalDate) attribute);
            case COLUMN_DEPARTURE -> ride.setDeparture((LocalTime) attribute);
            case COLUMN_FROM -> ride.setFrom((String) attribute);
            case COLUMN_TO -> ride.setTo((String) attribute);
            case COLUMN_DISTANCE -> ride.setDistance((Integer) attribute);
            case COLUMN_CATEGORIES -> ride.setCategories((Collection<RideCategory>) attribute);
        }
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jakub Ratajik
 */
public class DateTimeUtils {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd. MM. yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static boolean isBeforeOrEqual(LocalTime tOne, LocalTime tTwo){
        return tOne.isBefore(tTwo) || tOne.equals(tTwo);
    }

    public static boolean isAfterOrEqual(LocalTime tOne, LocalTime tTwo){
        return tOne.isAfter(tTwo) || tOne.equals(tTwo);
    }
}

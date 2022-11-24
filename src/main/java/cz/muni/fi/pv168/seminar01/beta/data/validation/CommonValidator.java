package cz.muni.fi.pv168.seminar01.beta.data.validation;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * @author Jakub Ratajik
 */
public class CommonValidator {
    private CommonValidator() {
    }

    public static boolean isValidLongAlphaSpaceString(String string) {
        return string.matches("^[a-zA-Z ]{1,100}$");
    }

    public static boolean isValidDateParsing(String date) {
        try {
            LocalDate.parse(date.trim());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidIntParsing(String num) {
        try {
            Integer.parseInt(num.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidFloatParsing(String num) {
        try {
            Float.parseFloat(num.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidTimeParsing(String time) {
        try {
            LocalTime.parse(time.trim());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidLongParsing(String num) {
        try {
            Long.parseLong(num.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.validation;


import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jakub Ratajik
 */
public class CommonValidator {
    private CommonValidator() {
    }

    /**
     * Checks if given string contains only czech characters, space, and consists
     * of exactly 1-100 characters.
     *
     * @param string to validate
     * @return true if string satisfies given conditions, false otherwise.
     */
    public static boolean isValidLongCzechInput(String string) {
        return string.matches("^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓ a-zA-Z]{1,100}$");
    }

    /**
     * Checks if given string contains only czech alphanumeric characters, space,
     * and consists of exactly 1-100 characters.
     *
     * @param string to validate
     * @return true if string satisfies given requirements, false otherwise.
     */
    public static boolean isValidLongCzechNumInput(String string) {
        return string.matches("^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓ a-zA-Z0-9]{1,100}$");
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

    /**
     * Checks whether given string can represent a list with IDs - numeric values.
     * Makes sure that there is no category with the given IDs yet.
     *
     * @param categoryIdListString string possibly representing list of IDs
     * @param tableCategory        which type of category is it
     * @return true if categoryIdListString satisfies given requirements, false otherwise.
     */
    public static boolean isValidIdList(String categoryIdListString, TableCategory tableCategory) {
        if (categoryIdListString.matches("\\[]")) {
            return true;
        }
        if (!categoryIdListString.matches("\\[([0-9]+)(, [0-9]+)*]")) {
            return false;
        }

        List<Integer> categoryIdList = Arrays.stream(
                        categoryIdListString
                                .substring(1, categoryIdListString.length() - 1)
                                .split(", "))
                .map(Integer::parseInt)
                .toList();

        return categoryIdList
                .stream()
                .allMatch(categoryId -> Shortcut.getTableModel(tableCategory).getObjectById(categoryId) != null);
    }
}

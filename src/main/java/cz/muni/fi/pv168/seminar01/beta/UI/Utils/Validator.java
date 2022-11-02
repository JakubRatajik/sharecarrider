package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

/**
 * @author Jakub Ratajik
 */
public class Validator {
    public static boolean isValidIntegerNumberInput(String text) {
        return text.matches("\s*[1-9][0-9]*\s*");
    }

    public static boolean isValidRealNumberInput(String text) {
        return text.matches("\s*([0-9]*[.])?[0-9]+\s*");
    }
}

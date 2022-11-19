package cz.muni.fi.pv168.seminar01.beta.data.validation;

/**
 * @author Jakub Ratajik
 */
public class CommonValidator {
    private CommonValidator() {
    }

    public static boolean isValidLongAlphaSpaceString(String string) {
        return string.matches("^[a-zA-Z ]{1,100}$");
    }


}

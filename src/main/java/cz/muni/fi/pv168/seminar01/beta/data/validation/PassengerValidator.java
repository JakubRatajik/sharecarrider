package cz.muni.fi.pv168.seminar01.beta.data.validation;

/**
 * @author Jakub Ratajik
 */
public class PassengerValidator {
    private PassengerValidator() {
    }

    public static void validatePassenger(String id, String firstName, String lastName, String phoneNumber, String categories) {
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new ValidationException("Telefónní číslo musí být ve formátu '712345678' nebo '+421123456789'.");
        }
        if (!CommonValidator.isValidLongCzechInput(firstName)
                || !CommonValidator.isValidLongCzechInput(lastName)) {
            throw new ValidationException("Jméno musí obsahovat pouze česká písmena, mezeru a mít nejvýše 100 znaků.");
        }
    }

    public static void validatePassenger(String firstName, String lastName, String phoneNumber) {
        validatePassenger(null, firstName, lastName, phoneNumber, null);
    }

    private static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^(\\+\\d{3})?\\d{9}$");
    }
}

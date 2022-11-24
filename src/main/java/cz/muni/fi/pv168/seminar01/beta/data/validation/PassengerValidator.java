package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

/**
 * @author Jakub Ratajik
 */
public class PassengerValidator {
    private PassengerValidator() {
    }

    public static void validatePassenger(String id, String firstName, String lastName, String phoneNumber, String categories) {
        if (id != null
                && !isValidPassengerId(id)) {
            throw new ValidationException("Neplatné ID pasažéra.");
        }
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new ValidationException("Telefónní číslo musí být ve formátu '712345678' nebo '+421123456789'.");
        }
        if (!CommonValidator.isValidLongCzechInput(firstName)
                || !CommonValidator.isValidLongCzechInput(lastName)) {
            throw new ValidationException("Jméno musí obsahovat pouze česká písmena, mezeru a mít nejvýše 100 znaků.");
        }
        if (categories != null
                && !CommonValidator.isValidIdList(categories, TableCategory.PASSENGER_CATEGORY)) {
            throw new ValidationException("Nesprávný formát kategorií cestujícího.");
        }
    }

    private static boolean isValidPassengerId(String id) {
        return CommonValidator.isValidLongParsing(id)
                && Shortcut.getTableModel(TableCategory.PASSENGERS).getObjectById(Long.parseLong(id)) == null;
    }

    public static void validatePassenger(String firstName, String lastName, String phoneNumber) {
        validatePassenger(null, firstName, lastName, phoneNumber, null);
    }

    private static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^(\\+\\d{3})?\\d{9}$");
    }
}

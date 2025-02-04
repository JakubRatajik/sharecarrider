package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

public class CategoryValidator {
    private CategoryValidator() {
    }

    private static boolean isCategoryValid(String id, String name, TableCategory tableCategory) {
        return id != null
                && CommonValidator.isValidLongParsing(id);
    }

    public static void validateRideCategory(String id, String name) {
        if (isCategoryValid(id, name, TableCategory.RIDE_CATEGORY)) {
            return;
        }
        throw new ValidationException("Neplatné ID Kategorie jízd.");
    }

    public static void validatePassengerCategory(String id, String name) {
        if (isCategoryValid(id, name, TableCategory.PASSENGER_CATEGORY)) {
            return;
        }
        throw new ValidationException("Neplatné ID Kategorie pasažérů.");
    }
}

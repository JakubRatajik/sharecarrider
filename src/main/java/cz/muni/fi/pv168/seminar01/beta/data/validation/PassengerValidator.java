package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Ratajik
 */
public class PassengerValidator {
    private PassengerValidator() {
    }

    public static void validatePassenger(String firstName, String lastName, String phoneNumber, String categories) {
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new ValidationException("Telefónní číslo musí být ve formátu '712345678' nebo '+421123456789'.");
        }
        if (!CommonValidator.isValidLongAlphaSpaceString(firstName)
                || !CommonValidator.isValidLongAlphaSpaceString(lastName)) {
            throw new ValidationException("Jméno musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
        }
        if (categories == null) {
            return;
        }
        if (!isCategoryListValid(categories)) {
            throw new ValidationException("Nesprávný formát kategorií.");
        }
    }

    private static boolean isCategoryListValid(String categories) {
        if (!categories.matches("\\[(([a-zA-z]+)(, [a-zA-z]+)*)*]")) {
            return false;
        }
        List<String> categoriesList = Arrays.asList(
                categories.substring(1, categories.length() - 1).split(", "));
        return !categoriesList.stream()
                .map(PassengerCategory::fromString)
                .collect(Collectors.toSet())
                .contains(null);
    }

    public static void validatePassenger(String firstName, String lastName, String phoneNumber) {
        validatePassenger(firstName, lastName, phoneNumber, null);
    }

    private static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^(\\+\\d{3})?\\d{9}$");
    }
}

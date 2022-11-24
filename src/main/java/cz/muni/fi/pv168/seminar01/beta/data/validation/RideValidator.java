package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Jakub Ratajik
 */
public class RideValidator {
    //TODO: add categories validation
    private RideValidator() {
    }

    public static void validateRide(String id, String date, String departure, String arrival, String from, String to, String distance, String categories,
                                    String passengers, String vehicleID, String repetition, String description) {
        // ID == null -> validation is called from addRide action, thus no ID exists yet
        if (id != null && !isValidRideId(id)) {
            throw new ValidationException("Neplatné ID jízdy.");
        }
        if (date != null && !CommonValidator.isValidDateParsing(date)) {
            throw new ValidationException("Nesprávný formát datumu jízdy.");
        }
        if (!CommonValidator.isValidTimeParsing(departure)
                || !CommonValidator.isValidTimeParsing(arrival)) {
            throw new ValidationException("Nesprávný formát času.");
        }
        if (100 < from.length() || 100 < to.length()) {
            throw new ValidationException("Místo odjezdu a příjezdu musí mít nejvýše 100 znaků.");
        }
        if (!CommonValidator.isValidIntParsing(distance)) {
            throw new ValidationException("Vzdálenost musí být celé číslo.");
        }
        if (categories != null
                && !CommonValidator.isValidIdList(categories, TableCategory.RIDE_CATEGORY)) {
            throw new ValidationException("Nesprávný formát kategorií jízdy.");
        }
        if (passengers != null
                && !CommonValidator.isValidIdList(passengers, TableCategory.PASSENGERS)) {
            throw new ValidationException("Neplatný seznam pasažérů jízdy.");
        }
        if (vehicleID != null
                && !isVehicleIDValid(vehicleID)) {
            throw new ValidationException("Neplatné ID vozidla jízdy.");
        }
        if (repetition != null
                && !isRepetitionValid(repetition)) {
            throw new ValidationException("Neplatné opakování jízdy.");
        }
        if (description.length() > 300) {
            throw new ValidationException("Maximální délka popisu je 300 znaků.");
        }
    }

    private static boolean isValidRideId(String id) {
        return CommonValidator.isValidFloatParsing(id)
                && Shortcut.getTableModel(TableCategory.RIDES).getObjectById(Long.parseLong(id)) == null;
    }

    public static void validateRide(String departure, String arrival, String from, String to, String distance, String description) {
        validateRide(null, null, departure, arrival, from, to, distance, null, null, null, null, description);
    }

    private static boolean isVehicleIDValid(String string) {
        if (!CommonValidator.isValidLongParsing(string)) {
            return false;
        }
        return Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(string)) != null;
    }

    private static boolean isRepetitionValid(String repetition) {
        return Arrays.stream(Repetition.values())
                .map(Repetition::name)
                .collect(Collectors.toSet())
                .contains(repetition);
    }
}

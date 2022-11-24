package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
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
        if (id == null || !CommonValidator.isValidFloatParsing(id) || Shortcut.getTableModel(TableCategory.RIDES).getObjectById(Long.parseLong(id)) != null) {
            throw new ValidationException("Neplatné ID jízdy.");
        }
        if (date != null && !CommonValidator.isValidDateParsing(date)) {
            throw new ValidationException("Nesprávný formát datumu jízdy.");
        }
        if (!CommonValidator.isValidTimeParsing(departure)
                || !CommonValidator.isValidTimeParsing(arrival)) {
            throw new ValidationException("Nesprávný formát času.");
        }
//        if (!CommonValidator.isValidLongAlphaSpaceString(from)
//                || !CommonValidator.isValidLongAlphaSpaceString(to)) {
//            throw new ValidationException("Místo odjezdu a příjezdu musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
//        }
        // --TODO kvůli tomuto padá import
        if (!CommonValidator.isValidIntParsing(distance)) {
            throw new ValidationException("Vzdálenost musí být celé číslo.");
        }
        if (categories != null && !isCategoryListValid(categories)) {
            throw new ValidationException("Nesprávný formát kategorií jízdy.");
        }
        if (passengers != null && !isPassengerIdListValid(passengers)) {
            throw new ValidationException("Nesprávný formát pasažérů jízdy.");
        }
//        if (vehicleID != null && !isVehicleIDValid(vehicleID)) {
//            throw new ValidationException("Neplatné ID vozidla jízdy.");
//        }
        if (description.length() > 300) {
            throw new ValidationException("Maximální délka popisu je 300 znaků.");
        }
    }

    private static boolean isCategoryListValid(String categoryList) {
        //TODO: change, after implementing categories
        return true;
    }

    public static void validateRide(String date, String departure, String arrival, String from, String to, String distance, String categories,
                                    String passengers, String vehicleID, String repetition, String description) {
        validateRide(null, date, departure, arrival, from, to, distance, categories, passengers, vehicleID, repetition, description);
    }

    public static void validateRide(String departure, String arrival, String from, String to, String distance, String description) {
        validateRide(null, null, departure, arrival, from, to, distance, null, null, null, null, description);
    }

    private static boolean isVehicleIDValid(String string) {
        if (!CommonValidator.isValidLongParsing(string)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(string));

        return vehicle != null;
    }

    private static boolean isPassengerIdListValid(String listString) {
        if (!listString.matches("\\[(([0-9]+)(, [0-9]+)*)*]")) {
            return false;
        }
        String[] passengerIds = listString.substring(1, listString.length() - 1).split(", ");

        if (listString.length() > 2) {
            for (String passenger : passengerIds) {
                Passenger passengerObject = (Passenger) Shortcut.getTableModel(TableCategory.PASSENGERS).getObjectById(Long.parseLong(passenger));
                if (passengerObject == null) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRepetitionValid(String repetition) {
        return !Arrays.stream(Repetition.values())
                .map(Repetition::name)
                .collect(Collectors.toSet())
                .contains(repetition.trim());
    }
}

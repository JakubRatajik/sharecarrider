package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.DateTimeUtils;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Ratajik
 */
public class RideValidator {
    private RideValidator() {
    }

    public static void validateRide(String id, String date, String departure, String arrival, String from, String to, String distance, String categories,
                                    String passengers, String vehicleID, String repetition, String description) {
        if (date == null || !CommonValidator.isValidDateParsing(date)) {
            throw new ValidationException("Nesprávný formát datumu jízdy.");
        }
        validateRide(id, LocalDate.parse(date), departure, arrival, from, to, distance, categories,
                        passengers, vehicleID, repetition, description);
    }

    public static void validateRide(String id, LocalDate date, String departure, String arrival, String from, String to, String distance, String categories,
                                    String passengers, String vehicleID, String repetition, String description) {
        // ID == null -> validation is called from addRide action, thus no ID exists yet
        if (id != null && !isValidRideId(id)) {
            throw new ValidationException("Neplatné ID jízdy.");
        }
        if (!validTimeInputFormat(departure, arrival)) {
            throw new ValidationException("Nesprávný formát času.");
        }
        if (LocalTime.parse(arrival).isBefore(LocalTime.parse(departure))) {
            throw new ValidationException("Čas příjezdu nemůže být před časem odjezdu.");
        }
        if (anotherRidePlannedForTimePeriod(id, date, departure, arrival)) {
            throw new ValidationException("Na tenhle čas máte již naplánovanou jinou jízdu.");
        }
        if (100 < from.length() || 100 < to.length()) {
            throw new ValidationException("Místo odjezdu a příjezdu musí mít nejvýše 100 znaků.");
        }

        if (!CommonValidator.isValidIntParsing(distance)) {
            throw new ValidationException("Vzdálenost musí být celé číslo.");
        }
        /*
        if (categories != null
                && !CommonValidator.isValidIdList(categories, TableCategory.RIDE_CATEGORY)) {
            throw new ValidationException("Nesprávný formát kategorií jízdy.");
        }*/ //--TODO-- non functional import because of this
        /*
        if (passengers != null
                && !CommonValidator.isValidIdList(passengers, TableCategory.PASSENGERS)) {
            throw new ValidationException("Neplatný seznam pasažérů jízdy.");
        }
        */ // TODO-- non functional import because of this
        /*
        if (vehicleID != null
                && !isVehicleIDValid(vehicleID)) {
            throw new ValidationException("Neplatné ID vozidla jízdy.");
        }
        */ // TODO-- non functional import because of this
        if (repetition != null
                && !isRepetitionValid(repetition)) {
            throw new ValidationException("Neplatné opakování jízdy.");
        }
        if (description.length() > 300) {
            throw new ValidationException("Maximální délka popisu je 300 znaků.");
        }
    }
    // TODO - make clear which validateRide method is for what and when it's used
    public static void validateRide(String id, LocalDate date, String departure, String arrival, String from, String to, String distance, String description) {
        validateRide(id, date, departure, arrival, from, to, distance, null, null, null, null, description);
    }


    private static boolean anotherRidePlannedForTimePeriod(String idString, LocalDate date, String departureString, String arrivalString) {
        LocalTime departure = LocalTime.parse(departureString);
        LocalTime arrival = LocalTime.parse(arrivalString);
        List<Ride> allRides = new ArrayList<>();

        try {
            allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();
        }
        catch (NullPointerException e) {
            return false;
        }

        if (idString == null) {
            return allRides.stream()
                    .filter(ride -> ride.getDate().isEqual(date))
                    .anyMatch(ride -> ((DateTimeUtils.isBeforeOrEqual(ride.getDeparture(), arrival) && DateTimeUtils.isAfterOrEqual(ride.getArrival(), departure))
                            || (DateTimeUtils.isBeforeOrEqual(ride.getArrival(), arrival) && DateTimeUtils.isAfterOrEqual(ride.getDeparture(), departure))));

        } else {
            long id = Long.parseLong(idString);

            return allRides.stream()
                    .filter(ride -> ride.getDate().isEqual(date))
                    .filter(ride -> ride.getId() != id)
                    .anyMatch(ride -> ((DateTimeUtils.isBeforeOrEqual(ride.getDeparture(), arrival) && DateTimeUtils.isAfterOrEqual(ride.getArrival(), departure))
                            || (DateTimeUtils.isBeforeOrEqual(ride.getArrival(), arrival) && DateTimeUtils.isAfterOrEqual(ride.getDeparture(), departure))));

        }
   }

    private static boolean validTimeInputFormat(String departure, String arrival) {
        return CommonValidator.isValidTimeParsing(departure)
                && CommonValidator.isValidTimeParsing(arrival);
    }

    private static boolean isValidRideId(String id) {
        // TODO - uncomment and fix validation for edit rides
        return CommonValidator.isValidDoubleParsing(id);
                //&& Shortcut.getTableModel(TableCategory.RIDES).getObjectById(Long.parseLong(id)) == null;
    }

    private static boolean isVehicleIDValid(String string) {
        if (!CommonValidator.isValidLongParsing(string)) {
            return false;
        }
        return CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(string)) != null;
    }

    private static boolean isRepetitionValid(String repetition) {
        return Arrays.stream(Repetition.values())
                .map(Repetition::name)
                .collect(Collectors.toSet())
                .contains(repetition);
    }
}

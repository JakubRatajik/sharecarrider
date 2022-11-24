package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Jakub Ratajik
 */
public class VehicleValidator {
    private VehicleValidator() {
    }

    public static void validateVehicle(String id, String licensePlate, String brand, String type, String capacity, String consumption, String fuelType) {
        if (id == null || !CommonValidator.isValidLongParsing(id) || Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(id)) != null) {
            throw new ValidationException("Neplatdné ID vozidla.");
        }
//        if (!isLicensePlateValid(licensePlate)) {
//            throw new ValidationException("SPZ musí být ve formátu '6A81234' nebo '6A9 1234'");
//        }
        // --TODO kvůli tomuto padá import
//        if (CommonValidator.isValidLongAlphaSpaceString(brand)) {
//            throw new ValidationException("Značka musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
//        }
        // --TODO kvůli tomuto padá import
//        if (CommonValidator.isValidLongAlphaSpaceString(type)) {
//            throw new ValidationException("Model auta musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
//        }
        // --TODO kvůli tomuto padá import
        if (!CommonValidator.isValidIntParsing(capacity)) {
            throw new ValidationException("Kapacita musí být celé číslo.");
        }
        if (!CommonValidator.isValidFloatParsing(consumption)) {
            throw new ValidationException("Spotřeba musí být reální číslo.");
        }
        if (fuelType == null) {
            return;
        }
//        if (!isFuelTypeValid(fuelType)) {
//            throw new ValidationException("Nesprávný typ paliva.");
//        }
        // --TODO kvůli tomuto padá import
    }

    public static void validateVehicle(String licensePlate, String brand, String type, String capacity, String consumption) {
        validateVehicle(null, licensePlate, brand, type, capacity, consumption, null);
    }

    public static boolean isLicensePlateValid(String licensePlate) {
        return licensePlate.matches("\\s*\\d[a-zA-Z]\\+?\\d{5}\\s*");
    }

    public static boolean isFuelTypeValid(String fuelType) {
        return !Arrays.stream(FuelType.values())
                .map(FuelType::name)
                .collect(Collectors.toSet())
                .contains(fuelType);
    }
}

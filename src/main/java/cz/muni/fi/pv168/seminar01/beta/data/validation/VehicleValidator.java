package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
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
//        TODO: uncomment after validation without id but with fuel
//        if (id != null && !isValidVehicleId(id)) {
//            throw new ValidationException("Neplatdné ID vozidla.");
//        }
//       TODO: uncomment after implementing database - providing reasonable license plates
//        if (!isLicensePlateValid(licensePlate)) {
//            throw new ValidationException("SPZ musí být ve formátu '6A81234' nebo '6A9 1234'");
//        }
        if (!CommonValidator.isValidLongCzechNumInput(brand)) {
            throw new ValidationException("Značka musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
        }
        if (!CommonValidator.isValidLongCzechNumInput(type)) {
            throw new ValidationException("Model auta musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
        }
        if (!CommonValidator.isValidIntParsing(capacity)) {
            throw new ValidationException("Kapacita musí být celé číslo.");
        }
        if (!CommonValidator.isValidDoubleParsing(consumption)) {
            throw new ValidationException("Spotřeba musí být reální číslo.");
        }
        if (fuelType != null
                && !isFuelTypeValid(fuelType)) {
            throw new ValidationException("Nesprávný typ paliva.");
        }
    }

    private static boolean isValidVehicleId(String id) {
        return CommonValidator.isValidLongParsing(id)
                && Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(id)) == null;
    }

    public static void validateVehicle(String licensePlate, String brand, String type, String capacity, String consumption) {
        validateVehicle(null, licensePlate, brand, type, capacity, consumption, null);
    }

    public static boolean isLicensePlateValid(String licensePlate) {
        return licensePlate.matches("\\s*\\d[a-zA-Z]\\+?\\d{5}\\s*");
    }

    public static boolean isFuelTypeValid(String fuelType) {
        return Arrays.stream(FuelType.values())
                .map(FuelType::name)
                .collect(Collectors.toSet())
                .contains(fuelType);
    }
}

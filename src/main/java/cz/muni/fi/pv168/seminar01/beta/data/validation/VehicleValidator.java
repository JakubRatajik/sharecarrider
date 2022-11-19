package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Jakub Ratajik
 */
public class VehicleValidator {
    private VehicleValidator() {}

    public static void validateVehicle(String licensePlate, String brand, String type, String capacity, String consumption, String fuelType) {
        if (!isLicensePlateValid(licensePlate)) {
            throw new ValidationException("SPZ musí být ve formátu '6A81234' nebo '6A9 1234'");
        }
        if (CommonValidator.isValidLongAlphaSpaceString(brand)) {
            throw new ValidationException("Značka musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
        }
        if (CommonValidator.isValidLongAlphaSpaceString(type)) {
            throw new ValidationException("Model auta musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
        }
        if (!isCapacityValid(capacity)) {
            throw new ValidationException("Kapacita musí být celé číslo menší než 200.");
        }
        if (!isConsumptionValid(consumption)){
            throw new ValidationException("Spotřeba musí být reální číslo s bodkou/čárkou a 2 desetinnýma místy.");
        }
        if (fuelType == null) {
            return;
        }
        if (!isFuelTypeValid(fuelType)) {
            throw new ValidationException("Nesprávný typ paliva.");
        }
    }

    public static void validateVehicle(String licensePlate, String brand, String type, String capacity, String consumption) {
        validateVehicle(licensePlate, brand, type, capacity, consumption, null);
    }

    public static boolean isConsumptionValid(String consumption) {
        return consumption.matches("\\s*\\d+([,.][0-9]{1,2})?\\s*");
    }

    public static boolean isCapacityValid(String capacity) {
        return capacity.matches("\\s*\\d{1,2}\\s*");
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

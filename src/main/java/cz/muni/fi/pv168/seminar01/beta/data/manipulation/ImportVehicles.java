package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportVehicles {

    static List<Vehicle> importVehicles(File vehicles) throws FileNotFoundException {
        Scanner reader = new Scanner(vehicles);
        List<Vehicle> vehicleList = new ArrayList<>();
        while (reader.hasNextLine()) {
            vehicleList.add(importVehicle(ManipulationUtils.splitter(reader.nextLine())));
        }
        return vehicleList;
    }

    private static Vehicle importVehicle(String[] lineSplit) {
        ManipulationUtils.trimAllStringsInArray(lineSplit);

        if (lineSplit.length != ManipulationUtils.VEHICLE_PARAMETERS_COUNT) {
            new ErrorDialog(MainWindow.getFrame(), "Špatný počet atributů v CSV u vozidla");
            throw new DataManipulationException("Špatný počet parametrů pro Vozidlo v CSV, mělo být " + ManipulationUtils.VEHICLE_PARAMETERS_COUNT + ", bylo " + lineSplit.length, new Exception());
        }

        String idString = lineSplit[0];
        String licencePlate = lineSplit[1];
        String brand = lineSplit[2];
        String model = lineSplit[3];
        String seatsString = lineSplit[4];
        String consumptionString = lineSplit[5];
        String fuelTypeString = lineSplit[6];

        try {
            VehicleValidator.validateVehicle(idString, licencePlate, brand, model, seatsString, consumptionString, fuelTypeString);
        } catch (ValidationException e) {
            throw new DataManipulationException("Problém s načtením vozidel.", e);
        }
        return new Vehicle(Long.parseLong(idString), licencePlate, brand, model, Integer.parseInt(seatsString), Float.parseFloat(consumptionString), FuelType.valueOf(fuelTypeString));
    }
}

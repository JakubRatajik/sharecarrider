package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.PassengerValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.VehicleTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ImporterBase {
    private static final int VEHICLE_PARAMETERS_COUNT = 7;
    private static final int PASSENGER_PARAMETERS_COUNT = 5;
    private static final int RIDE_PARAMETERS_COUNT = 12;

    private static String[] splitter(String lineToSplit) {
        return lineToSplit.split(AbstractExporter.SEPARATOR);
    }

    public static void loadData(File rides, File vehicles, File passengers) {
        try {
            importVehicles(vehicles);
            importPassengers(passengers);
            importRides(rides);
        } catch (Exception e) {
            return;
        }
    }

    private static void importVehicles(File vehicles) throws FileNotFoundException {
        Scanner reader = new Scanner(vehicles);
        while (reader.hasNextLine()) {
            importVehicle(splitter(reader.nextLine()));
        }
    }

    public static void importPassengers(File passengers) throws FileNotFoundException {
        Scanner reader = new Scanner(passengers);
        while (reader.hasNextLine()) {
            importPassenger(splitter(reader.nextLine()));
        }
    }

    public static void importRides(File rides) throws FileNotFoundException {
        Scanner reader = new Scanner(rides);
        while (reader.hasNextLine()) {
            importRide(splitter(reader.nextLine()));
        }
    }

    public static String[] listParser(String list) {
        list = list.substring(1, list.length() - 1);
        return list.split(", ");
    }

    public static void importRide(String[] lineSplit) {
        trimAllStringsInArray(lineSplit);
        if (lineSplit.length != RIDE_PARAMETERS_COUNT) {
            return;
        }

//        String idString = lineSplit[0];
//        String dateString = lineSplit[1];
//        String departureString = lineSplit[2];
//        String arrivalString = lineSplit[3];
//        String from = lineSplit[4];
//        String to = lineSplit[5];
//        String distanceString = lineSplit[6];
//        String categoriesString = lineSplit[7];
//        String passengerListString = lineSplit[8];
//        String vehicleIdString = lineSplit[9];
//        String repetitionString = lineSplit[10];
//        String description = lineSplit[11];
//
//        try {
//            RideValidator.validateRide(idString, dateString, departureString, arrivalString, from, to,
//                    distanceString, categoriesString, passengerListString, vehicleIdString, repetitionString, description);
//        }
//        catch (ValidationException e) {
//            throw new DataManipulationException("Problém s načtením jízd.", e);
//        }
//
//        long id = Long.parseLong(idString);
//        LocalDate date = LocalDate.parse(dateString);
//        LocalTime departure = LocalTime.parse(departureString);
//        LocalTime arrival = LocalTime.parse(arrivalString);
//        int distance = Integer.parseInt(distanceString);
//        Set<RideCategory> categories = new HashSet<>();
//        Set<Passenger> passengers = new HashSet<>();
//        Vehicle vehicle = (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(Long.parseLong(vehicleIdString));
//        Repetition repetition = Enum.valueOf(Repetition.class, repetitionString);

//        // CATEGORIES
//        //            String cat = lineSplit[6];
//        //            if (cat.length() > 2) {
//        //                for (String category: listParser(cat)) {
//        //                    categorySet.add(RideCategory.fromString(category));
//        //                }
//        //            }
//
//        if (passengerListString.length() > 2) {
//            for (String passenger : listParser(passengerListString)) {
//                Passenger passengerObject = (Passenger) Shortcut.getTableModel(TableCategory.PASSENGERS).getObjectById(Long.parseLong(passenger));
//                passengers.add(passengerObject);
//            }
//        }
//
//        Ride newRide = new Ride(id, date, departure, arrival, from, to, distance, categories, passengers, vehicle, repetition, description);
//        ((RideTableModel) Shortcut.getTableModel(TableCategory.RIDES)).addRow(newRide);
//
        //TODO remove from: HERE
        long id = Long.parseLong(lineSplit[0]);
        LocalDate date = LocalDate.parse(lineSplit[1]);
        LocalTime time = LocalTime.parse(lineSplit[2]);
        String from = lineSplit[3];
        String where = lineSplit[4];
        int distance = Integer.parseInt(lineSplit[5]);
        String pass = lineSplit[7];
        Set<Passenger> passengerSet = new HashSet<>();
        if (pass.length() > 2) {
            for (String passenger : listParser(pass)) {
                Passenger passengerObject = (Passenger) Shortcut.getTableModel(TableCategory.PASSENGERS).getObjectById(Long.parseLong(passenger));
                passengerSet.add(passengerObject);
            }
        }

        long vehicleID = Long.parseLong(lineSplit[8]);
        Vehicle vehicleObject = (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(vehicleID);

        String rep = lineSplit[9];
        Repetition repetition = Enum.valueOf(Repetition.class, rep);


        ((RideTableModel) Shortcut.getTableModel(TableCategory.RIDES)).addRow(
                new Ride(id, date, time, null, from, where, distance, new HashSet<>(), passengerSet, vehicleObject, repetition, "description"));
        //TODO remove to: HERE
    }

    private static void importPassenger(String[] lineSplit) {
        if (lineSplit.length != PASSENGER_PARAMETERS_COUNT) {
            return;
        }

        String idString = lineSplit[0];
        String name = lineSplit[1];
        String surname = lineSplit[2];
        String phoneNumber = lineSplit[3];
        String categories = lineSplit[4];

        try {
            PassengerValidator.validatePassenger(idString, name, surname, phoneNumber, categories);
        }
        catch (ValidationException e) {
            throw new DataManipulationException("Problém s načtením pasažérů.", e);
        }

        Set<PassengerCategory> categorySet = new HashSet<>();
        if (categories.length() > 2) {
            for (String category : listParser(categories)) {
                categorySet.add(PassengerCategory.fromString(category));
            }

        }
        ((PassengerTableModel) Shortcut.getTableModel(TableCategory.PASSENGERS)).addRow(
                new Passenger(Long.parseLong(idString), name, surname, phoneNumber, categorySet));
    }

    private static void trimAllStringsInArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

    private static void importVehicle(String[] lineSplit) {
        trimAllStringsInArray(lineSplit);

        if (lineSplit.length != VEHICLE_PARAMETERS_COUNT) {
            return;
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
        }
        catch (ValidationException e) {
            throw new DataManipulationException("Problém s načtením pasažérů.", e);
        }

        ((VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES)).addRow(
                new Vehicle(Long.parseLong(idString), licencePlate, brand, model, Integer.parseInt(seatsString), Float.parseFloat(consumptionString), FuelType.valueOf(fuelTypeString)));
    }
}

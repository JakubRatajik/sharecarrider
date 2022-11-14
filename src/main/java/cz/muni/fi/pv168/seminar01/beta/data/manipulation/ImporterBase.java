package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
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
    private static final int VEHICLE_PARAMETERS = 7;
    private static final int PASSENGER_PARAMETERS = 5;
    private static final int RIDE_PARAMETERS = 10;

    private static String[] splitter(String toSplit) {
        return toSplit.split(AbstractExporter.SEPARATOR);
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


    public static void importRide(String[] split) {
        if (split.length == RIDE_PARAMETERS) {
            int id = tryToInt(split[0]);
            LocalDate date = LocalDate.parse(split[1]);
            LocalTime time = LocalTime.parse(split[2]);
            String from = split[3];
            String where = split[4];
            int distance = tryToInt(split[5]);

            // CATEGORIES
//            Set<PassengerCategory> categorySet = new HashSet<>();
//            String cat = split[6];
//            if (cat.length() > 2) {
//                for (String category: listParser(cat)) {
//                    categorySet.add(RideCategory.fromString(category));
//                }
//            }

            String pass = split[7];
            Set<Passenger> passengerSet = new HashSet<>();
            if (pass.length() > 2) {
                for (String passenger : listParser(pass)) {
                    Passenger passengerObject = (Passenger) Shortcut.getTableModel(TableCategory.PASSENGERS).getObjectById(tryToInt(passenger));
                    if (passengerObject == null) {
                        System.err.println("Import not successful - > Person with id " + pass + " is missing in list");
                    }
                    passengerSet.add(passengerObject);
                }
            }

            int vehicleID = tryToInt(split[8]);
            Vehicle vehicleObject = (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getObjectById(vehicleID);
            if (vehicleObject == null) {
                System.err.println("Import not successful - > Vehicle with id " + vehicleID + " is missing in list");
            }
            Vehicle vehicle = vehicleObject;

            String rep = split[9];
            Repetition repetition = Enum.valueOf(Repetition.class, rep);


            ((RideTableModel) Shortcut.getTableModel(TableCategory.RIDES)).addRow(
                    new Ride(id, date, time, from, where, distance, new HashSet<RideCategory>(), passengerSet, vehicle, repetition));
        }
    }

    private static void importPassenger(String[] split) {
        if (split.length == PASSENGER_PARAMETERS) {
            int id = tryToInt(split[0]);
            String name = split[1];
            String surname = split[2];
            String phoneNumber = split[3];
            String cat = split[4];
            Set<PassengerCategory> categorySet = new HashSet<>();
            if (cat.length() > 2) {
                for (String category : listParser(cat)) {
                    categorySet.add(PassengerCategory.fromString(category));
                }
            }

            ((PassengerTableModel) Shortcut.getTableModel(TableCategory.PASSENGERS)).addRow(
                    new Passenger(id, name, surname, phoneNumber, categorySet));
        }
    }


    private static void importVehicle(String[] split) {
        if (split.length == VEHICLE_PARAMETERS) {
            int id = tryToInt(split[0]);
            String licencePlate = split[1];
            String brand = split[2];
            String model = split[3];
            int seats = tryToInt(split[4]);
            float consumption = (float) tryToDouble(split[5]);
            FuelType type = tryToFuelType(split[6]);
            ((VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES)).addRow(
                    new Vehicle(id, licencePlate, brand, model, seats, consumption, type));
        }
    }

    private static int tryToInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (Exception e) {
            return -1;
        }

    }

    private static double tryToDouble(String integer) {
        try {
            return Double.parseDouble(integer);
        } catch (Exception e) {
            return -1;
        }
    }

    private static FuelType tryToFuelType(String integer) {
        try {
            return FuelType.valueOf(integer);
        } catch (Exception e) {
            return null;
        }

    }

}

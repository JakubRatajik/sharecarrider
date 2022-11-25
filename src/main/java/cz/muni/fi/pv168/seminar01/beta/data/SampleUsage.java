package cz.muni.fi.pv168.seminar01.beta.data;

import cz.muni.fi.pv168.seminar01.beta.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for example of usage of TestDataGenerator.
 */
public final class SampleUsage {
    private static final TestDataGenerator testDataGenerator = new TestDataGenerator();
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final List<Passenger> passengers = new ArrayList<>();
    private static final List<Ride> rides = new ArrayList<>();

    private static final List<PassengerCategory> PASSENGER_CATEGORIES = new ArrayList<>();

    private static final List<RideCategory> rideCategories = new ArrayList<>();

    private SampleUsage() {
        throw new AssertionError("This class is not intended for instantiation.");
    }

    /**
     * This method generates sample data.
     * 10 passengers
     * 4 vehicles
     * 35 rides
     * <p>
     * It's also an example of how to generate data with testDataGenerator.
     */
    public static void generateSampleData() {
        for (int i = 0; i < 10; i++) {
            passengers.add(testDataGenerator.createPassenger());
        }
        for (int i = 0; i < 4; i++) {
            vehicles.add(testDataGenerator.createVehicle());
        }
        for (int i = 0; i < 35; i++) {
            rides.add(testDataGenerator.createRide());
        }
        PASSENGER_CATEGORIES.addAll(testDataGenerator.getCategs());
        rideCategories.addAll(testDataGenerator.getRidecategs());

    }

    public static List<Vehicle> getVehicles() {
        return vehicles;
    }

    public static List<Fuel> getFuels() {
        List<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.DIESEL, new BigDecimal(42)));
        fuels.add(new Fuel(FuelType.GASOLINE, new BigDecimal(42)));
        fuels.add(new Fuel(FuelType.ELECTRICITY, new BigDecimal(42)));
        fuels.add(new Fuel(FuelType.LPG, new BigDecimal(42)));
        fuels.add(new Fuel(FuelType.CNG, new BigDecimal(42)));
        return fuels;
    }

    public static List<Passenger> getPassengers() {
        return passengers;
    }

    public static List<Ride> getRides() {
        return rides;
    }

    public static List<PassengerCategory> getPassengerCategories() { return PASSENGER_CATEGORIES;}

    public static List<RideCategory> getRideCategories() { return rideCategories;}
}

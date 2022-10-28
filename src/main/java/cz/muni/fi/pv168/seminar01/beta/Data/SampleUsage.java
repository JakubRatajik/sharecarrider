package cz.muni.fi.pv168.seminar01.beta.Data;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for example of usage of TestDataGenerator.
 */
public final class SampleUsage {
    private final TestDataGenerator testDataGenerator = new TestDataGenerator();
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Passenger> passengers = new ArrayList<>();
    private final List<Ride> rides = new ArrayList<>();

    public SampleUsage() {
        generateSampleData();
    }

    /**
     * This method generates sample data.
     * 10 passengers
     * 4 vehicles
     * 35 rides
     * <p>
     * It's also an example of how to generate data with testDataGenerator.
     */
    public void generateSampleData() {
        for (int i = 0; i < 10; i++) {
            passengers.add(testDataGenerator.createPassenger());
        }
        for (int i = 0; i < 4; i++) {
            vehicles.add(testDataGenerator.createVehicle());
        }
        for (int i = 0; i < 35; i++) {
            rides.add(testDataGenerator.createRide());
        }
    }

    public Object[] getVehicles() {
        return vehicles.toArray();
    }

    public Object[] getPassengers() {
        return passengers.toArray();
    }

    public Object[] getRides() {
        return rides.toArray();
    }
}

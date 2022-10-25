package cz.muni.fi.pv168.seminar01.beta.Data;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public final class SampleUsage {
    private final TestDataGenerator testDataGenerator = new TestDataGenerator();
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Passenger> passengers = new ArrayList<>();
    private final List<Ride> rides = new ArrayList<>();

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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Ride> getRides() {
        return rides;
    }
}

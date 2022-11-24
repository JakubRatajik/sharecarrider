package cz.muni.fi.pv168.seminar01.beta.wiring;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.FuelRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.PassengerCategoryRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.PassengerRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.RideCategoryRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.RideRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.VehicleRepository;
import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

/**
 * Dependency provider common for all environments
 */
public abstract class CommonDependencyProvider implements DependencyProvider {

    private final Repository<Ride> rides;
    private final Repository<Vehicle> vehicles;
    private final Repository<Passenger> passengers;
    private final Repository<RideCategory> rideCategories;
    private final Repository<PassengerCategory> passengerCategories;
    private final Repository<Fuel> fuels;

    protected CommonDependencyProvider(DatabaseManager databaseManager) {

        this.rides = new RideRepository();
        this.vehicles = new VehicleRepository();
        this.passengers = new PassengerRepository();
        this.passengerCategories = new PassengerCategoryRepository();
        this.rideCategories = new RideCategoryRepository();
        this.fuels = new FuelRepository();


    }

    @Override
    public Repository<Ride> getRideRepository() {
        return rides;
    }

    @Override
    public Repository<Passenger> getPassengerRepository() {
        return passengers;
    }
    @Override
    public Repository<Vehicle> getVehicleRepository() {
        return vehicles;
    }
    @Override
    public Repository<PassengerCategory> getPassengerCategoryRepository() {
        return passengerCategories;
    }
    @Override
    public Repository<RideCategory> getRideCategoryRepository() {
        return rideCategories;
    }
    @Override
    public Repository<Fuel> getFuelRepository() {
        return fuels;
    }
}

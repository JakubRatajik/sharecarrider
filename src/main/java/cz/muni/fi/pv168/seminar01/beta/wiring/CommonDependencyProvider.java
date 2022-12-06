package cz.muni.fi.pv168.seminar01.beta.wiring;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.FuelDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.RideCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.RideDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.VehicleDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.FuelMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.RideCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.RideMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.VehicleMapper;
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

        this.rides = new RideRepository(
                new RideMapper(),
                new RideDao(databaseManager::getConnectionHandler),
                new RideCategoryMapper(),
                new RideCategoryDao(databaseManager::getConnectionHandler),
                new VehicleMapper(),
                new VehicleDao(databaseManager::getConnectionHandler),
                new PassengerMapper(),
                new PassengerDao(databaseManager::getConnectionHandler));
        this.vehicles = new VehicleRepository(
                new VehicleMapper(),
                new VehicleDao(databaseManager::getConnectionHandler));
        this.passengers = new PassengerRepository(
                new PassengerMapper(),
                new PassengerDao(databaseManager::getConnectionHandler),
                new PassengerCategoryMapper(),
                new PassengerCategoryDao(databaseManager::getConnectionHandler));
        this.passengerCategories = new PassengerCategoryRepository(
                new PassengerCategoryMapper(),
                new PassengerCategoryDao(databaseManager::getConnectionHandler));
        this.rideCategories = new RideCategoryRepository(
                new RideCategoryMapper(),
                new RideCategoryDao(databaseManager::getConnectionHandler));
        this.fuels = new FuelRepository(
                new FuelMapper(),
                new FuelDao(databaseManager::getConnectionHandler));


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

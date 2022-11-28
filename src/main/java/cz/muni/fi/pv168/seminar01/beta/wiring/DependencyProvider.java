package cz.muni.fi.pv168.seminar01.beta.wiring;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.Repository;
import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;


/**
 * Dependency provider interface
 */
public interface DependencyProvider {
    Repository<Ride> getRideRepository();
    Repository<Passenger> getPassengerRepository();
    Repository<Vehicle> getVehicleRepository();
    Repository<PassengerCategory> getPassengerCategoryRepository();
    Repository<RideCategory> getRideCategoryRepository();
    Repository<Fuel> getFuelRepository();

}

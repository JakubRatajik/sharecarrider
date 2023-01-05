package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.PassengerCategoryRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.PassengerRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.RideCategoryRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.RideRepository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.VehicleRepository;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Class used for importing
 * as input, class gets 5 File handlers and using other Manipulation package classes
 * imports all necessary data to the application
 */
public class ImporterBase {

    static final IDImporterMapper ID_MAPPER = new IDImporterMapper();

    public static long getNewID(TableCategory category, long oldID) {
        return ID_MAPPER.getNewID(category, oldID);
    }

    private static void deleteAll() {
        CommonElementSupplier.getRepository(TableCategory.RIDES).deleteAll();
        CommonElementSupplier.getRepository(TableCategory.VEHICLES).deleteAll();
        CommonElementSupplier.getRepository(TableCategory.PASSENGERS).deleteAll();
        CommonElementSupplier.getRepository(TableCategory.RIDE_CATEGORY).deleteAll();
        CommonElementSupplier.getRepository(TableCategory.PASSENGER_CATEGORY).deleteAll();
    }

    public static void loadData(File rides, File vehicles, File passengers, File passengerCategories, File rideCategories) {
        List<Passenger> passengerList;
        List<Ride> rideList;
        List<Vehicle> vehicleList;
        List<PassengerCategory> passengerCategoryList;
        List<RideCategory> rideCategoryList;
        deleteAll();

        try {
            if (passengerCategories != null) {
                passengerCategoryList = PassengerCategoryImporter.importPassengerCategories(passengerCategories);
                for (PassengerCategory passengerCategory : passengerCategoryList) {
                    ID_MAPPER.addIDs(TableCategory.PASSENGER_CATEGORY, passengerCategory.getId(), ((PassengerCategoryRepository) CommonElementSupplier.getRepository(TableCategory.PASSENGER_CATEGORY)).introduceEntity(passengerCategory));
                }
            }
            if (vehicles != null) {
                vehicleList = VehicleImporter.importVehicles(vehicles);
                for (Vehicle vehicle : vehicleList) {
                    ID_MAPPER.addIDs(TableCategory.VEHICLES, vehicle.getId(), ((VehicleRepository) CommonElementSupplier.getRepository(TableCategory.VEHICLES)).introduceEntity(vehicle));
                }
            }
            if (passengers != null) {
                passengerList = PassengerImporter.importPassengers(passengers);
                for (Passenger passenger : passengerList) {
                    ID_MAPPER.addIDs(TableCategory.PASSENGERS, passenger.getId(), ((PassengerRepository) CommonElementSupplier.getRepository(TableCategory.PASSENGERS)).introduceEntity(passenger));
                }
            }
            if (rideCategories != null) {
                rideCategoryList = RideCategoryImporter.importRideCategories(rideCategories);
                for (RideCategory rideCategory : rideCategoryList) {
                    ID_MAPPER.addIDs(TableCategory.RIDE_CATEGORY, rideCategory.getId(), ((RideCategoryRepository) CommonElementSupplier.getRepository(TableCategory.RIDE_CATEGORY)).introduceEntity(rideCategory));
                }
            }
            if (rides != null) {
                rideList = RideImporter.importRides(rides);
                for (Ride ride : rideList) {
                    ((RideRepository) CommonElementSupplier.getRepository(TableCategory.RIDES)).addToTableModel(ride);
                }
            }
        } catch (DataManipulationException | ValidationException |
                 FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            new ErrorDialog(MainWindow.getFrame(), e);
        }
    }
}

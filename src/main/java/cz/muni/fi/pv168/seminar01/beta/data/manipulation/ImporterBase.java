package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.*;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.*;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class used for importing
 * as input, class gets 5 File handlers and using other Manipulation package classes
 * imports all necessary data to the application
 */
public class ImporterBase {

    public static void loadData(File rides, File vehicles, File passengers, File passengerCategories, File rideCategories) {
        List<Passenger> passengerList = new ArrayList<>();
        List<Ride> rideList = new ArrayList<>();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<PassengerCategory> passengerCategoryList = new ArrayList<>();
        List<RideCategory> rideCategoryList = new ArrayList<>();
        try {
            if (passengerCategories != null) {
                passengerCategoryList = ImportPassengerCategories.importPassengerCategories(passengerCategories);
                for (PassengerCategory passengerCategory: passengerCategoryList) {
                    ((PassengerCategoryTableModel) Shortcut.getTableModel(TableCategory.PASSENGER_CATEGORY)).addRow(passengerCategory);
                }
            } if (vehicles != null) {
                vehicleList = ImportVehicles.importVehicles(vehicles);
                for (Vehicle vehicle: vehicleList) {
                    ((VehicleTableModel) Shortcut.getTableModel(TableCategory.VEHICLES)).addRow(vehicle);
                }
            } if (passengers != null) {
                passengerList = ImportPassengers.importPassengers(passengers);
                for (Passenger passenger: passengerList) {
                    ((PassengerTableModel) Shortcut.getTableModel(TableCategory.PASSENGERS)).addRow(passenger);
                }
            } if (rideCategories != null) {
                rideCategoryList = ImportRideCategories.importRideCategories(rideCategories);
                for (RideCategory rideCategory: rideCategoryList) {
                    ((RideCategoryTableModel) Shortcut.getTableModel(TableCategory.RIDE_CATEGORY)).addRow(rideCategory);
                }
            } if (rides != null) {
                rideList = ImportRides.importRides(rides);
                for (Ride ride: rideList) {
                    ((RideTableModel) Shortcut.getTableModel(TableCategory.RIDES)).addRow(ride);
                }
            }
        } catch (DataManipulationException | ValidationException | FileNotFoundException e) {
            System.out.println(e.getStackTrace());
            new ErrorDialog(MainWindow.getFrame(), e);
            return;
        }






    }











}

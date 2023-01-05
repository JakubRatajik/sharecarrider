package cz.muni.fi.pv168.seminar01.beta.ui.workers;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.PassengerCategoryExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.PassengerExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.RideCategoryExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.RideExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.VehicleExporter;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class AsyncExporter {
    public void exportData(File rides, File vehicles, File passengers, File passengersCategories, File ridesCategories) {
        var asyncWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                RideExporter rideExporter = new RideExporter();
                rideExporter.export((List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData(),
                        rides.getAbsolutePath());
                VehicleExporter vehicleExporter = new VehicleExporter();
                vehicleExporter.export((List<Vehicle>) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getData(),
                        vehicles.getAbsolutePath());
                PassengerExporter passengerExporter = new PassengerExporter();
                passengerExporter.export((List<Passenger>) CommonElementSupplier.getTableModel(TableCategory.PASSENGERS).getData(),
                        passengers.getAbsolutePath());
                PassengerCategoryExporter passengerCategoryExporter = new PassengerCategoryExporter();
                passengerCategoryExporter.export((List<PassengerCategory>) CommonElementSupplier.getTableModel(TableCategory.PASSENGER_CATEGORY).getData(),
                        passengersCategories.getAbsolutePath());
                RideCategoryExporter rideCategoryExporter = new RideCategoryExporter();
                rideCategoryExporter.export((List<RideCategory>) CommonElementSupplier.getTableModel(TableCategory.RIDE_CATEGORY).getData(),
                        ridesCategories.getAbsolutePath());
                return null;
            }
        };
        asyncWorker.execute();
    }
}

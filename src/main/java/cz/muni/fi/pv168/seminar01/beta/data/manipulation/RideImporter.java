package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.RideValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class RideImporter {

    public static List<Ride> importRides(File rides) throws FileNotFoundException {
        Scanner reader = new Scanner(rides);
        List<Ride> rideList = new ArrayList<>();
        while (reader.hasNextLine()) {
            rideList.add(importRide(ManipulationUtils.splitter(reader.nextLine())));
        }
        return rideList;
    }

    public static Ride importRide(String[] lineSplit) {
        ManipulationUtils.trimAllStringsInArray(lineSplit);
        if (lineSplit.length != ManipulationUtils.RIDE_PARAMETERS_COUNT && lineSplit.length != ManipulationUtils.RIDE_PARAMETERS_COUNT - 1) {
            new ErrorDialog(MainWindow.getFrame(), "Špatný počet atributů v CSV u jízdy");
            throw new DataManipulationException("Špatný počet parametrů pro Jízdu v CSV, mělo být " + ManipulationUtils.RIDE_PARAMETERS_COUNT + ", bylo " + lineSplit.length, new Exception());
        }

        String idString = lineSplit[0];
        String dateString = lineSplit[1];
        String departureString = lineSplit[2];
        String arrivalString = lineSplit[3];
        String from = lineSplit[4];
        String to = lineSplit[5];
        String distanceString = lineSplit[6];
        String categoriesString = lineSplit[7];
        String passengerListString = lineSplit[8];
        String vehicleIdString = lineSplit[9];
        String repetitionString = lineSplit[10];
        String description = "";
        if (lineSplit.length == ManipulationUtils.RIDE_PARAMETERS_COUNT) {
            description = lineSplit[11];
        }


        try {
            RideValidator.validateRide(idString, dateString, departureString, arrivalString, from, to,
                    distanceString, categoriesString, passengerListString, vehicleIdString, repetitionString, description);
        } catch (ValidationException e) {
            throw new DataManipulationException("Problém s načtením jízd. " + e.getMessage(), e);
        }

        long id = Long.parseLong(idString);
        LocalDate date = LocalDate.parse(dateString);
        LocalTime departure = LocalTime.parse(departureString);
        LocalTime arrival = LocalTime.parse(arrivalString);
        int distance = Integer.parseInt(distanceString);
        Set<RideCategory> categories = new HashSet<>();
        Set<Passenger> passengers = new HashSet<>();
        Vehicle vehicle;
        if (!Objects.equals(vehicleIdString, "null")) {
            vehicle = (Vehicle) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getObjectById(ImporterBase.getNewID(TableCategory.VEHICLES, Long.parseLong(vehicleIdString)));
            if (vehicle == null) {
                throw new DataManipulationException("Vozidlo s daným ID nebylo nalezeno, prosím, zkontrolujte data v csv: (ride ID: " + id + ")", new Exception());
            }
        } else {
            vehicle = new Vehicle(279, "X", "X", "X", 1, 1, FuelType.DIESEL);
        }
        Repetition repetition = Enum.valueOf(Repetition.class, repetitionString);


        String cat = lineSplit[7];
        if (cat.length() > 2) {
            for (String category : ManipulationUtils.listParser(cat)) {
                RideCategory rideCategory = (((RideCategoryTableModel) CommonElementSupplier.getTableModel(TableCategory.RIDE_CATEGORY)).getCategoryByID(ImporterBase.getNewID(TableCategory.RIDE_CATEGORY, Long.parseLong(category))));
                if (rideCategory == null) {
                    throw new DataManipulationException("Kategorie jízdy s daným ID nebyla nalezena, prosím, zkontrolujte data v csv: (Ride ID: " + id + ")", new Exception());
                }
                categories.add(rideCategory);

            }
        }

        if (passengerListString.length() > 2) {
            for (String passenger : ManipulationUtils.listParser(passengerListString)) {
                Passenger passengerObject = (Passenger) CommonElementSupplier.getTableModel(TableCategory.PASSENGERS).getObjectById(ImporterBase.getNewID(TableCategory.PASSENGERS, Long.parseLong(passenger)));
                if (passengerObject == null) {
                    throw new DataManipulationException("Cestující s daným ID nebyl nalezen, prosím, zkontrolujte data v csv: (ride ID: " + id + ")", new Exception());
                }
                passengers.add(passengerObject);
            }
        }
        return new Ride(id, date, departure, arrival, from, to, distance, categories, passengers, vehicle, repetition, description);
    }

}

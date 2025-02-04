package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.PassengerValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerImporter {

    static List<Passenger> importPassengers(File passengers) throws FileNotFoundException {
        Scanner reader = new Scanner(passengers);
        List<Passenger> passengersToBeImported = new ArrayList<>();
        while (reader.hasNextLine()) {
            passengersToBeImported.add(importPassenger(ManipulationUtils.splitter(reader.nextLine())));
        }
        return passengersToBeImported;
    }

    private static Passenger importPassenger(String[] lineSplit) {
        if (lineSplit.length != ManipulationUtils.PASSENGER_PARAMETERS_COUNT) {
            new ErrorDialog(MainWindow.getFrame(), "Špatný počet atributů v CSV u pasažéra");
            throw new DataManipulationException("Špatný počet parametrů pro Pasažéra v CSV, mělo být " + ManipulationUtils.PASSENGER_PARAMETERS_COUNT + ", bylo " + lineSplit.length, new Exception());
        }

        String idString = lineSplit[0];
        String name = lineSplit[1];
        String surname = lineSplit[2];
        String phoneNumber = lineSplit[3];
        String categories = lineSplit[4];

        try {
            PassengerValidator.validatePassenger(idString, name, surname, phoneNumber, categories);
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), "Problém s načtením pasažérů");
            throw new DataManipulationException("Problém s načtením pasažérů. " + e.getMessage(), e);
        }

        List<PassengerCategory> categorySet = new ArrayList<>();
        if (categories.length() > 2) {
            for (String category : ManipulationUtils.listParser(categories)) {
                PassengerCategory passengerCategoryTmp = ((PassengerCategoryTableModel) CommonElementSupplier.getTableModel(TableCategory.PASSENGER_CATEGORY)).getObjectById(ImporterBase.getNewID(TableCategory.PASSENGER_CATEGORY, Long.parseLong(category)));
                if (passengerCategoryTmp == null) {
                    throw new DataManipulationException("Kategorii pasažéra s daným ID nebylo možné najít, prosím, zkontrolujte data v csv: (passenger ID: " + Long.parseLong(idString) + ")", new Exception());
                }
                categorySet.add(passengerCategoryTmp);
            }

        }
        return new Passenger(Long.parseLong(idString), name, surname, phoneNumber, categorySet);
    }
}

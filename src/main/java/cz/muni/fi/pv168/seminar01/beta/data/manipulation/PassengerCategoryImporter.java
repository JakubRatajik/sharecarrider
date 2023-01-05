package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.CategoryValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerCategoryImporter {

    static List<PassengerCategory> importPassengerCategories(File passengerCategories) throws FileNotFoundException {
        Scanner reader = new Scanner(passengerCategories);
        List<PassengerCategory> passengerCategoryList = new ArrayList<>();
        while (reader.hasNextLine()) {
            passengerCategoryList.add(importPassengerCategory(ManipulationUtils.splitter(reader.nextLine())));
        }
        return passengerCategoryList;
    }

    private static PassengerCategory importPassengerCategory(String[] lineSplit) {
        ManipulationUtils.trimAllStringsInArray(lineSplit);

        if (lineSplit.length != ManipulationUtils.PASSENGER_CATEGORIES_PARAMETER_COUNT) {
            new ErrorDialog(MainWindow.getFrame(), "Špatný počet atributů v CSV u kategorií pasažérů");
            throw new DataManipulationException("Špatný počet parametrů pro kategorii pasažérů v CSV, mělo být " + ManipulationUtils.PASSENGER_CATEGORIES_PARAMETER_COUNT + ", bylo " + lineSplit.length, new Exception());
        }

        String idString = lineSplit[0];
        String name = lineSplit[1];


        try {
            CategoryValidator.validatePassengerCategory(idString, name);
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), "Problém při načítání kategorií pasažérů");
            throw new DataManipulationException("Problém s načtením kategorií pasžérů.", e);
        }
        return new PassengerCategory(Long.parseLong(idString), name);
    }
}

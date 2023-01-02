package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.data.validation.CategoryValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RideCategoryImporter {

    static List<RideCategory> importRideCategories(File rideCategories) throws FileNotFoundException {
        Scanner reader = new Scanner(rideCategories);
        List<RideCategory> rideCategoryList = new ArrayList<>();
        while (reader.hasNextLine()) {
            rideCategoryList.add(importRideCategory(ManipulationUtils.splitter(reader.nextLine())));
        }
        return rideCategoryList;
    }

    private static RideCategory importRideCategory(String[] lineSplit) {
        ManipulationUtils.trimAllStringsInArray(lineSplit);

        if (lineSplit.length != ManipulationUtils.RIDE_CATEGORIES_PARAMETER_COUNT) {
            new ErrorDialog(MainWindow.getFrame(), "Špatný počet atributů v CSV u kategorií jízd");
            throw new DataManipulationException("Špatný počet parametrů pro kategorii jízd v CSV, mělo být " + ManipulationUtils.RIDE_CATEGORIES_PARAMETER_COUNT + ", bylo " + lineSplit.length, new Exception());
        }

        String idString = lineSplit[0];
        String name = lineSplit[1];

        try {
            CategoryValidator.validateRideCategory(idString, name);
        } catch (ValidationException e) {
            new ErrorDialog(MainWindow.getFrame(), "Problém při načítání kategorií jízd");
            throw new DataManipulationException("Problém s načtením kategorií jízd.", e);
        }
        return new RideCategory(Long.parseLong(idString), name);
    }
}

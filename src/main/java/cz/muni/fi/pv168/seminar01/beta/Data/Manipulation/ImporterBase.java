package cz.muni.fi.pv168.seminar01.beta.Data.Manipulation;

import cz.muni.fi.pv168.seminar01.beta.Model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.DialogBase;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.VehicleTableModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImporterBase {

    private static String[] splitter(String toSplit) {
        return toSplit.split(AbstractExporter.SEPARATOR);
    }

    public static void loadData(File rides, File vehicles, File passengers) {
        try {
            importVehicles(vehicles);
        } catch (Exception e) {
            return;
        }



    }

    private static void importVehicles(File vehicles) throws FileNotFoundException {
        Scanner reader = new Scanner(vehicles);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            importVehicle(line);
        }
    }



    private static void importVehicle(String vehicle) {
        String[] split = splitter(vehicle);
        if (split.length == 7) {
            int id = tryToInt(split[0]);
            String licencePlate = split[1];
            String brand = split[2];
            String model = split[3];
            int seats = tryToInt(split[4]);
            float consumption = (float) tryToDouble(split[5]);
            FuelType type = tryToFuelType(split[6]);
            ((VehicleTableModel) DialogBase.getTableModel(TableCategory.VEHICLES)).addRow(
                    new Vehicle(id, licencePlate, brand, model, seats, consumption, type));
        }
    }

    private static int tryToInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (Exception e) {
            return -1;
        }

    }

    private static double tryToDouble(String integer) {
        try {
            return Double.parseDouble(integer);
        } catch (Exception e) {
            return -1;
        }
    }

    private static FuelType tryToFuelType(String integer) {
        try {
            return FuelType.valueOf(integer);
        } catch (Exception e) {
            return null;
        }

    }

}

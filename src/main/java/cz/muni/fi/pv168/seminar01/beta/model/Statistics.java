package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

public class Statistics {


    public static Vehicle findCheapestVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) Shortcut.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) < 0 ? a : b)
                .orElse(null);
    }

    public static Vehicle findMostExpensiveVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) Shortcut.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) > 0 ? a : b)
                .orElse(null);
    }

    public static int countTotalDistance() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();
        return allRides
                .stream()
                .mapToInt(Ride::getDistance)
                .sum();
    }

    public static BigDecimal countTotalCost() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .map(Ride::countPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static double countAverageDistance() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return countTotalDistance() / (double) allRides.size();
    }

    public static BigDecimal countAverageCost() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return countTotalCost().divide(new BigDecimal(allRides.size()));
    }

    public static Ride findLongestRide() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.getDistance() >= b.getDistance() ? a : b)
                .orElse(null);
    }

    public static Ride findMostExpensiveRide() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.countPrice().compareTo(b.countPrice()) > 0 ? a : b)
                .orElse(null);
    }
}

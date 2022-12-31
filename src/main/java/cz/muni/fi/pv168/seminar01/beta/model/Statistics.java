package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Statistics {


    public static Vehicle findCheapestVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) < 0 ? a : b)
                .orElse(null);
    }

    public static Vehicle findMostExpensiveVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) > 0 ? a : b)
                .orElse(null);
    }

    public static int countTotalDistance() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();
        return allRides
                .stream()
                .mapToInt(Ride::getDistance)
                .sum();
    }

    public static BigDecimal countTotalCost() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .map(Ride::countPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static double countAverageDistance() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();
        if (allRides.size() == 0) {
            return 0;
        }

        return countTotalDistance() / (double) allRides.size();
    }

    public static BigDecimal countAverageCost() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();

        if (allRides.size() == 0) {
            return BigDecimal.ZERO;
        }

        return countTotalCost().divide(new BigDecimal(allRides.size()), RoundingMode.UP);
    }

    public static Ride findLongestRide() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.getDistance() >= b.getDistance() ? a : b)
                .orElse(null);
    }

    public static Ride findMostExpensiveRide() {
        List<Ride> allRides = (List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.countPrice().compareTo(b.countPrice()) > 0 ? a : b)
                .orElse(null);
    }
}

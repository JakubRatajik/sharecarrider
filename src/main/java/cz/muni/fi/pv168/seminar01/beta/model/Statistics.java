package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Statistics {


    public static Vehicle findCheapestVehicle() {
        List<Vehicle> allVehicles = MainWindow.getProvider().getVehicleRepository().findAll();
        return findCheapestVehicle(allVehicles);
    }
    
    public static Vehicle findCheapestVehicle(List<Vehicle> allVehicles) {
        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) < 0 ? a : b)
                .orElse(null);
    }

    public static Vehicle findMostExpensiveVehicle() {
        List<Vehicle> allVehicles = MainWindow.getProvider().getVehicleRepository().findAll();
        return findMostExpensiveVehicle(allVehicles);
    }
    
    public static Vehicle findMostExpensiveVehicle(List<Vehicle> allVehicles) {
        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) > 0 ? a : b)
                .orElse(null);
    }

    public static int countTotalDistance() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();
        return countTotalDistance(allRides);
    }
    
    public static int countTotalDistance(List<Ride> allRides) {
               return allRides
                .stream()
                .mapToInt(Ride::getDistance)
                .sum();
    }

    public static BigDecimal countTotalCost() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();

        return countTotalCost(allRides);
    }
    
    public static BigDecimal countTotalCost(List<Ride> allRides) {
        return allRides
                .stream()
                .map(Ride::countPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static double countAverageDistance() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();
        return countAverageDistance(allRides);
    }
    
    public static double countAverageDistance(List<Ride> allRides) {
        if (allRides.size() == 0) {
            return 0;
        }

        return countTotalDistance(allRides) / (double) allRides.size();
    }

    public static BigDecimal countAverageCost() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();
        return countAverageCost(allRides);
    }
    
    public static BigDecimal countAverageCost(List<Ride> allRides) {
        if (allRides.size() == 0) {
            return BigDecimal.ZERO;
        }

        return countTotalCost().divide(new BigDecimal(allRides.size()), RoundingMode.UP);
    }

    public static Ride findLongestRide() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();
        return findLongestRide(allRides);
    }
    
    public static Ride findLongestRide(List<Ride> allRides) {
        return allRides
                .stream()
                .reduce((a, b) -> a.getDistance() >= b.getDistance() ? a : b)
                .orElse(null);
    }

    public static Ride findMostExpensiveRide() {
        List<Ride> allRides = MainWindow.getProvider().getRideRepository().findAll();

        return findMostExpensiveRide(allRides);
    }

    public static Ride findMostExpensiveRide(List<Ride> allRides) {
        return allRides
                .stream()
                .reduce((a, b) -> a.countPrice().compareTo(b.countPrice()) > 0 ? a : b)
                .orElse(null);
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.math.RoundingMode;
import java.util.List;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Locale;

/**
 * This class represents the content of the statistics frame including all the counting of its attributes.
 */
public class Statistics {
    private JPanel main;

    private int totalDistance;
    private BigDecimal totalCost;
    private JButton longestRide;
    private JButton expensiveRide;

    private JLabel totalDistanceLabel;
    private JLabel totalCostLabel;
    private JLabel averageDistanceLabel;
    private JLabel averageCostLabel;

    private JLabel expensiveVehicle;
    private JLabel cheapestVehicle;

    public Statistics() {
        initializeContent();
    }

    public void initializeContent() {
        main = new JPanel();
        main.setLayout(new GridLayout(4, 4));
        main.setBorder(BorderFactory.createEmptyBorder(0, UIUtilities.LEFT_FRAME_INDENT, 20, UIUtilities.RIGHT_FRAME_INDENT));
        main.setBackground(UIUtilities.WHITE);

        JLabel ttlDistance = new JLabel("•  Celková vzdálenost:");
        ttlDistance.setFont(UIUtilities.fStatistics);
        main.add(ttlDistance);
        totalDistance = countTotalDistance();
        totalDistanceLabel = new JLabel( totalDistance + " km");
        totalDistanceLabel.setFont(UIUtilities.fStatistics);
        main.add(totalDistanceLabel);

        JLabel ttlCost = new JLabel("•  Celková cena:");
        ttlCost.setFont(UIUtilities.fStatistics);
        main.add(ttlCost);
        totalCost = countTotalCost();
        totalCostLabel = new JLabel(totalCost.setScale(2, RoundingMode.UP) + " Kč");
        totalCostLabel.setFont(UIUtilities.fStatistics);
        main.add(totalCostLabel);

        JLabel avgDistance = new JLabel("• Průměrná vzdálenost jízdy:");
        avgDistance.setFont(UIUtilities.fStatistics);
        main.add(avgDistance);
        averageDistanceLabel = new JLabel(String.format(Locale.US, "%.2f", countAverageDistance()) + " km");
        averageDistanceLabel.setFont(UIUtilities.fStatistics);
        main.add(averageDistanceLabel);

        JLabel avgCost = new JLabel("• Průměrná cena jízdy:");
        avgCost.setFont(UIUtilities.fStatistics);
        main.add(avgCost);
        averageCostLabel = new JLabel(countAverageCost().setScale(2, RoundingMode.UP) + " Kč");
        averageCostLabel.setFont(UIUtilities.fStatistics);
        main.add(averageCostLabel);

        JLabel longestRide = new JLabel("• Detail nejdelší jízdy:");
        longestRide.setFont(UIUtilities.fStatistics);
        main.add(longestRide);
        this.longestRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(this.longestRide);
        this.longestRide.setFont(UIUtilities.fStatistics);
        this.longestRide.setSize(70, 30);
        addLongestRideAL();
        main.add(this.longestRide);

        JLabel xpnsvRide = new JLabel("• Detail nejdražší jízdy:");
        xpnsvRide.setFont(UIUtilities.fStatistics);
        main.add(xpnsvRide);
        expensiveRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(expensiveRide);
        expensiveRide.setFont(UIUtilities.fStatistics);
        expensiveRide.setSize(70, 30);
        addExpensiveRideAL();
        main.add(expensiveRide);

        JLabel xpnsvVehicle = new JLabel("• Vozidlo, které jezdí nejdráž:");
        xpnsvVehicle.setFont(UIUtilities.fStatistics);
        main.add(xpnsvVehicle);
        Vehicle veh = findMostExpensiveVehicle();
        expensiveVehicle = new JLabel(veh.getBrand() + " " + veh.getType());
        expensiveVehicle.setFont(UIUtilities.fStatistics);
        main.add(expensiveVehicle);

        JLabel chpstVehicle = new JLabel("•  Vozidlo, které jezdí nejlevněji:");
        chpstVehicle.setFont(UIUtilities.fStatistics);
        main.add(chpstVehicle);
        veh = findCheapestVehicle();
        cheapestVehicle = new JLabel("   " + veh.getBrand() + " " + veh.getType());
        cheapestVehicle.setFont(UIUtilities.fStatistics);
        main.add(cheapestVehicle);
    }

    public void addExpensiveRideAL() {
        expensiveRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(), "Nejdražší jízda", findMostExpensiveRide()));
    }

    public void addLongestRideAL() {
        longestRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(), "Nejdelší jízda", findLongestRide()));
    }

    public static void update() {
    }

    public Vehicle findCheapestVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) Shortcut.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) > 0 ? a : b)
                .orElse(null);
    }

    public Vehicle findMostExpensiveVehicle() {
        List<Vehicle> allVehicles = (List<Vehicle>) Shortcut.getTableModel(TableCategory.VEHICLES).getData();

        return allVehicles
                .stream()
                .reduce((a, b) -> a.countPricePerHundredKM().compareTo(b.countPricePerHundredKM()) < 0 ? a : b)
                .orElse(null);
    }

    public int countTotalDistance() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();
        return allRides
                .stream()
                .mapToInt(Ride::getDistance)
                .sum();
    }

    public BigDecimal countTotalCost() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .map(Ride::countPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double countAverageDistance() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return countTotalDistance() / (double) allRides.size();
    }

    public BigDecimal countAverageCost() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return countTotalCost().divide(new BigDecimal(allRides.size()));
    }

    public Ride findLongestRide() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.getDistance() >= b.getDistance() ? a : b)
                .orElse(null);
    }

    public Ride findMostExpensiveRide() {
        List<Ride> allRides = (List<Ride>) Shortcut.getTableModel(TableCategory.RIDES).getData();

        return allRides
                .stream()
                .reduce((a, b) -> a.countPrice().compareTo(b.countPrice()) > 0 ? a : b)
                .orElse(null);
    }

    public JPanel getMain() {
        return main;
    }
}

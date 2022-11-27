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
        main.setBorder(BorderFactory.createEmptyBorder(0, UIUtilities.LEFT_FRAME_INDENT, 150, UIUtilities.RIGHT_FRAME_INDENT));
        main.setBackground(UIUtilities.WHITE);

        JLabel ttlDistance = new JLabel("•  Celková vzdálenost:");
        ttlDistance.setFont(UIUtilities.fStatistics);
        ttlDistance.setForeground(UIUtilities.OCHER);
        main.add(ttlDistance);
        totalDistanceLabel = new JLabel();
        totalDistanceLabel.setFont(UIUtilities.fStatistics);
        main.add(totalDistanceLabel);

        JLabel ttlCost = new JLabel("•  Celková cena:");
        ttlCost.setFont(UIUtilities.fStatistics);
        ttlCost.setForeground(UIUtilities.OCHER);
        main.add(ttlCost);
        totalCostLabel = new JLabel();
        totalCostLabel.setFont(UIUtilities.fStatistics);
        main.add(totalCostLabel);

        JLabel avgDistance = new JLabel("• Průměrná vzdálenost jízdy:");
        avgDistance.setFont(UIUtilities.fStatistics);
        avgDistance.setForeground(UIUtilities.OCHER);
        main.add(avgDistance);
        averageDistanceLabel = new JLabel();
        averageDistanceLabel.setFont(UIUtilities.fStatistics);
        main.add(averageDistanceLabel);

        JLabel avgCost = new JLabel("• Průměrná cena jízdy:");
        avgCost.setFont(UIUtilities.fStatistics);
        avgCost.setForeground(UIUtilities.OCHER);
        main.add(avgCost);
        averageCostLabel = new JLabel();
        averageCostLabel.setFont(UIUtilities.fStatistics);
        main.add(averageCostLabel);

        JLabel lngstRide = new JLabel("• Detail nejdelší jízdy:");
        lngstRide.setFont(UIUtilities.fStatistics);
        lngstRide.setForeground(UIUtilities.OCHER);
        main.add(lngstRide);
        longestRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(longestRide);
        longestRide.setFont(UIUtilities.fStatistics);
        longestRide.setSize(70, 30);
        addLongestRideAL();
        main.add(longestRide);

        JLabel xpnsvRide = new JLabel("• Detail nejdražší jízdy:");
        xpnsvRide.setFont(UIUtilities.fStatistics);
        xpnsvRide.setForeground(UIUtilities.OCHER);
        main.add(xpnsvRide);
        expensiveRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(expensiveRide);
        expensiveRide.setFont(UIUtilities.fStatistics);
        expensiveRide.setSize(70, 30);
        addExpensiveRideAL();
        main.add(expensiveRide);

        JLabel xpnsvVehicle = new JLabel("• Vozidlo, které jezdí nejdráž:");
        xpnsvVehicle.setFont(UIUtilities.fStatistics);
        xpnsvVehicle.setForeground(UIUtilities.OCHER);
        main.add(xpnsvVehicle);
        expensiveVehicle = new JLabel();
        expensiveVehicle.setFont(UIUtilities.fStatistics);
        main.add(expensiveVehicle);

        JLabel chpstVehicle = new JLabel("•  Vozidlo, které jezdí nejlevněji:");
        chpstVehicle.setFont(UIUtilities.fStatistics);
        chpstVehicle.setForeground(UIUtilities.OCHER);
        main.add(chpstVehicle);
        cheapestVehicle = new JLabel();
        cheapestVehicle.setFont(UIUtilities.fStatistics);
        main.add(cheapestVehicle);

        update();
    }

    public void addExpensiveRideAL() {
        expensiveRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(), "Nejdražší jízda", findMostExpensiveRide()));
    }

    public void addLongestRideAL() {
        longestRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(), "Nejdelší jízda", findLongestRide()));
    }

    public void update() {
        totalDistance = countTotalDistance();
        totalDistanceLabel.setText(totalDistance + " km");

        totalCost = countTotalCost();
        totalCostLabel.setText(totalCost.setScale(2, RoundingMode.UP) + " Kč");

        averageDistanceLabel.setText(String.format(Locale.US, "%.2f", countAverageDistance()) + " km");
        averageCostLabel.setText(countAverageCost().setScale(2, RoundingMode.UP) + " Kč");

        Vehicle veh = findMostExpensiveVehicle();
        if (veh == null) {
            expensiveVehicle.setText("Nebyla nalezena žádná vozidla");
        } else {
            expensiveVehicle.setText(veh.getBrand() + " " + veh.getType());
        }

        veh = findCheapestVehicle();
        if (veh == null) {
            expensiveVehicle.setText("Nebyla nalezena žádná vozidla");
        } else {
            cheapestVehicle.setText("   " + veh.getBrand() + " " + veh.getType());
        }
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

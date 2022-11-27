package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

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
        totalCostLabel = new JLabel(totalCost + " Kč");
        totalCostLabel.setFont(UIUtilities.fStatistics);
        main.add(totalCostLabel);

        JLabel avgDisstance = new JLabel("• Průměrná vzdálenost jízdy:");
        avgDisstance.setFont(UIUtilities.fStatistics);
        main.add(avgDisstance);
        averageDistanceLabel = new JLabel(countAverageDistance() + " km");
        averageDistanceLabel.setFont(UIUtilities.fStatistics);
        main.add(averageDistanceLabel);

        JLabel avgCost = new JLabel("• Průměrná cena jízdy:");
        avgCost.setFont(UIUtilities.fStatistics);
        main.add(avgCost);
        averageCostLabel = new JLabel(countAverageCost() + " Kč");
        averageCostLabel.setFont(UIUtilities.fStatistics);
        main.add(averageCostLabel);

        JLabel lngstRide = new JLabel("• Detail nejdelší jízdy:");
        lngstRide.setFont(UIUtilities.fStatistics);
        main.add(lngstRide);
        longestRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(longestRide);
        longestRide.setFont(UIUtilities.fStatistics);
        longestRide.setSize(70, 30);
        addLongestRideAL();
        main.add(longestRide);

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
        Vehicle veh = findExpensiveVehicle();
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

    public Vehicle findCheapestVehicle() {
        return (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getEntity(0);
    }

    public Vehicle findExpensiveVehicle() {
        return (Vehicle) Shortcut.getTableModel(TableCategory.VEHICLES).getEntity(0);
    }

    public void addExpensiveRideAL() {
        expensiveRide.addActionListener(al -> {
            new RideDetailDialog(MainWindow.getFrame(), "Nejdražší jízda", findMostExpensiveRide());
        });
    }

    public void addLongestRideAL() {
        longestRide.addActionListener(al -> {
            new RideDetailDialog(MainWindow.getFrame(), "Nejdelší jízda", findLongestRide());
        });
    }

    public static void update() {
    }

    public int countTotalDistance() {
        return 0;
    }

    public BigDecimal countTotalCost() {
        return BigDecimal.valueOf(0);
    }

    public double countAverageDistance() {
        return 0;
    }

    public BigDecimal countAverageCost() {
        return BigDecimal.valueOf(0);
    }

    public Ride findLongestRide() {
        return null;
    }

    public Ride findMostExpensiveRide() {
        return null;
    }

    public JPanel getMain() {
        return main;
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.model.Statistics;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.RideDetailDialog;

import javax.swing.*;
import java.math.RoundingMode;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Locale;

/**
 * This class represents the content of the statistics frame including all the counting of its attributes.
 */
public class StatisticsUI {
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

    public StatisticsUI() {
        initializeContent();
    }

    public void initializeContent() {
        main = new JPanel();
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        main.setBorder(BorderFactory.createEmptyBorder(30, UIUtilities.LEFT_FRAME_INDENT, 150, UIUtilities.RIGHT_FRAME_INDENT));
        main.setBackground(UIUtilities.WHITE);

        JLabel ttlDistance = new JLabel("•  Celková vzdálenost:");
        ttlDistance.setFont(new Font("Inter", Font.BOLD, 22));
        ttlDistance.setForeground(UIUtilities.OCHER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.2;
        c.weighty = 0.9;
        c.gridx = 0;
        c.gridy = 0;
        main.add(ttlDistance, c);
        totalDistanceLabel = new JLabel();
        totalDistanceLabel.setFont(new Font("Inter", Font.BOLD, 22));
        c.gridx = 1;
        c.gridy = 0;
        main.add(totalDistanceLabel, c);

        JLabel ttlCost = new JLabel("•  Celková cena:");
        ttlCost.setFont(new Font("Inter", Font.BOLD, 22));
        ttlCost.setForeground(UIUtilities.OCHER);
        c.gridx = 2;
        c.gridy = 0;
        main.add(ttlCost, c);
        totalCostLabel = new JLabel();
        totalCostLabel.setFont(new Font("Inter", Font.BOLD, 22));
        c.gridx = 3;
        c.gridy = 0;
        main.add(totalCostLabel, c);

        JLabel avgDistance = new JLabel("• Průměrná vzdálenost jízdy:");
        avgDistance.setFont(UIUtilities.fStatistics);
        avgDistance.setForeground(UIUtilities.OCHER);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        main.add(avgDistance, c);
        averageDistanceLabel = new JLabel();
        averageDistanceLabel.setFont(UIUtilities.fStatistics);
        c.gridx = 1;
        c.gridy = 1;
        main.add(averageDistanceLabel, c);

        JLabel avgCost = new JLabel("• Průměrná cena jízdy:");
        avgCost.setFont(UIUtilities.fStatistics);
        avgCost.setForeground(UIUtilities.OCHER);
        c.gridx = 2;
        c.gridy = 1;
        main.add(avgCost, c);
        averageCostLabel = new JLabel();
        averageCostLabel.setFont(UIUtilities.fStatistics);
        c.gridx = 3;
        c.gridy = 1;
        main.add(averageCostLabel, c);

        JLabel xpnsvVehicle = new JLabel("• Vozidlo, které jezdí nejdráž:");
        xpnsvVehicle.setFont(UIUtilities.fStatistics);
        xpnsvVehicle.setForeground(UIUtilities.OCHER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        main.add(xpnsvVehicle, c);
        expensiveVehicle = new JLabel();
        expensiveVehicle.setFont(UIUtilities.fStatistics);
        c.gridx = 1;
        c.gridy = 2;
        main.add(expensiveVehicle, c);

        JLabel lngstRide = new JLabel("• Detail nejdelší jízdy:");
        lngstRide.setFont(UIUtilities.fStatistics);
        lngstRide.setForeground(UIUtilities.OCHER);
        c.gridx = 2;
        c.gridy = 2;
        main.add(lngstRide, c);
        longestRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(longestRide);
        longestRide.setFont(UIUtilities.fStatistics);
        addLongestRideAL();
        c.gridx = 3;
        c.gridy = 2;
        main.add(longestRide, c);

        JLabel chpstVehicle = new JLabel("•  Vozidlo, které jezdí nejlevněji:");
        chpstVehicle.setFont(UIUtilities.fStatistics);
        chpstVehicle.setForeground(UIUtilities.OCHER);
        c.gridx = 0;
        c.gridy = 3;
        main.add(chpstVehicle, c);
        cheapestVehicle = new JLabel();
        cheapestVehicle.setFont(UIUtilities.fStatistics);
        c.gridx = 1;
        c.gridy = 3;
        main.add(cheapestVehicle, c);

        JLabel xpnsvRide = new JLabel("• Detail nejdražší jízdy:");
        xpnsvRide.setFont(UIUtilities.fStatistics);
        xpnsvRide.setForeground(UIUtilities.OCHER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        main.add(xpnsvRide, c);
        expensiveRide = new JButton("Ukaž informace");
        UIUtilities.formatDefaultComponent(expensiveRide);
        expensiveRide.setFont(UIUtilities.fStatistics);
        expensiveRide.setSize(20, 30);
        addExpensiveRideAL();
        c.gridx = 3;
        c.gridy = 3;
        main.add(expensiveRide, c);

        update();
    }

    public void addExpensiveRideAL() {
        expensiveRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(),
                "Nejdražší jízda", Statistics.findMostExpensiveRide()));
    }

    public void addLongestRideAL() {
        longestRide.addActionListener(al -> new RideDetailDialog(MainWindow.getFrame(),
                "Nejdelší jízda", Statistics.findLongestRide()));
    }

    public void update() {
        totalDistance = Statistics.countTotalDistance();
        totalDistanceLabel.setText(totalDistance + " km");

        totalCost = Statistics.countTotalCost();
        totalCostLabel.setText(totalCost.setScale(2, RoundingMode.UP) + " Kč");

        averageDistanceLabel.setText(String.format(Locale.US, "%.2f", Statistics.countAverageDistance()) + " km");
        averageCostLabel.setText(Statistics.countAverageCost().setScale(2, RoundingMode.UP) + " Kč");

        Vehicle veh = Statistics.findMostExpensiveVehicle();
        if (veh == null) {
            expensiveVehicle.setText("Nebyla nalezena žádná vozidla");
        } else {
            expensiveVehicle.setText(veh.getBrand() + " " + veh.getType());
        }

        veh = Statistics.findCheapestVehicle();
        if (veh == null) {
            expensiveVehicle.setText("Nebyla nalezena žádná vozidla");
        } else {
            cheapestVehicle.setText(veh.getBrand() + " " + veh.getType());
        }
    }


    public JPanel getMain() {
        return main;
    }
}

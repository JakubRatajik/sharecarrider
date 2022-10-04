package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddVehicleDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameWindow {
    private static JFrame frame;
    private static TabFrame rides;
    private static TabFrame vehicles;
    private static TabFrame passengers;
    public JFrameWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Share Car Rider");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 750));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(UIConstants.LIGHT_BEIGE);
        addMainBar();
        addTabBar();

        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);


        frame.setVisible(true);

    }

    private void addMainBar() {
        MainBar panel = new MainBar();
        frame.add(panel);
        frame.add(panel, BorderLayout.NORTH);
    }

    private void addTabBar() {
        JTabbedPane tabs = new JTabbedPane();
        rides = new TabFrame();
        rides.getPlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddRideDialog(frame, "Add Ride");
            }
        });
        vehicles = new TabFrame();
        vehicles.getPlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddVehicleDialog(frame, "Add Vehicle");
            }
        });
        passengers = new TabFrame();
        Statistics statistics = new Statistics();
        tabs.setFont(UIConstants.fTab);
        tabs.addTab("Rides", rides.getMain());
        tabs.addTab("Vehicles", vehicles.getMain());
        tabs.addTab("Passengers", passengers.getMain());
        tabs.addTab("Statistics", statistics.getMain());

        tabs.setForeground(UIConstants.TEXT_BROWN);
        tabs.setBackground(UIConstants.WHITE);


        tabs.setBackgroundAt(3, UIConstants.OCHER);


        frame.add(tabs);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static TabFrame getRides() {
        return rides;
    }

    public static TabFrame getVehicles() {
        return vehicles;
    }

    public static TabFrame getPassengers() {
        return passengers;
    }
}

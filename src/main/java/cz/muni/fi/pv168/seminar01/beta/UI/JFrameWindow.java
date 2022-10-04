package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddVehicleDialog;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameWindow {
    private static JFrame frame;
    private static TabFrame rides;
    private static TabFrame vehicles;
    private static TabFrame passengers;

    private static JPanel topPanel;
    public JFrameWindow() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setTitle("Share Car Rider");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1333, 750));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(UIConstants.WHITE);
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        topPanel.setBackground(UIConstants.LIGHT_BEIGE);
        addMainBar();
        addPlain();
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

    private void addPlain() {
        JToolBar bar = new JToolBar();
        bar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bar.setBackground(UIConstants.LIGHT_BEIGE);
        bar.setForeground(UIConstants.LIGHT_BEIGE);
        JLabel spacing = new JLabel("HELLO");
        spacing.setFont(new Font("Arial", Font.PLAIN, 22));
        spacing.setForeground(UIConstants.LIGHT_BEIGE);
        bar.add(spacing);
        topPanel.add(bar, BorderLayout.NORTH);

    }

    private void addTabBar() {
        UIManager.put( "TabbedPane.borderColor", UIConstants.WHITE );
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
        tabs.setBackground(UIConstants.WHITE);

        tabs.setForeground(UIConstants.TEXT_BROWN);


        tabs.setBackgroundAt(3, UIConstants.OCHER);

        topPanel.add(tabs, BorderLayout.CENTER);
        frame.add(topPanel);
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

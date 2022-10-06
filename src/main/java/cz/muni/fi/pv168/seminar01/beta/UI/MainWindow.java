package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddPassengerDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddRideDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.AddVehicleDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.TemporaryDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.Model.TabCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.TableInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private static JFrame frame;
    private static TabFrame rides;
    private static TabFrame vehicles;
    private static TabFrame passengers;

    private static JPanel topPanel;
    public MainWindow() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        var im = getClass().getResource("/SCR.png");
        if (im != null) {
            frame.setIconImage(new ImageIcon(im).getImage());
        }


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
        TableInitializer.initializeTab(rides, TabCategory.RIDES);
        rides.getPlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddRideDialog(frame, "Add Ride");
            }
        });
        rides.getSortBy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Sort By");
            }
        });
        rides.getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Filter");
            }
        });

        vehicles = new TabFrame();
        vehicles.getPlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddVehicleDialog(frame, "Add Vehicle");
            }
        });
        vehicles.getSortBy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Sort By");
            }
        });
        vehicles.getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Filter");
            }
        });
        passengers = new TabFrame();
        passengers.getPlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new AddPassengerDialog(frame, "Add Passenger");
            }
        });
        passengers.getSortBy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Sort By");
            }
        });
        passengers.getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dial = new TemporaryDialog(frame, "Filter");
            }
        });
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

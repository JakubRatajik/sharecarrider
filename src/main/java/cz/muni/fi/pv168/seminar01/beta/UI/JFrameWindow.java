package cz.muni.fi.pv168.seminar01.beta.UI;

import javax.swing.*;
import java.awt.*;

public class JFrameWindow {
    private static JFrame frame;
    public JFrameWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Share Car Rider");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1400, 950));
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
        Rides rides = new Rides();
        Rides vehicles = new Rides();
        Rides passengers = new Rides();
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
}

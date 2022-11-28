package cz.muni.fi.pv168.seminar01.beta.ui;

import cz.muni.fi.pv168.seminar01.beta.model.FuelPrice;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.wiring.ProductionDependencyProvider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow implements ChangeListener {
    private static JFrame frame;
    private static TabFrame ridesTabFrame;
    private static TabFrame vehiclesTabFrame;
    private static TabFrame passengersTabFrame;
    private static TabFrame passengerCategoriesTabFrame;
    private static TabFrame rideCategoriesTabFrame;
    private static JPanel topPanel;
    private static FuelPrice fuelPrice;
    private Statistics statistics;

    private ProductionDependencyProvider provider;

    public MainWindow() {
        initialize();
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static TabFrame getRidesTabFrame() {
        return ridesTabFrame;
    }

    public static TabFrame getVehiclesTabFrame() {
        return vehiclesTabFrame;
    }

    public static TabFrame getPassengersTabFrame() {
        return passengersTabFrame;
    }

    public static TabFrame getPassengerCategoriesTabFrame() {
        return passengerCategoriesTabFrame;
    }

    public static TabFrame getRideCategoriesTabFrame() {
        return rideCategoriesTabFrame;
    }

    public static FuelPrice getFuelPrice() {
        return fuelPrice;
    }

    private void initialize() {
        provider = new ProductionDependencyProvider();
        frame = new JFrame();
        var im = getClass().getResource("/SCR.png");
        if (im != null) {
            frame.setIconImage(new ImageIcon(im).getImage());
        }

        // fuelPrice will be moved to Repository and accessed via DependencyProvider
        // TODO - use dependency provider to access repositories for FuelPrice and other data
        fuelPrice = new FuelPrice(provider);

        frame.setTitle("Share Car Rider");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1333, 750));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(UIUtilities.WHITE);
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        topPanel.setBackground(UIUtilities.LIGHT_BEIGE);
        addMainBar();
        addPlain();
        addTabBar();

        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);


        frame.setVisible(true);

    }

    private void addMainBar() {
        MainBar panel = new MainBar();
        frame.add(panel, BorderLayout.NORTH);
    }

    private void addPlain() {
        JToolBar bar = new JToolBar();
        bar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bar.setBackground(UIUtilities.LIGHT_BEIGE);
        bar.setForeground(UIUtilities.LIGHT_BEIGE);
        JLabel spacing = new JLabel("Muminci <3");
        spacing.setFont(new Font("Arial", Font.PLAIN, 22));
        spacing.setForeground(UIUtilities.LIGHT_BEIGE);
        bar.add(spacing);
        topPanel.add(bar, BorderLayout.NORTH);

    }

    private void addTabBar() {
        UIManager.put("TabbedPane.borderColor", UIUtilities.WHITE);
        JTabbedPane tabs = new JTabbedPane();
        ridesTabFrame = new TabFrame(TableCategory.RIDES, provider);
        vehiclesTabFrame = new TabFrame(TableCategory.VEHICLES, provider);
        passengersTabFrame = new TabFrame(TableCategory.PASSENGERS, provider);
        passengerCategoriesTabFrame = new TabFrame(TableCategory.PASSENGER_CATEGORY, provider);
        rideCategoriesTabFrame = new TabFrame(TableCategory.RIDE_CATEGORY, provider);
        statistics = new Statistics();
        tabs.setFont(UIUtilities.fTab);
        tabs.addTab("Jízdy", ridesTabFrame.getMainPanel());
        tabs.addTab("Vozidla", vehiclesTabFrame.getMainPanel());
        tabs.addTab("Cestující", passengersTabFrame.getMainPanel());
        tabs.addTab("Kategorie cestujících", passengerCategoriesTabFrame.getMainPanel());
        tabs.addTab("Kategorie jízd", rideCategoriesTabFrame.getMainPanel());
        tabs.addTab("Statistiky", statistics.getMain());
        tabs.setBackground(UIUtilities.WHITE);
        tabs.addChangeListener(this);
        tabs.setForeground(UIUtilities.TEXT_BROWN);


        tabs.setBackgroundAt(5, UIUtilities.OCHER);

        topPanel.add(tabs, BorderLayout.CENTER);
        frame.add(topPanel);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex == 5) {
            statistics.update();
        }
    }
}

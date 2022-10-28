package cz.muni.fi.pv168.seminar01.beta.UI;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private static JFrame frame;
    private static TabFrame ridesTabFrame;
    private static TabFrame vehiclesTabFrame;
    private static TabFrame passengersTabFrame;

    private static JPanel topPanel;

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
        bar.setBackground(UIConstants.LIGHT_BEIGE);
        bar.setForeground(UIConstants.LIGHT_BEIGE);
        JLabel spacing = new JLabel("Muminci <3");
        spacing.setFont(new Font("Arial", Font.PLAIN, 22));
        spacing.setForeground(UIConstants.LIGHT_BEIGE);
        bar.add(spacing);
        topPanel.add(bar, BorderLayout.NORTH);

    }

    private void addTabBar() {
        UIManager.put("TabbedPane.borderColor", UIConstants.WHITE);
        JTabbedPane tabs = new JTabbedPane();
        /*rides.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();
                    RideDetailDialog dialog = new RideDetailDialog()
                    JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
                }*/
        ridesTabFrame = new TabFrame(TableCategory.RIDES);
        vehiclesTabFrame = new TabFrame(TableCategory.VEHICLES);
        passengersTabFrame = new TabFrame(TableCategory.PASSENGERS);
        Statistics statistics = new Statistics();
        tabs.setFont(UIConstants.fTab);
        tabs.addTab("Jízdy", ridesTabFrame.getMainPanel());
        tabs.addTab("Vozidla", vehiclesTabFrame.getMainPanel());
        tabs.addTab("Cestující", passengersTabFrame.getMainPanel());
        tabs.addTab("Statistiky", statistics.getMain());
        tabs.setBackground(UIConstants.WHITE);

        tabs.setForeground(UIConstants.TEXT_BROWN);


        tabs.setBackgroundAt(3, UIConstants.OCHER);

        topPanel.add(tabs, BorderLayout.CENTER);
        frame.add(topPanel);
    }
}

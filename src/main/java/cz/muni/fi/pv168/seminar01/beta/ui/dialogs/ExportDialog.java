package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.PassengerCategoryExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.PassengerExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.RideCategoryExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.RideExporter;
import cz.muni.fi.pv168.seminar01.beta.data.manipulation.VehicleExporter;
import cz.muni.fi.pv168.seminar01.beta.data.validation.ValidationException;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Kateřina Vácová
 */
public class ExportDialog extends DialogBase {
    private File rides = null;
    private File vehicles = null;
    private File passengers = null;
    private File ridesCategories = null;
    private File passengersCategories = null;

    public ExportDialog(Frame frame, String name) {
        super(frame, name);
    }

    public void initializeBottom(JPanel bottom) {
        JButton cancel = new JButton("Zrušit");
        cancel.addActionListener(e -> dispose());
        JButton exportButton = new JButton("Exportovat");
        onExportButton(exportButton);
        UIUtilities.formatDefaultComponent(cancel);
        UIUtilities.formatDefaultComponent(exportButton);
        bottom.add(cancel);
        bottom.add(exportButton);
    }

    public void initializeCenter(JPanel center) {
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        UIUtilities.formatWhiteTextBrownDialog(center);

        JButton exportRidesButton = new JButton("Vyberte složku pro export");
        JTextArea exportRidesPath = new JTextArea();
        exportRidesPath.setEditable(false);
        JScrollPane ridesPathScroll = new JScrollPane(exportRidesPath);
        exportRidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ridesFileChooser = new JFileChooser();
                ridesFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int dialogResult = ridesFileChooser.showOpenDialog(center);
                if (dialogResult == JFileChooser.APPROVE_OPTION) {
                    rides = new File(ridesFileChooser.getSelectedFile() + "/rides.csv");
                    vehicles = new File(ridesFileChooser.getSelectedFile() + "/vehicles.csv");
                    passengers = new File(ridesFileChooser.getSelectedFile() + "/passengers.csv");
                    ridesCategories = new File(ridesFileChooser.getSelectedFile() + "/ridesCategories.csv");
                    passengersCategories = new File(ridesFileChooser.getSelectedFile() + "/passengersCategories.csv");
                }
                exportRidesPath.setText(String.valueOf(ridesFileChooser.getSelectedFile()));
            }
        });
        exportRidesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(exportRidesButton);
        center.add(ridesPathScroll);

        this.add(center);
        setSize(300, 160);

    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    private void onExportButton(JButton exportButton) {
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passengers == null || vehicles == null || rides == null ||
                        passengersCategories == null || ridesCategories == null) {
                    //throw new ValidationException("Some files are missing");
                    new ErrorDialog(MainWindow.getFrame(), new ValidationException("Some files are missing"));
                } else {
                    RideExporter rideExporter = new RideExporter();
                    rideExporter.export((List<Ride>) CommonElementSupplier.getTableModel(TableCategory.RIDES).getData(),
                            rides.getAbsolutePath());
                    VehicleExporter vehicleExporter = new VehicleExporter();
                    vehicleExporter.export((List<Vehicle>) CommonElementSupplier.getTableModel(TableCategory.VEHICLES).getData(),
                            vehicles.getAbsolutePath());
                    PassengerExporter passengerExporter = new PassengerExporter();
                    passengerExporter.export((List<Passenger>) CommonElementSupplier.getTableModel(TableCategory.PASSENGERS).getData(),
                            passengers.getAbsolutePath());
                    PassengerCategoryExporter passengerCategoryExporter = new PassengerCategoryExporter();
                    passengerCategoryExporter.export((List<PassengerCategory>) CommonElementSupplier.getTableModel(TableCategory.PASSENGER_CATEGORY).getData(),
                            passengersCategories.getAbsolutePath());
                    RideCategoryExporter rideCategoryExporter = new RideCategoryExporter();
                    rideCategoryExporter.export((List<RideCategory>) CommonElementSupplier.getTableModel(TableCategory.RIDE_CATEGORY).getData(),
                            ridesCategories.getAbsolutePath());
                    dispose();
                }
            }
        });
    }
}

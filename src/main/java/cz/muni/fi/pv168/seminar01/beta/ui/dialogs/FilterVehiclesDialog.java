package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FilterVehiclesDialog extends SortFilterDialog {
    private JCheckBox capacityFilter;
    private JCheckBox consumptionFilter;
    private JTextField capacityFrom;
    private JTextField capacityTo;
    private JTextField consumptionFrom;
    private JTextField consumptionTo;
   // private JComboBox<Integer> brand;

    public FilterVehiclesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {

    }

    public void setAttributes() {
        capacityFilter = new JCheckBox("  Kapacita:");
        consumptionFilter = new JCheckBox("  Spotřeba:");
        capacityFrom = UIUtilities.createTextField();
        capacityTo = UIUtilities.createTextField();
        consumptionFrom = UIUtilities.createTextField();
        consumptionTo = UIUtilities.createTextField();
       // brand = new JComboBox<>();
       // UIUtilities.formatDefaultJComboBox(brand);
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(6, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        String paragraph = "      ";
        center.add(capacityFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(capacityFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(capacityTo);
        center.add(consumptionFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(consumptionFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(consumptionTo);
        //center.add(new JLabel("  •  Značka"));
        //
        // center.add(brand);
        this.add(center);
        setSize(450, 350);
    }

    public void onOkButton(JButton ok) {
        ok.addActionListener(actionListener -> {
            RowFilter<ShareCarRiderTableModel<Vehicle>, Integer> rfCapacity = new RowFilter<>() {
                @Override
                public boolean include(Entry<? extends ShareCarRiderTableModel<Vehicle>, ? extends Integer> entry) {
                    return true;
                }
            };
            RowFilter<ShareCarRiderTableModel<Vehicle>, Integer> rfConsumption = new RowFilter<>() {
                @Override
                public boolean include(Entry<? extends ShareCarRiderTableModel<Vehicle>, ? extends Integer> entry) {
                    return true;
                }
            };

            ShareCarRiderTable table = Shortcut.getTable(TableCategory.VEHICLES);
            TableRowSorter<ShareCarRiderTableModel<Vehicle>> sorter
                    = new TableRowSorter<>((ShareCarRiderTableModel<Vehicle>) table.getModel());

            if (capacityFilter.isSelected()) {
                if (!isValidCapacityFilterInput()) {
                    return;
                }

                rfCapacity = new RowFilter<>() {
                    @Override
                    public boolean include(Entry<? extends ShareCarRiderTableModel<Vehicle>, ? extends Integer> entry) {
                        ShareCarRiderTableModel<Vehicle> vehicleModel = entry.getModel();
                        Vehicle vehicle = vehicleModel.getEntity(entry.getIdentifier());

                        return vehicle.getCapacity() >= Integer.parseInt(capacityFrom.getText())
                                && vehicle.getCapacity() <= Integer.parseInt(capacityTo.getText());
                    }
                };
            }

            if (consumptionFilter.isSelected()) {
                if (!isValidConsumptionFilterInput()) {
                    return;
                }

                rfConsumption = new RowFilter<>() {
                    @Override
                    public boolean include(Entry<? extends ShareCarRiderTableModel<Vehicle>, ? extends Integer> entry) {
                        ShareCarRiderTableModel<Vehicle> vehicleModel = entry.getModel();
                        Vehicle vehicle = vehicleModel.getEntity(entry.getIdentifier());

                        return vehicle.getConsumption() >= Double.parseDouble(consumptionFrom.getText())
                                && vehicle.getConsumption() <= Double.parseDouble(consumptionTo.getText());
                    }
                };
            }
            List<RowFilter<ShareCarRiderTableModel<Vehicle>, Integer>> listRfs = new ArrayList<>();
            listRfs.add(rfConsumption);
            listRfs.add(rfCapacity);
            sorter.setRowFilter(RowFilter.andFilter(listRfs));
            table.setRowSorter(sorter);

            dispose();
        });
    }

    private boolean isValidCapacityFilterInput() {
        try {
            Integer.parseInt(capacityFrom.getText());
            Integer.parseInt(capacityTo.getText());
            return true;
        }
        catch (NumberFormatException e) {
            new ErrorDialog(MainWindow.getFrame(), "Kapacita musí být celé číslo.");
            return false;
        }
    }

    private boolean isValidConsumptionFilterInput() {
        try {
            Double.parseDouble(consumptionFrom.getText());
            Double.parseDouble(consumptionTo.getText());
            return true;
        }
        catch (NumberFormatException e) {
            new ErrorDialog(MainWindow.getFrame(), "Spotřeba musí být reálné číslo.");
            return false;
        }
    }
}

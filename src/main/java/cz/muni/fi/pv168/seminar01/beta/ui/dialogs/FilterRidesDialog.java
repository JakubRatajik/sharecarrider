package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilterRidesDialog extends SortFilterDialog {
    private JCheckBox dateFilter;
    private JCheckBox distanceFilter;
    private JDatePicker dateFrom;
    private JDatePicker dateTo;
    private JTextField distanceFrom;
    private JTextField distanceTo;
    private JScrollPane categories;
    private JList<RideCategory> categoriesList;
    private JCheckBox repetitionFilter;
    private JRadioButton repeating;
    private JRadioButton nonRepeating;

    private static int[] selectedCategoryIndices = {};
    private static boolean dateFilterEnabled = false;
    private static boolean distanceFilterEnabled = false;
    private static boolean repetitionFilterEnabled = false;
    private static boolean repeatingSelected = false;
    private static boolean nonRepeatingSelected = false;
    private static String distanceFromText = "";
    private static String distanceToText = "";


    public FilterRidesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {
    }

    public void setAttributes() {
        dateFilter = new JCheckBox(" Datum");
        distanceFilter = new JCheckBox(" Vzdálenost");
        repetitionFilter = new JCheckBox(" Opakující se jízdy");
        repetitionFilter.setSelected(repetitionFilterEnabled);
        repeating = new JRadioButton(" Opakujici");
        repeating.setSelected(repeatingSelected);
        nonRepeating = new JRadioButton(" Neopakujici");
        nonRepeating.setSelected(nonRepeatingSelected);
        dateFilter.setSelected(dateFilterEnabled);
        distanceFilter.setSelected(distanceFilterEnabled);
        dateFrom = new JDatePicker();
        UIUtilities.formatDefaultComponent(dateFrom);
        dateTo = new JDatePicker();
        UIUtilities.formatDefaultComponent(dateTo);
        distanceFrom = UIUtilities.createTextField();
        distanceTo = UIUtilities.createTextField();
        distanceFrom.setText(distanceFromText);
        distanceTo.setText(distanceToText);

        DefaultListModel<RideCategory> l1 = new DefaultListModel<>();
        List<RideCategory> categoriesL = (List<RideCategory>) Shortcut.getTableModel(TableCategory.RIDE_CATEGORY).getData();
        l1.addAll(categoriesL);
        categoriesList = new JList<>(l1);
        categoriesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean hasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

                if (isSelected) {
                    component.setBackground(UIUtilities.MIDDLE_BROWN);
                }

                setText(value.toString());

                return component;
            }
        });
        UIUtilities.formatDefaultComponent(categoriesList);
        categoriesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        categories = new JScrollPane(categoriesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIUtilities.formatDefaultComponent(categories);

        for (int index : selectedCategoryIndices) {
            categoriesList.addSelectionInterval(index, index);
        }
    }

    public void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(10, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        String paragraph = "      ";
        center.add(dateFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(dateFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(dateTo);
        center.add(distanceFilter);
        center.add(new JLabel(" "));
        center.add(new JLabel(paragraph + "•  Od:"));
        center.add(distanceFrom);
        center.add(new JLabel(paragraph + "•  Do:"));
        center.add(distanceTo);
        center.add(new JLabel("  •  Kategorie"));
        center.add(categories);
        center.add(repetitionFilter);
        center.add(repeating);
        center.add(new JLabel(paragraph));
        center.add(nonRepeating);
        this.add(center);
        setSize(450, 460);
    }

    public void onOkButton(JButton ok) {
        ok.addActionListener(actionListener -> {
            RowFilter<ShareCarRiderTableModel<Ride>, Integer> rfDate = new RowFilter<>() {
                @Override
                public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                    return true;
                }
            };
            RowFilter<ShareCarRiderTableModel<Ride>, Integer> rfDistance = new RowFilter<>() {
                @Override
                public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                    return true;
                }
            };
            RowFilter<ShareCarRiderTableModel<Ride>, Integer> rfRepetition = new RowFilter<>() {
                @Override
                public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                    return true;
                }
            };
            List<RideCategory> selectedValuesList = categoriesList.getSelectedValuesList();
            selectedCategoryIndices = categoriesList.getSelectedIndices();

            StringBuilder sb = new StringBuilder("^.*$");
            for (RideCategory category: selectedValuesList) {
                sb.insert(1, String.format("(?=.*\\b%s\\b)", category.getName()));
            }

            RowFilter<ShareCarRiderTableModel<Ride>, Integer> rfCategories = RowFilter.regexFilter(sb.toString());

            if (dateFilter.isSelected()) {
                if (!isValidDateFilterInput()) {
                    return;
                }
                rfDate = new RowFilter<>() {
                    @Override
                    public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                        ShareCarRiderTableModel<Ride> rideModel = entry.getModel();
                        Ride ride = rideModel.getEntity(entry.getIdentifier());
                        DateModel<?> fromModel = dateFrom.getModel();
                        DateModel<?> toModel = dateTo.getModel();
                        LocalDate from = LocalDate.of(fromModel.getYear(), fromModel.getMonth() + 1, fromModel.getDay());
                        LocalDate to = LocalDate.of(toModel.getYear(), toModel.getMonth() + 1, toModel.getDay());

                        return ride.getDate().compareTo(from) >= 0
                                && ride.getDate().compareTo(to) <= 0;
                    }
                };
            }

            if (distanceFilter.isSelected()) {
                if (!isValidDistanceFilterInput()) {
                    return;
                }

                rfDistance = new RowFilter<>() {
                    @Override
                    public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                        ShareCarRiderTableModel<Ride> rideModel = entry.getModel();
                        Ride ride = rideModel.getEntity(entry.getIdentifier());

                        return ride.getDistance() >= Integer.parseInt(distanceFrom.getText())
                                && ride.getDistance() <= Integer.parseInt(distanceTo.getText());
                    }
                };
            }

            if (repetitionFilter.isSelected()) {
                if (repeating.isSelected()) {
                    rfRepetition = new RowFilter<>() {
                        @Override
                        public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                            ShareCarRiderTableModel<Ride> rideModel = entry.getModel();
                            Ride ride = rideModel.getEntity(entry.getIdentifier());

                            return ride.getRepetition() != Repetition.NONE;
                        }
                    };
                }
                else if (nonRepeating.isSelected()) {
                    rfRepetition = new RowFilter<>() {
                        @Override
                        public boolean include(Entry<? extends ShareCarRiderTableModel<Ride>, ? extends Integer> entry) {
                            ShareCarRiderTableModel<Ride> rideModel = entry.getModel();
                            Ride ride = rideModel.getEntity(entry.getIdentifier());

                            return ride.getRepetition() == Repetition.NONE;
                        }
                    };
                }
            }

            List<RowFilter<ShareCarRiderTableModel<Ride>, Integer>> listRfs = new ArrayList<>();
            ShareCarRiderTable table = Shortcut.getTable(TableCategory.RIDES);
            TableRowSorter<ShareCarRiderTableModel<Ride>> sorter
                    = new TableRowSorter<>((ShareCarRiderTableModel<Ride>) table.getModel());

            listRfs.add(rfDate);
            listRfs.add(rfDistance);
            listRfs.add(rfCategories);
            listRfs.add(rfRepetition);
            sorter.setRowFilter(RowFilter.andFilter(listRfs));
            table.setRowSorter(sorter);

            table.changeLocalDateRenderer(false);

            dateFilterEnabled = dateFilter.isSelected();
            distanceFilterEnabled = distanceFilter.isSelected();
            distanceFromText = distanceFrom.getText();
            distanceToText = distanceTo.getText();
            repetitionFilterEnabled = repetitionFilter.isSelected();

            dispose();
        });
    }

    public static void clearAttributes() {
        dateFilterEnabled = false;
        distanceFilterEnabled = false;
        distanceFromText = "";
        distanceToText = "";
        selectedCategoryIndices = new int[]{};
        repetitionFilterEnabled = false;
    }

    private boolean isValidDistanceFilterInput() {
        try {
            Integer.parseInt(distanceFrom.getText());
            Integer.parseInt(distanceTo.getText());
            return true;
        }
        catch (NumberFormatException e) {
            new ErrorDialog(MainWindow.getFrame(), "Vzdálenost musí být celé číslo.");
            return false;
        }
    }

    private boolean isValidDateFilterInput() {
        if (dateFrom.getModel().getValue() == null
                || dateTo.getModel().getValue() == null) {
            new ErrorDialog(MainWindow.getFrame(), "Nebyl zvolen datum.");
            return false;
        }
        return true;
    }
}

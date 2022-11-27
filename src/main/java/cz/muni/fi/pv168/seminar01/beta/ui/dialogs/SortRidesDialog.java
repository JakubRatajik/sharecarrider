package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.RideTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortRidesDialog extends SortFilterDialog {
    private JRadioButton dateMinMax;
    private JRadioButton dateMaxMin;
    private JRadioButton distanceMinMax;
    private JRadioButton distanceMaxMin;

    public SortRidesDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {
    }

    @Override
    protected void onOkButton(JButton ok) {
        ok.addActionListener(al -> {
            ShareCarRiderTable rideTable = Shortcut.getTable(TableCategory.RIDES);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            if (dateMaxMin.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(RideTableModel.COLUMN_DATE, SortOrder.ASCENDING));
                rideTable.getRowSorter().setSortKeys(sortKeys);
            } else if (dateMinMax.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(RideTableModel.COLUMN_DATE, SortOrder.DESCENDING));
                rideTable.getRowSorter().setSortKeys(sortKeys);
            } else if (distanceMaxMin.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(RideTableModel.COLUMN_DISTANCE, SortOrder.DESCENDING));
                rideTable.getRowSorter().setSortKeys(sortKeys);
            } else if (distanceMinMax.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(RideTableModel.COLUMN_DISTANCE, SortOrder.ASCENDING));
                rideTable.getRowSorter().setSortKeys(sortKeys);
            }

            dispose();
        });
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(6, 1));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Datum"));
        center.add(dateMinMax);
        center.add(dateMaxMin);
        center.add(new JLabel("•  Vzdálenost"));
        center.add(distanceMinMax);
        center.add(distanceMaxMin);
        this.add(center);
        setSize(280, 200);
    }

    @Override
    protected void setAttributes() {
        dateMinMax = new JRadioButton("Od nejnovějších k nejstarším");
        dateMaxMin = new JRadioButton("Od nejstarších k nejnovějším");
        distanceMinMax = new JRadioButton("Od nejkratších k nejdelším");
        distanceMaxMin = new JRadioButton("Od nejdelších k nejkratším");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dateMaxMin);
        buttonGroup.add(dateMinMax);
        buttonGroup.add(distanceMaxMin);
        buttonGroup.add(distanceMinMax);
    }
}

package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.ui.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SortPassengersDialog extends SortFilterDialog {
    private JRadioButton firstNameMinMax;
    private JRadioButton firstNameMaxMin;
    private JRadioButton surnameMinMax;
    private JRadioButton surnameMaxMin;

    private static boolean firstNameMinMaxSelected = false;
    private static boolean firstNameMaxMinSelected = false;
    private static boolean surnameMinMaxSelected = false;
    private static boolean surnameMaxMinSelected = false;

    public SortPassengersDialog(Frame frame, String name) {
        super(frame, name);
    }

    @Override
    protected void addAttribute(Object attribute) {}

    @Override
    protected void onOkButton(JButton ok) {
        ok.addActionListener(al -> {
            ShareCarRiderTable passengerTable = CommonElementSupplier.getTable(TableCategory.PASSENGERS);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();

            if (firstNameMaxMin.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(PassengerTableModel.COLUMN_FIRSTNAME, SortOrder.DESCENDING));
                passengerTable.getRowSorter().setSortKeys(sortKeys);
            } else if (firstNameMinMax.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(PassengerTableModel.COLUMN_FIRSTNAME, SortOrder.ASCENDING));
                passengerTable.getRowSorter().setSortKeys(sortKeys);
            } else if (surnameMaxMin.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(PassengerTableModel.COLUMN_LASTNAME, SortOrder.DESCENDING));
                passengerTable.getRowSorter().setSortKeys(sortKeys);
            } else if (surnameMinMax.isSelected()) {
                sortKeys.add(new RowSorter.SortKey(PassengerTableModel.COLUMN_LASTNAME, SortOrder.ASCENDING));
                passengerTable.getRowSorter().setSortKeys(sortKeys);
            }

            firstNameMinMaxSelected = firstNameMinMax.isSelected();
            firstNameMaxMinSelected = firstNameMaxMin.isSelected();
            surnameMinMaxSelected = surnameMinMax.isSelected();
            surnameMaxMinSelected = surnameMaxMin.isSelected();

            dispose();
        });
    }

    @Override
    protected void initializeContent(JPanel center) {
        center.setLayout(new GridLayout(6, 1));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Jméno"));
        center.add(firstNameMinMax);
        center.add(firstNameMaxMin);
        center.add(new JLabel("•  Příjmení"));
        center.add(surnameMinMax);
        center.add(surnameMaxMin);
        this.add(center);
        setSize(280, 200);
    }

    @Override
    protected void setAttributes() {
        firstNameMinMax = new JRadioButton("A - Z");
        firstNameMaxMin = new JRadioButton("Z - A");
        surnameMinMax = new JRadioButton("A - Z");
        surnameMaxMin = new JRadioButton("Z - A");
        firstNameMinMax.setSelected(firstNameMinMaxSelected);
        firstNameMaxMin.setSelected(firstNameMaxMinSelected);
        surnameMinMax.setSelected(surnameMinMaxSelected);
        surnameMaxMin.setSelected(surnameMaxMinSelected);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(firstNameMaxMin);
        buttonGroup.add(firstNameMinMax);
        buttonGroup.add(surnameMaxMin);
        buttonGroup.add(surnameMinMax);
    }
}

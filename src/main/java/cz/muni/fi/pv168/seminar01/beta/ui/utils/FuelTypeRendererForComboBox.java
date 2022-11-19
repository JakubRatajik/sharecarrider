package cz.muni.fi.pv168.seminar01.beta.ui.utils;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;

import javax.swing.*;
import java.awt.*;

/**
 * This class provides custom rendering for FuelType in ComboBox.
 * Instead of FuelType enum value, getDescription method is used for displayed text.
 */
public class FuelTypeRendererForComboBox extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((FuelType) value).getDescription());
        return this;
    }
}

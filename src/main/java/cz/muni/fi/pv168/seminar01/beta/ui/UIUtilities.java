package cz.muni.fi.pv168.seminar01.beta.ui;

import javax.swing.*;
import java.awt.*;

public class UIUtilities {
    public static final Color DARK_BROWN = new Color(0x4B4237);
    public static final Color LIGHT_BEIGE = new Color(0xEDE7D9);
    public static final Color OCHER = new Color(0xE3B74F);
    public static final Color WHITE = new Color(0xFFFFFF);
    public static final Color MIDDLE_BROWN = new Color(0xA49694);
    public static final Color TEXT_BROWN = new Color(0x24201B);

    public static final Font fTab = new Font("Inter", Font.PLAIN, 17);
    public static final Font fMenu = new Font("Inter", Font.PLAIN, 15);
    public static final Font fTable = new Font("Inter", Font.PLAIN, 15);
    public static final Font fDialog = new Font("Inter", Font.PLAIN, 12);

    public static final int LEFT_FRAME_INDENT = 60;
    public static final int RIGHT_FRAME_INDENT = 60;

    public static JTextField createTextField() {
        JTextField field = new JTextField();
        formatDefaultComponent(field);
        return field;
    }

    public static void formatDefaultComponent(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(LIGHT_BEIGE);
        component.setFont(fDialog);
        component.putClientProperty("JComponent.outline", UIUtilities.MIDDLE_BROWN);
    }

    public static void formatDefaultJComboBox(JComboBox<?> jComboBox) {
        formatDefaultComponent(jComboBox);
        jComboBox.setRenderer(new DefaultListCellRenderer() {
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

                return component;
            }
        });
    }

    public static void formatDefaultJComboBox(JComboBox<?> jComboBox, DefaultListCellRenderer renderer) {
        formatDefaultJComboBox(jComboBox);
        jComboBox.setRenderer(renderer);
    }

    public static void formatBeigeTextBrownDialog(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(LIGHT_BEIGE);
        component.setFont(fDialog);
        component.putClientProperty("JComponent.outline", UIUtilities.MIDDLE_BROWN);
    }

    public static void formatInverseBeigeTextBrownComponent(JComponent component) {
        component.setForeground(LIGHT_BEIGE);
        component.setBackground(MIDDLE_BROWN);
        component.setFont(fDialog);
        component.putClientProperty("JComponent.outline", UIUtilities.MIDDLE_BROWN);
    }

    public static void formatWhiteTextBrownDialog(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(WHITE);
        component.setFont(fDialog);
        component.putClientProperty("JComponent.outline", UIUtilities.MIDDLE_BROWN);
    }

    public static void formatWhiteTextBrownMenu(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(WHITE);
        component.setFont(fMenu);
        component.putClientProperty("JComponent.outline", UIUtilities.MIDDLE_BROWN);
    }
}

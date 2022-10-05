package cz.muni.fi.pv168.seminar01.beta.UI;

import javax.swing.*;
import java.awt.*;

public class UIConstants {
    public static final Color DARK_BROWN = new Color(0x4B4237);
    public static final Color LIGHT_BEIGE = new Color(0xEDE7D9);
    public static final Color OCHER= new Color(0xE3B74F);
    public static final Color WHITE = new Color(0xFFFFFF);
    public static final Color MIDDLE_BROWN = new Color(0xA49694);
    public static final Color TEXT_BROWN= new Color(0x24201B);

    public static final Font fTab = new Font("Inter", Font.PLAIN, 17);
    public static final Font fMenu= new Font("Inter", Font.PLAIN, 15);
    public static final Font fDialog= new Font("Inter", Font.PLAIN, 12);

    public static JTextField createTextField() {
        JTextField field = new JTextField();
        formatComponentDialog(field);
        return field;
    }

    public static void formatComponentDialog(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(LIGHT_BEIGE);
        component.setFont(fDialog);
    }

    public static void formatBeigeTextBrownDialog(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(LIGHT_BEIGE);
        component.setFont(fDialog);
    }

    public static void formatWhiteTextBrownDialog(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(WHITE);
        component.setFont(fDialog);
    }

    public static void formatWhiteTextBrownMenu(JComponent component) {
        component.setForeground(TEXT_BROWN);
        component.setBackground(WHITE);
        component.setFont(fMenu);
    }


}

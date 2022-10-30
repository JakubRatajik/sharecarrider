package cz.muni.fi.pv168.seminar01.beta;

import com.formdev.flatlaf.FlatIntelliJLaf;
import cz.muni.fi.pv168.seminar01.beta.Data.SampleUsage;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;

import javax.swing.*;

/**
 * The entry point of the application.
 */
public class Main {

    private Main() {
        throw new AssertionError("This class is not intended for instantiation.");
    }


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Cannot load wanted LaF");
        }

        SampleUsage.generateSampleData();
        MainWindow window = new MainWindow();


    }
}


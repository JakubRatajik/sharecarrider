package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import cz.muni.fi.pv168.seminar01.beta.Model.Ride;
import cz.muni.fi.pv168.seminar01.beta.UI.Dialogs.RideDetailDialog;
import cz.muni.fi.pv168.seminar01.beta.UI.MainWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * @author Jakub Ratajik
 */
public class RideTableModel extends ShareCarRiderTableModel {
    public RideTableModel() {
        super(new String[]{"Datum", "Odjezd", "Začátek", "Cíl", "Vzdálenost", "Kategorie"},
                new Object[][] {
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12,42,35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"}
                });
    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> columnClass;

        switch(col) {
            case 0 -> columnClass = LocalDate.class;
            case 1 -> columnClass = LocalTime.class;
            case 4 -> columnClass = int.class;
            default -> columnClass = String.class;
        }

        return columnClass;
    }

    @Override
    public Ride getEntity(int modelRow) {
        return null;
    }
}

package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import cz.muni.fi.pv168.seminar01.beta.Model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * @author Jakub Ratajik
 */
public class RideTableModel extends ShareCarRiderTableModel {
    public RideTableModel() {
        super(new String[]{"Datum", "Odjezd", "Začátek", "Cíl", "Vzdálenost", "Kategorie"},
                new Object[][]{
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"},
                        {LocalDate.of(1925, 12, 1), LocalTime.of(12, 42, 35), "Skybar", "Kino Scala", 3, "Work"},
                        {LocalDate.now(), LocalTime.now(), "Vranov nad Topľou", "Supíkovce", 987, "Party"}
                });
    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> columnClass;

        switch (col) {
            case 0 -> columnClass = LocalDate.class;
            case 1 -> columnClass = LocalTime.class;
            case 4 -> columnClass = int.class;
            default -> columnClass = String.class;
        }

        return columnClass;
    }

//    @Override
//    public Object getValueAt(int row, int col) {
//        Object value = "??";
//        Ride ride = (Ride) data[row];
//
//        switch (col) {
//            case 0:
//                value = user.getUserUsername();
//                break;
//            case 1:
//                value = user.getUserName();
//                break;
//            case 2:
//                value = user.getUserPhone();
//                break;
//            case 3:
//                value = user.getUserNic();
//                break;
//            case 4:
//                value = user.getUserAddress();
//                break;
//            case 5:
//                value = user.getUserEmail();
//                break;
//        }
//
//        return value;
//    }
//
    @Override
    public Ride getEntity(int modelRow) {
        return null;
    }
}

package cz.muni.fi.pv168.seminar01.beta.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ride {

    private LocalDate date;
    private LocalTime time;
    private String start;
    private String finish;
    private int distance;
    private Vehicle vehicle;
    private Passenger[] passengers;

    public Ride(LocalDate date, LocalTime time, String start, String finish, int distance,
                Vehicle vehicle, Passenger[] passengers) {
        this.date = date;
        this.time = time;
        this.start = start;
        this.finish = finish;
        this.distance = distance;
        this.vehicle = vehicle;
        this.passengers = passengers;
    }
}

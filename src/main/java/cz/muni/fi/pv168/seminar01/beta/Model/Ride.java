package cz.muni.fi.pv168.seminar01.beta.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ride {
    private LocalDate date;
    private LocalTime time;
    private String from;
    private String to;
    private int distance;
    private Set<String> categories;
    private List<Passenger> passengers;
    private Vehicle vehicle;
    private Repetition repetition;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Ride(LocalDate date, LocalTime time, String from, String to, int distance, Collection<String> categories,
                List<Passenger> passengers, Vehicle vehicle, Repetition repetition) {
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.categories = new HashSet<>(categories);
        this.passengers = passengers;
        this.vehicle = vehicle;
        this.repetition = repetition;
    }

    public String getDate() {
        return date.format(dateFormatter);
    }

    public String getTime() {
        return time.format(timeFormatter);
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public void removeCategory(String category) {
        categories.remove(category);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    // getters and setters

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Collection<String> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Collection<String> categories) {
        this.categories = new HashSet<>(categories);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Repetition getRepetition() {
        return repetition;
    }

    public void setRepetition(Repetition repetition) {
        this.repetition = repetition;
    }
}

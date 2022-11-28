package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.DateTimeUtils;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a ride with ID.
 */
public class Ride implements HasID {
    private final long id;
    private LocalDate date;
    private LocalTime departure;
    private LocalTime arrival;
    private String from;
    private String to;
    private int distance;
    private Set<RideCategory> categories;
    private Set<Passenger> passengers;
    private Vehicle vehicle;
    private Repetition repetition;
    private String description;

    public Ride(LocalDate date, LocalTime departure, LocalTime arrival, String from, String to, int distance, Collection<RideCategory> categories,
                Collection<Passenger> passengers, Vehicle vehicle, Repetition repetition, String description) {
        this(IDGenerator.getNewID(Ride.class), date, departure, arrival, from, to, distance, categories, passengers, vehicle, repetition, description);
    }

    public Ride(long id, LocalDate date, LocalTime departure, LocalTime arrival, String from, String to, int distance, Collection<RideCategory> categories,
                Collection<Passenger> passengers, Vehicle vehicle, Repetition repetition, String description) {
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.categories = new HashSet<>(categories);
        this.passengers = new HashSet<>(passengers);
        this.vehicle = vehicle;
        this.repetition = repetition;
        this.id = id;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateUnformatted() {
        return date.toString();
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public String getDepartureFormatted() {
        return departure.format(DateTimeUtils.TIME_FORMATTER);
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public void addCategory(RideCategory rideCategory) {
        categories.add(rideCategory);
    }

    public void removeCategory(RideCategory rideCategory) {
        categories.remove(rideCategory);
    }

    // getters and setters

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Collection<RideCategory> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Collection<RideCategory> categories) {
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

    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }

    public void setPassengers(Collection<Passenger> passengers) {
        this.passengers = new HashSet<>(passengers);
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

    public LocalTime getArrival() {
        return arrival;
    }

    public String getArrivalFormatted() {
        return arrival.format(DateTimeUtils.TIME_FORMATTER);
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "date=" + date +
                ", time=" + departure +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", distance=" + distance +
                ", categories=" + categories +
                ", passengers=" + passengers +
                ", vehicle=" + vehicle +
                ", repetition=" + repetition +
                ", id=" + id +
                '}';
    }

    public BigDecimal countPrice() {
        BigDecimal BDdistance = BigDecimal.valueOf(distance);
        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal BDConsumption = BigDecimal.valueOf(vehicle.getConsumption());
        BigDecimal BDFuelPrice = MainWindow.getFuelPrice().getFuelPrice(vehicle.getFuelType());
        // MainWindow.getFuelPrice() is temporary solution, check MainWindow for more info

        return BDdistance
                .divide(hundred)
                .multiply(BDConsumption)
                .multiply(BDFuelPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return id == ride.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getCategoryNames() {
        return categories.stream()
                .map(RideCategory::getName)
                .collect(Collectors.joining(", "));
    }
}

package cz.muni.fi.pv168.seminar01.beta.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final long id;
    private LocalDate date;
    private LocalTime time;
    private String from;
    private String to;
    private int distance;
    private Set<RideCategory> categories;
    private Set<Passenger> passengers;
    private Vehicle vehicle;
    private Repetition repetition;

    public Ride(LocalDate date, LocalTime time, String from, String to, int distance, Collection<RideCategory> categories,
                Collection<Passenger> passengers, Vehicle vehicle, Repetition repetition) {
        this(IDGenerator.getNewID(Ride.class), date, time, from, to, distance, categories, passengers, vehicle, repetition);
    }

    public Ride(long id, LocalDate date, LocalTime time, String from, String to, int distance, Collection<RideCategory> categories,
                Collection<Passenger> passengers, Vehicle vehicle, Repetition repetition) {
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.categories = new HashSet<>(categories);
        this.passengers = new HashSet<>(passengers);
        this.vehicle = vehicle;
        this.repetition = repetition;
        this.id = id;
    }

    public String getDate() {
        return date.format(dateFormatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateUnformatted() {
        return date.toString();
    }

    public String getTime() {
        return time.format(timeFormatter);
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "date=" + date +
                ", time=" + time +
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
        BigDecimal BDFuelPrice = FuelPrice.getFuelPrice(vehicle.getFuelType());

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

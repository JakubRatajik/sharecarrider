package cz.muni.fi.pv168.seminar01.beta.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a passenger with ID.
 */
public class Passenger implements HasID {
    private final long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<PassengerCategory> categories;

    public Passenger(String firstName, String lastName, String phoneNumber, Collection<PassengerCategory> categories) {
        this(IDGenerator.getNewID(Passenger.class), firstName, lastName, phoneNumber, categories);
    }

    public Passenger(long id, String firstName, String lastName, String phoneNumber, Collection<PassengerCategory> categories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        if (!isPhoneNumberValid()) {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
        this.categories = new HashSet<>(categories);
        this.id = id;
    }

    private boolean isPhoneNumberValid() {
        return phoneNumber.matches("[+]?\\d+");
    }

    public void addCategory(PassengerCategory rideCategory) {
        categories.add(rideCategory);
    }

    public void removeCategory(PassengerCategory rideCategory) {
        categories.remove(rideCategory);
    }

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!isPhoneNumberValid()) {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
        this.phoneNumber = phoneNumber;
    }

    public Set<PassengerCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<PassengerCategory> categories) {
        this.categories = categories;
    }

    public String getCategoryNames() {
        return categories.stream()
                .map(PassengerCategory::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", categories=" + categories +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

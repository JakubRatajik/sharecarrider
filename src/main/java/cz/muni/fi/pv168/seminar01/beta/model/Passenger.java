package cz.muni.fi.pv168.seminar01.beta.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a passenger with ID.
 */
public class Passenger implements HasID {
    private final long id;
    private final Set<PassengerCat> categories = new HashSet<>();
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Passenger(String firstName, String lastName, String phoneNumber, Set<PassengerCat> categories) {
        this(IDGenerator.getNewID(Passenger.class), firstName, lastName, phoneNumber, categories);
    }

    public Passenger(long id, String firstName, String lastName, String phoneNumber, Set<PassengerCat> categories) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setCategories(categories);
        this.id = id;
    }

    public void addCategory(PassengerCat passengerCategory) {
        categories.add(passengerCategory);
    }

    public void removeCategory(PassengerCat passengerCategory) {
        categories.remove(passengerCategory);
    }

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "first name must not be null");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "last name must not be null");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phone number must not be null");
    }

    public Set<PassengerCat> getCategories() {
        return categories;
    }

    public Set<Long> getCategoryIDs() {
        Set<Long> set = new HashSet<>();
        for(PassengerCat cat: categories) {
            set.add(cat.getId());
        }
        return set;
    }

    public void setCategories(Set<PassengerCat> categories) {
        this.categories.clear();
        this.categories.addAll(Objects.requireNonNull(categories, "categories must not be null"));
    }

    public String getCategoryNames() {
        return categories.stream()
                .map(PassengerCat::getName)
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

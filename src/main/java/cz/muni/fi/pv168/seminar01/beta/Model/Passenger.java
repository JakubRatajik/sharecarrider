package cz.muni.fi.pv168.seminar01.beta.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Passenger {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<Category> categories;
    private final int id;

    public Passenger(String firstName, String lastName, String phoneNumber, Collection<Category> categories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        if (!isPhoneNumberValid()) {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
        this.categories = new HashSet<>(categories);
        id = IDGenerator.getNewID();
    }

    private boolean isPhoneNumberValid() {
        return phoneNumber.matches("[+]?\\d+");
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getId() {
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
}

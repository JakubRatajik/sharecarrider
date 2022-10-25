package cz.muni.fi.pv168.seminar01.beta.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Passenger {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public Set<String> categories;

    public Passenger(String firstName, String lastName, String phoneNumber, Collection<String> categories) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        if (!isPhoneNumberValid()) {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
        this.categories = new HashSet<>(categories);
    }

    private boolean isPhoneNumberValid() {
        return phoneNumber.matches("[+]?\\d+");
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public void removeCategory(String category) {
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

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}

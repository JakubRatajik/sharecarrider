package cz.muni.fi.pv168.seminar01.beta.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Ride {
    private LocalDateTime dateTime;
    private String from;
    private String to;
    private int distance;
    private Set<String> categories;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Ride(LocalDateTime dateTime, String from, String to, int distance, Collection<String> categories) {
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.categories = new HashSet<>(categories);
    }

    public String getDate() {
        return dateTime.format(dateFormatter);
    }

    public String getTime() {
        return dateTime.format(timeFormatter);
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public void removeCategory(String category) {
        categories.remove(category);
    }

    // getters and setters

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Collection<String> getCategories() {
        return categories;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

package cz.muni.fi.pv168.seminar01.beta.Model;

import java.awt.*;
import java.util.Objects;

public class RideCategory {
    private final Color color;
    private final String name;
    private final int id;

    public RideCategory(Color color, String name) {
        this.color = color;
        this.name = name;
        id = IDGenerator.getNewID(this.getClass());
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "color=" + color +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideCategory rideCategory = (RideCategory) o;
        return id == rideCategory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

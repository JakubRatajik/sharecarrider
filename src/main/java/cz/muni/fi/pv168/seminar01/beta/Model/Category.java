package cz.muni.fi.pv168.seminar01.beta.Model;

import java.awt.*;
import java.util.Objects;

public class Category {
    private final Color color;
    private final String name;
    private final int id;

    public Category(Color color, String name) {
        this.color = color;
        this.name = name;
        id = IDGenerator.getNewID();
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
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

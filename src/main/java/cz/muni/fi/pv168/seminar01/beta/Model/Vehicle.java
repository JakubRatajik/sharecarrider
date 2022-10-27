package cz.muni.fi.pv168.seminar01.beta.Model;

import java.util.Objects;

public class Vehicle {
    private String brand;
    private String type;
    private int capacity;
    private float consumption;
    private final int id;

    public Vehicle(String brand, String type, int capacity, float consumption) {
        this.brand = brand;
        this.type = type;
        this.capacity = capacity;
        this.consumption = consumption;
        id = IDGenerator.getNewID();
    }

    // getters and setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", consumption=" + consumption +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

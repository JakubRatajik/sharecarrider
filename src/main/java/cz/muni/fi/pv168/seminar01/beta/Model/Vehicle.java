package cz.muni.fi.pv168.seminar01.beta.Model;

import java.util.Objects;

public class Vehicle {
    private final int id;
    private final FuelType fuelType;
    private String licensePlate;
    private String brand;
    private String type;
    private int capacity;
    private float consumption;

    public Vehicle(String licensePlate, String brand, String type, int capacity, float consumption, FuelType fuelType) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.type = type;
        this.capacity = capacity;
        this.consumption = consumption;
        this.fuelType = fuelType;
        id = IDGenerator.getNewID(this.getClass());
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
                "licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", consumption=" + consumption +
                ", id=" + id +
                '}';
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public String getCapacityString() {
        String result = String.valueOf(capacity);
        switch (capacity) {
            case 1 -> result += " člověk";
            case 2, 3, 4 -> result += " lidi";
            default -> result += " lidí";
        }
        return result;
    }
}

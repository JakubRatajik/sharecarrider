package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;

import java.math.BigDecimal;
import java.util.Objects;

public class Vehicle implements HasID {
    private final long id;
    private FuelType fuelType;
    private String licensePlate;
    private String brand;
    private String type;
    private int capacity;
    private double consumption;

    public Vehicle(String licensePlate, String brand, String type, int capacity, double consumption, FuelType fuelType) {
        this(IDGenerator.getNewID(Vehicle.class), licensePlate, brand, type, capacity, consumption, fuelType);
    }

    public Vehicle(long id, String licensePlate, String brand, String type, int capacity, double consumption, FuelType fuelType) {
        this.id = id;
        setLicensePlate(licensePlate);
        this.brand = brand;
        this.type = type;
        this.capacity = capacity;
        this.consumption = consumption;
        this.fuelType = fuelType;
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

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return brand + " " + type + " (" + licensePlate + ")";
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = Objects.requireNonNull(licensePlate).toUpperCase();
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

    public void setFuelType(FuelType fuel) {
        fuelType = fuel;
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

    public BigDecimal countPricePerHundredKM() {
        BigDecimal fuelPrice;
        if (MainWindow.getFuelPrice() == null) {
            fuelPrice = new BigDecimal("30");
        } else {
            fuelPrice = MainWindow.getFuelPrice().getFuelPrice(fuelType);
        }

        return fuelPrice.multiply(new BigDecimal(consumption));
    }
}

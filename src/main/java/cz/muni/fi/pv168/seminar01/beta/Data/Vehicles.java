package cz.muni.fi.pv168.seminar01.beta.Data;

public class Vehicles {
    public String brand;
    public String type;
    public int capacity;
    public float consumption;

    public Vehicles(String brand, String type, int capacity, float consumption) {
        this.brand = brand;
        this.type = type;
        this.capacity = capacity;
        this.consumption = consumption;
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
}

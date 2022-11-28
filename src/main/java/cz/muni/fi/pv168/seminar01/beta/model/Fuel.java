package cz.muni.fi.pv168.seminar01.beta.model;

import java.math.BigDecimal;

/**
 * @author Jan Macecek
 */
public class Fuel implements HasID {
    private FuelType fuel;
    private BigDecimal fuelPrice;

    public Fuel(FuelType fuelType, BigDecimal fuelPrice) {
        this.fuel = fuelType;
        this.fuelPrice = fuelPrice;
    }

    @Override
    public long getId() {
        return fuel.ordinal();
    }

    public BigDecimal getPrice() {
        return fuelPrice;
    }

    public void setPrice(BigDecimal price)  {
        fuelPrice = price;
    }
}

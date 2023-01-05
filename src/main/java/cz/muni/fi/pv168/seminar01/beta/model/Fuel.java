package cz.muni.fi.pv168.seminar01.beta.model;

import java.math.BigDecimal;

/**
 * @author Jan Macecek
 */
public class Fuel implements HasID {
    private final FuelType fuelType;
    private BigDecimal fuelPrice;

    public Fuel(FuelType fuelType, BigDecimal fuelPrice) {
        this.fuelType = fuelType;
        this.fuelPrice = fuelPrice;
    }

    @Override
    public long getId() {
        return fuelType.ordinal();
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public BigDecimal getPrice() {
        return fuelPrice;
    }

    public void setPrice(BigDecimal price)  {
        fuelPrice = price;
    }
}

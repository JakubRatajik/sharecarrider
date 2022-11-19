package cz.muni.fi.pv168.seminar01.beta.model;

import java.math.BigDecimal;

/**
 * This class represents static values of fuel prices, that are used for computing a price for a single ride and some statistics.
 */
public class FuelPrice {
    private static final BigDecimal DEFAULT_PRICE = new BigDecimal("-1");
    private static final String NOT_SET = "Not set";
    private BigDecimal gasolinePrice;
    private BigDecimal electricityPrice;
    private BigDecimal LPGPrice;
    private BigDecimal CNGPrice;
    private BigDecimal dieselPrice;

    public FuelPrice() {
        dieselPrice = DEFAULT_PRICE;
        gasolinePrice = DEFAULT_PRICE;
        LPGPrice = DEFAULT_PRICE;
        CNGPrice = DEFAULT_PRICE;
        electricityPrice = DEFAULT_PRICE;
    }

    public BigDecimal getFuelPrice(FuelType fuelType) {
        return switch (fuelType) {
            case LPG -> LPGPrice;
            case CNG -> CNGPrice;
            case DIESEL -> dieselPrice;
            case GASOLINE -> gasolinePrice;
            case ELECTRICITY -> electricityPrice;
            default -> BigDecimal.ZERO;
        };
    }

    public BigDecimal getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(BigDecimal gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public BigDecimal getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(BigDecimal electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public BigDecimal getLPGPrice() {
        return LPGPrice;
    }

    public void setLPGPrice(BigDecimal LPGPrice) {
        this.LPGPrice = LPGPrice;
    }

    public BigDecimal getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(BigDecimal dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    public BigDecimal getCNGPrice() {
        return CNGPrice;
    }

    public void setCNGPrice(BigDecimal CNGPrice) {
        this.CNGPrice = CNGPrice;
    }

    public String getGasolinePriceString() {
        if (gasolinePrice.compareTo(BigDecimal.ZERO) < 0) {
            return NOT_SET;
        }
        return gasolinePrice.toString();
    }

    public String getElectricityPriceString() {
        if (electricityPrice.compareTo(BigDecimal.ZERO) < 0) {
            return NOT_SET;
        }
        return electricityPrice.toString();
    }

    public String getLPGPriceString() {
        if (LPGPrice.compareTo(BigDecimal.ZERO) < 0) {
            return NOT_SET;
        }
        return LPGPrice.toString();
    }

    public String getDieselPriceString() {
        if (dieselPrice.compareTo(BigDecimal.ZERO) < 0) {
            return NOT_SET;
        }
        return dieselPrice.toString();
    }

    public String getCNGPriceString() {
        if (CNGPrice.compareTo(BigDecimal.ZERO) < 0) {
            return NOT_SET;
        }
        return CNGPrice.toString();
    }
}

package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.FuelRepository;

import java.math.BigDecimal;

/**
 * This class represents static values of fuel prices, that are used for computing a price for a single ride and some statistics.
 */
public class FuelPrice {
    private final FuelRepository fuels = new FuelRepository();

    public FuelPrice() {

    }

    public BigDecimal getFuelPrice(FuelType fuelType) {
        return switch (fuelType) {
            case LPG -> {
                Fuel fuel = fuels.findById(FuelType.LPG.ordinal()).orElse(null);
                if (fuel == null) {
                    throw new NullPointerException("Fuel of type " + FuelType.LPG.toString() + " not in the database");
                }
                yield fuel.getPrice();
            }
            case CNG -> {
                Fuel fuel = fuels.findById(FuelType.CNG.ordinal()).orElse(null);
                if (fuel == null) {
                    throw new NullPointerException("Fuel of type " + FuelType.CNG.toString() + " not in the database");
                }
                yield fuel.getPrice();
            }

            case DIESEL -> {
                Fuel fuel = fuels.findById(FuelType.DIESEL.ordinal()).orElse(null);
                if (fuel == null) {
                    throw new NullPointerException("Fuel of type " + FuelType.DIESEL.toString() + " not in the database");
                }
                yield fuel.getPrice();
            }
            case GASOLINE -> {
                Fuel fuel = fuels.findById(FuelType.GASOLINE.ordinal()).orElse(null);
                if (fuel == null) {
                    throw new NullPointerException("Fuel of type " + FuelType.GASOLINE.toString() + " not in the database");
                }
                yield fuel.getPrice();
            }
            case ELECTRICITY -> {
                Fuel fuel = fuels.findById(FuelType.ELECTRICITY.ordinal()).orElse(null);
                if (fuel == null) {
                    throw new NullPointerException("Fuel of type " + FuelType.ELECTRICITY.toString() + " not in the database");
                }
                yield fuel.getPrice();
            }
            default -> BigDecimal.ZERO;
        };
    }

    public BigDecimal getGasolinePrice() {
        return getFuelPrice(FuelType.GASOLINE);
    }

    public void setGasolinePrice(BigDecimal gasolinePrice) {
        fuels.update(new Fuel(FuelType.GASOLINE, gasolinePrice));
    }

    public BigDecimal getElectricityPrice() {
        return getFuelPrice(FuelType.ELECTRICITY);
    }

    public void setElectricityPrice(BigDecimal electricityPrice) {
        fuels.update(new Fuel(FuelType.ELECTRICITY, electricityPrice));
    }

    public BigDecimal getLPGPrice() {
        return getFuelPrice(FuelType.LPG);
    }

    public void setLPGPrice(BigDecimal LPGPrice) {
        fuels.update(new Fuel(FuelType.LPG, LPGPrice));
    }

    public BigDecimal getDieselPrice() {
        return getFuelPrice(FuelType.DIESEL);
    }

    public void setDieselPrice(BigDecimal dieselPrice) {
        fuels.update(new Fuel(FuelType.DIESEL, dieselPrice));
    }

    public BigDecimal getCNGPrice() {
        return getFuelPrice(FuelType.CNG);
    }

    public void setCNGPrice(BigDecimal CNGPrice) {
        fuels.update(new Fuel(FuelType.CNG, CNGPrice));
    }

    public String getGasolinePriceString() {
        return getGasolinePrice().toString();
    }

    public String getElectricityPriceString() {
        return getElectricityPrice().toString();
    }

    public String getLPGPriceString() {
        return getLPGPrice().toString();
    }

    public String getDieselPriceString() {
        return getDieselPrice().toString();
    }

    public String getCNGPriceString() {
        return getCNGPrice().toString();
    }
}

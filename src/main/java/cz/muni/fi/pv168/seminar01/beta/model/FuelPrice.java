package cz.muni.fi.pv168.seminar01.beta.model;

import cz.muni.fi.pv168.seminar01.beta.data.storage.repository.FuelRepository;
import cz.muni.fi.pv168.seminar01.beta.wiring.ProductionDependencyProvider;

import java.math.BigDecimal;

/**
 * This class represents static values of fuel prices, that are used for computing a price for a single ride and some statistics.
 */
public class FuelPrice {
    private final FuelRepository fuels;

    public FuelPrice(ProductionDependencyProvider provider) {
        fuels = (FuelRepository) provider.getFuelRepository();
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
        Fuel fuel = fuels.findAll().stream().filter(f -> f.getFuel() == FuelType.GASOLINE).findFirst().orElseThrow();
        fuel.setPrice(gasolinePrice);
        fuels.update(fuel);
    }

    public BigDecimal getElectricityPrice() {
        return getFuelPrice(FuelType.ELECTRICITY);
    }

    public void setElectricityPrice(BigDecimal electricityPrice) {
        Fuel fuel = fuels.findAll().stream().filter(f -> f.getFuel() == FuelType.ELECTRICITY).findFirst().orElseThrow();
        fuel.setPrice(electricityPrice);
        fuels.update(fuel);
    }

    public BigDecimal getLPGPrice() {
        return getFuelPrice(FuelType.LPG);
    }

    public void setLPGPrice(BigDecimal LPGPrice) {
        Fuel fuel = fuels.findAll().stream().filter(f -> f.getFuel() == FuelType.LPG).findFirst().orElseThrow();
        fuel.setPrice(LPGPrice);
        fuels.update(fuel);
    }

    public BigDecimal getDieselPrice() {
        return getFuelPrice(FuelType.DIESEL);
    }

    public void setDieselPrice(BigDecimal dieselPrice) {
        Fuel fuel = fuels.findAll().stream().filter(f -> f.getFuel() == FuelType.DIESEL).findFirst().orElseThrow();
        fuel.setPrice(dieselPrice);
        fuels.update(fuel);
    }

    public BigDecimal getCNGPrice() {
        return getFuelPrice(FuelType.CNG);
    }

    public void setCNGPrice(BigDecimal CNGPrice) {
        Fuel fuel = fuels.findAll().stream().filter(f -> f.getFuel() == FuelType.CNG).findFirst().orElseThrow();
        fuel.setPrice(CNGPrice);
        fuels.update(fuel);
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

package cz.muni.fi.pv168.seminar01.beta.model;

import java.math.BigDecimal;

public class FuelPrice {
    private static BigDecimal gasolinePrice = new BigDecimal("45.5");
    private static BigDecimal electricityPrice = new BigDecimal("7.2");
    private static BigDecimal LPGPrice = new BigDecimal("21.3");
    private static BigDecimal dieselPrice = new BigDecimal("49.7");
    private static BigDecimal randomPrice = new BigDecimal("43.2");

    private FuelPrice() {
    }

    public static BigDecimal getFuelPrice(FuelType fuelType) {
        return switch (fuelType) {
            case LPG -> LPGPrice;
            case DIESEL -> dieselPrice;
            case GASOLINE -> gasolinePrice;
            case ELECTRICITY -> electricityPrice;
            default -> randomPrice;
        };
    }

    public static BigDecimal getGasolinePrice() {
        return gasolinePrice;
    }

    public static void setGasolinePrice(BigDecimal gasolinePrice) {
        FuelPrice.gasolinePrice = gasolinePrice;
    }

    public static BigDecimal getElectricityPrice() {
        return electricityPrice;
    }

    public static void setElectricityPrice(BigDecimal electricityPrice) {
        FuelPrice.electricityPrice = electricityPrice;
    }

    public static BigDecimal getLPGPrice() {
        return LPGPrice;
    }

    public static void setLPGPrice(BigDecimal LPGPrice) {
        FuelPrice.LPGPrice = LPGPrice;
    }

    public static BigDecimal getDieselPrice() {
        return dieselPrice;
    }

    public static void setDieselPrice(BigDecimal dieselPrice) {
        FuelPrice.dieselPrice = dieselPrice;
    }
}

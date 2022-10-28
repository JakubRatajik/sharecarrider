package cz.muni.fi.pv168.seminar01.beta.Model;

public class FuelPrice {
    private static double gasolinePrice = 45.5;
    private static double electricityPrice = 7.2;
    private static double LPGPrice = 21.3;
    private static double dieselPrice = 49.7;
    private static double randomPrice = 43.2;

    private FuelPrice() {
    }

    public static double getFuelPrice(FuelType fuelType) {
        double price;

        switch (fuelType) {
            case LPG -> price = LPGPrice;
            case DIESEL -> price = dieselPrice;
            case GASOLINE -> price = gasolinePrice;
            case ELECTRICITY -> price = electricityPrice;
            default -> price = randomPrice;
        }

        return price;
    }

    public static double getGasolinePrice() {
        return gasolinePrice;
    }

    public static void setGasolinePrice(double gasolinePrice) {
        FuelPrice.gasolinePrice = gasolinePrice;
    }

    public static double getElectricityPrice() {
        return electricityPrice;
    }

    public static void setElectricityPrice(double electricityPrice) {
        FuelPrice.electricityPrice = electricityPrice;
    }

    public static double getLPGPrice() {
        return LPGPrice;
    }

    public static void setLPGPrice(double LPGPrice) {
        FuelPrice.LPGPrice = LPGPrice;
    }

    public static double getDieselPrice() {
        return dieselPrice;
    }

    public static void setDieselPrice(double dieselPrice) {
        FuelPrice.dieselPrice = dieselPrice;
    }
}

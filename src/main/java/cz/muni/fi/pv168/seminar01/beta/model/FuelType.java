package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * Types of fuel that a vehicle can drive on.
 *
 * if changes of names of fuels needed, then needed to be change also in database
 */
public enum FuelType implements EnumWithDescription {
    DIESEL("nafta"),
    GASOLINE("benzin"),
    LPG("LPG"),
    CNG("CNG"),
    ELECTRICITY("elektřina");

    private final String description;

    FuelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * Types of repeating of a ride.
 */
public enum Repetition implements EnumWithDescription {
    NONE("neopakovat"),
    DAILY("denně"),
    WEEKLY("týdně"),
    MONTHLY("měsíčně"),
    YEARLY("ročně");

    private final String description;

    Repetition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

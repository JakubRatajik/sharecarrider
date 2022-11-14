package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * @author Jakub Ratajik
 */
public enum PassengerCategory {
    WORK,
    FAMILY,
    FRIENDS,
    OTHER;

    public static PassengerCategory fromString(String str) {
        return switch (str) {
            case "Práce" -> WORK;
            case "Jiné" -> OTHER;
            case "Rodina" -> FAMILY;
            case "Přátelé" -> FRIENDS;
            default -> null;
        };
    }

    @Override
    public String toString() {
        String string;

        switch (this) {
            case WORK -> string = "Práce";
            case OTHER -> string = "Jiné";
            case FAMILY -> string = "Rodina";
            case FRIENDS -> string = "Přátelé";
            default -> string = "";
        }

        return string;
    }
}

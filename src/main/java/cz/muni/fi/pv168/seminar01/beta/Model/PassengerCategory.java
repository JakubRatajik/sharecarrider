package cz.muni.fi.pv168.seminar01.beta.Model;

/**
 * @author Jakub Ratajik
 */
public enum PassengerCategory {
    WORK,
    FAMILY,
    FRIENDS,
    OTHER;

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

package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

public class ManipulationUtils {

    public static final int VEHICLE_PARAMETERS_COUNT = 7;
    public static final int PASSENGER_PARAMETERS_COUNT = 5;
    public static final int RIDE_PARAMETERS_COUNT = 12;
    public static final int PASSENGER_CATEGORIES_PARAMETER_COUNT = 2;
    public static final int RIDE_CATEGORIES_PARAMETER_COUNT = 2;

    public static final String SEPARATOR = ";";

    public static String[] listParser(String list) {
        list = list.substring(1, list.length() - 1);
        return list.split(", ");
    }

    public static void trimAllStringsInArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

    public static String[] splitter(String lineToSplit) {
        return lineToSplit.split(ManipulationUtils.SEPARATOR);
    }
}

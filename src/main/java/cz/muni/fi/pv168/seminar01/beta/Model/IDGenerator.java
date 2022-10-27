package cz.muni.fi.pv168.seminar01.beta.Model;

import java.util.HashMap;
import java.util.Map;

public class IDGenerator {
    private static final Map<Integer, Integer> IDs = new HashMap<>();

    /**
     * Creates unique ID for supported classes. Only Ride, Category, Passenger and Vehicle classes
     * are supported (this ensures uniqueness).
     *
     * It uses first letter (as int) from class name as a prefix.
     *
     * @param caller - One of the supported classes.
     * @return unique ID
     */
    public static int getNewID(Class<?> caller) {
        int prefix;
        if (
                caller.equals(Ride.class) ||
                caller.equals(Category.class) ||
                caller.equals(Passenger.class) ||
                caller.equals(Vehicle.class)
        ) {
            String[] parsedName = caller.getName().split("\\.");
            prefix = parsedName[parsedName.length - 1].charAt(0);
        }
        else {
            throw new IllegalArgumentException("This class is not supported by IDGenerator.");
        }

        if (IDs.containsKey(prefix)) {
            IDs.put(prefix, IDs.get(prefix) + 1);
            return Integer.parseInt("" + prefix + IDs.get(prefix));
        } else {
            IDs.put(prefix, 0);
            return Integer.parseInt("" + prefix + 0);
        }
    }
}

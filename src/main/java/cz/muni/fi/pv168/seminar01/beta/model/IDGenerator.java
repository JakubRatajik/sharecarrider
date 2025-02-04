package cz.muni.fi.pv168.seminar01.beta.model;

import java.util.HashMap;
import java.util.Map;

/**
 * TEMPORARY SOLUTION!
 * This class will be replaced by database auto-generator.
 * The database will generate IDs automatically as soon as it is implemented.
 * <p>
 * Class for ID generation.
 */
public class IDGenerator {
    private static final Map<Long, Long> IDs = new HashMap<>();

    /**
     * Creates unique ID for supported classes. Only Ride, Category, Passenger and Vehicle classes
     * are supported (this ensures uniqueness).
     * <p>
     * It uses first letter (as Long) from class name as a prefix.
     *
     * @param caller - One of the supported classes.
     * @return unique ID
     */
    public static long getNewID(Class<?> caller) {
        long prefix;
        if (
                caller.equals(Ride.class) ||
                        caller.equals(Passenger.class) ||
                        caller.equals(Vehicle.class) ||
                        caller.equals(Category.class)
        ) {
            String[] parsedName = caller.getName().trim().split("\\.");
            prefix = parsedName[parsedName.length - 1].charAt(0);
        } else {
            throw new IllegalArgumentException("This class is not supported by IDGenerator.");
        }

        if (IDs.containsKey(prefix)) {
            IDs.put(prefix, IDs.get(prefix) + 1);
            return Long.parseLong("" + prefix + IDs.get(prefix));
        } else {
            IDs.put(prefix, 0L);
            return Long.parseLong("" + prefix + 0);
        }
    }
}

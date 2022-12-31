package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;

import java.util.HashMap;

public class IDImporterMapper {
    private final HashMap<Long, Long> passengerMapper;
    private final HashMap<Long, Long> vehicleMapper;
    private final HashMap<Long, Long> rideCategoryMapper;
    private final HashMap<Long, Long> passengerCategoryMapper;

    public IDImporterMapper() {
        passengerMapper = new HashMap<Long, Long>();
        vehicleMapper = new HashMap<Long, Long>();
        rideCategoryMapper = new HashMap<Long, Long>();
        passengerCategoryMapper = new HashMap<Long, Long>();
    }
    public void addIDs(TableCategory category, long oldID, long newID) {
        switch (category) {
            case PASSENGERS -> passengerMapper.put(oldID, newID);
            case VEHICLES -> vehicleMapper.put(oldID, newID);
            case RIDE_CATEGORY -> rideCategoryMapper.put(oldID, newID);
            case PASSENGER_CATEGORY -> passengerCategoryMapper.put(oldID, newID);
            default -> throw new RuntimeException("OTHER VALUE CANNOT BE GIVEN INTO THIS FUNCTION");
        }
    }

    public long getNewID(TableCategory category, long oldID) {
        return switch (category) {
            case PASSENGERS -> passengerMapper.get(oldID);
            case VEHICLES -> vehicleMapper.get(oldID);
            case RIDE_CATEGORY -> rideCategoryMapper.get(oldID);
            case PASSENGER_CATEGORY -> passengerCategoryMapper.get(oldID);
            default -> throw new RuntimeException("OTHER VALUE CANNOT BE GIVEN INTO THIS FUNCTION");
        };
    }
}

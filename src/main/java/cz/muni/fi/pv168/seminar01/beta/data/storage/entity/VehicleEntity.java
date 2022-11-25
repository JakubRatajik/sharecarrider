package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

public record VehicleEntity(
        Long id,
        String licensePlate,
        String brand,
        String type,
        int capacity,
        double consumption,
        String fuelType
) {
    public VehicleEntity(
            String licensePlate,
            String brand,
            String type,
            int capacity,
            double consumption,
            String fuelType
    ) {
        this(null, licensePlate, brand, type, capacity, consumption, fuelType);
    }
}

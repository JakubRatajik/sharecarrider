package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;

public record VehicleEntity(
        Long id,
        String licensePlate,
        String brand,
        String type,
        int capacity,
        float consumption,
        FuelType fuelType
) {
    public VehicleEntity(
            String licensePlate,
            String brand,
            String type,
            int capacity,
            float consumption,
            FuelType fuelType
    ) {
        this(null, licensePlate, brand, type, capacity, consumption, fuelType);
    }
}

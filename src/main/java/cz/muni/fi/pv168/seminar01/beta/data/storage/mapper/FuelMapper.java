package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.FuelEntity;
import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;

public class FuelMapper implements EntityMapper<FuelEntity, Fuel> {
    @Override
    public FuelEntity mapToEntity(Fuel model) {
        // TODO - validator
        return new FuelEntity(model.getFuelType().name(), model.getPrice());
    }

    @Override
    public Fuel mapToModel(FuelEntity entity) {
        return new Fuel(FuelType.valueOf(entity.fuelType()), entity.price());
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.VehicleEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

public class VehicleMapper implements EntityMapper<VehicleEntity, Vehicle>{
    @Override
    public VehicleEntity mapToEntity(Vehicle model) {
        VehicleValidator.validateVehicle(model.getId()+"",
                model.getLicensePlate(),
                model.getBrand(),
                model.getType(),
                model.getCapacity()+"",
                model.getConsumption()+"",
                model.getFuelType().toString());

        return new VehicleEntity(model.getId(),
                model.getLicensePlate(),
                model.getBrand(),
                model.getType(),
                model.getCapacity(),
                model.getConsumption(),
                model.getFuelType());
    }

    @Override
    public Vehicle mapToModel(VehicleEntity entity) {
        return new Vehicle(entity.id(), entity.licensePlate(), entity.brand(), entity.type(), entity.capacity(), entity.consumption(), entity.fuelType());
    }
}

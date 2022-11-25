package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.VehicleEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.PassengerValidator;
import cz.muni.fi.pv168.seminar01.beta.data.validation.VehicleValidator;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;

public class PassengerMapper implements EntityMapper<PassengerEntity, Passenger> {
    @Override
    public PassengerEntity mapToEntity(Passenger model) {
        PassengerValidator.validatePassenger(model.getId()+"", model.getFirstName(), model.getLastName(), model.getPhoneNumber(),model.getCategoryIDs().toString());

        return new PassengerEntity(model.getId(),
                model.getFirstName(),
                model.getLastName(),
                model.getPhoneNumber());
    }

    @Override
    public Passenger mapToModel(PassengerEntity entity) {
        return
    }
}

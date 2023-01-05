package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.VehicleDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.RideValidator;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;

import java.util.ArrayList;
import java.util.Optional;

public class RideMapper implements EntityMapper<RideEntity, Ride>{
    @Override
    public RideEntity mapToEntity(Ride model) {
        RideValidator.validateRide(model.getId()+"", model.getDate().toString(), model.getDepartureFormatted(), model.getArrivalFormatted(), model.getFrom(), model.getTo(), model.getDistance()+"", null, null, model.getVehicle().getId()+"", model.getRepetition().toString(), model.getDescription() );

        return new RideEntity(model.getId(),
                model.getDate(),
                model.getDeparture(),
                model.getArrival(),
                model.getFrom(),
                model.getTo(),
                model.getDistance(),
                model.getVehicle().getId(),
                model.getRepetition(),
                model.getDescription());
    }

    @Override
    public Ride mapToModel(RideEntity entity) {
        return new Ride(entity.id(),
                entity.date(),
                entity.departure(),
                entity.arrival(),
                entity.startDest(),
                entity.endDest(),
                entity.distance(),
                new ArrayList<>(),
                new ArrayList<>(),
                null,
                entity.repetition(),
                entity.description());
    }

    public Ride mapToModel(RideEntity entity, VehicleMapper vehicleMapper, VehicleDao vehicleDao) {
        return new Ride(entity.id(),
                entity.date(),
                entity.departure(),
                entity.arrival(),
                entity.startDest(),
                entity.endDest(),
                entity.distance(),
                new ArrayList<>(),
                new ArrayList<>(),
                (vehicleDao.findById(entity.vehicleId()))
                        .stream()
                        .map(vehicleMapper::mapToModel)
                        .findFirst().orElse(null),
                entity.repetition(),
                entity.description());
    }
}

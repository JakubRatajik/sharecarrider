package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.RideValidator;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;

public class RideMapper implements EntityMapper<RideEntity, Ride>{

    public RideMapper() {

    }
    @Override
    public RideEntity mapToEntity(Ride model) {
        RideValidator.validateRide(model.getId()+"", model.getDate().toString(), model.getDeparture().toString(), model.getArrival().toString(), model.getFrom(), model.getTo(), model.getDistance()+"", null, null, model.getVehicle()+"", model.getRepetition().toString(), model.getDescription() );


        //--TODO - new RideEntity - dont know how to make date from string
        return null;
    }

    @Override
    public Ride mapToModel(RideEntity entity) {
        return null;
    }
}

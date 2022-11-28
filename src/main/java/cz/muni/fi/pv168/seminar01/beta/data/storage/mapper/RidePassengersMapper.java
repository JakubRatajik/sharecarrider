package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RidePassengersEntity;
import cz.muni.fi.pv168.seminar01.beta.model.IdToId;

public class RidePassengersMapper implements EntityMapper<RidePassengersEntity, IdToId>{

    public RidePassengersMapper() {

    }

    @Override
    public RidePassengersEntity mapToEntity(IdToId model) {
        return new RidePassengersEntity(model.getId1(), model.getId2());
    }

    @Override
    public IdToId mapToModel(RidePassengersEntity entity) {
        return new IdToId(entity.rideId(), entity.passengerId());
    }
}

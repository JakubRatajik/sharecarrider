package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoriesEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideCategoriesEntity;
import cz.muni.fi.pv168.seminar01.beta.model.IdToId;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;

public class RideCategoriesMapper implements EntityMapper<RideCategoriesEntity, IdToId> {

    public RideCategoriesMapper() {

    }


    @Override
    public RideCategoriesEntity mapToEntity(IdToId model) {
        return new RideCategoriesEntity(model.getId1(), model.getId2());
    }

    @Override
    public IdToId mapToModel(RideCategoriesEntity entity) {
        return new IdToId(entity.rideId(), entity.rideCategoryId());
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoriesEntity;
import cz.muni.fi.pv168.seminar01.beta.model.IdToId;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;

public class PassengerCategoriesMapper implements EntityMapper<PassengerCategoriesEntity, IdToId> {

    public PassengerCategoriesMapper() {

    }

    @Override
    public PassengerCategoriesEntity mapToEntity(IdToId model) {
        return new PassengerCategoriesEntity(model.getId1(), model.getId2());
    }

    @Override
    public IdToId mapToModel(PassengerCategoriesEntity entity) {
        return new IdToId(entity.passengerId(), entity.passengerCategoryId());
    }
}

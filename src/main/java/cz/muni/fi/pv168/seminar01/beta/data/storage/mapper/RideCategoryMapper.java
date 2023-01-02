package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;

import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoryEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideCategoryEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.CategoryValidator;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

public class RideCategoryMapper implements EntityMapper<RideCategoryEntity, RideCategory>{

    public RideCategoryMapper() {

    }
    @Override
    public RideCategoryEntity mapToEntity(RideCategory model) {
        CategoryValidator.validateRideCategory(model.getId()+"", model.getName());

        return new RideCategoryEntity(
                model.getId(),
                model.getName());
    }

    @Override
    public RideCategory mapToModel(RideCategoryEntity entity) {
        return new RideCategory(
                entity.id(), entity.name()
        );
    }
}

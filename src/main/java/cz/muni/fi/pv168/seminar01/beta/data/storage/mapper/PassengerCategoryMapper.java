package cz.muni.fi.pv168.seminar01.beta.data.storage.mapper;


import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoryEntity;
import cz.muni.fi.pv168.seminar01.beta.data.validation.CategoryValidator;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;


/**
 * Mapper from the {@link PassengerCategoryEntity} to {@link PassengerCategory}.
 */
public final class PassengerCategoryMapper implements EntityMapper<PassengerCategoryEntity, PassengerCategory> {


    public PassengerCategoryMapper() {
        
    }

    @Override
    public PassengerCategory mapToModel(PassengerCategoryEntity entity) {
        return new PassengerCategory(
                entity.id(), entity.name()
        );
    }

    @Override
    public PassengerCategoryEntity mapToEntity(PassengerCategory source) {
        CategoryValidator.validatePassengerCategory(source.getId()+"", source.getName());

        return new PassengerCategoryEntity(
                source.getId(),
                source.getName()
        );
    }
}


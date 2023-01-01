package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoriesEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoryEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerMapper;
import cz.muni.fi.pv168.seminar01.beta.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class PassengerRepository extends AbstractRepository<Passenger>{
    private PassengerMapper mapper;
    private PassengerDao dao;

    private PassengerCategoryMapper categoryMapper;

    private PassengerCategoryDao categoryDao;
    public PassengerRepository(PassengerMapper mapper, PassengerDao dao, PassengerCategoryMapper categoryMapper, PassengerCategoryDao categoryDao) {
        super();
        this.mapper = mapper;
        this.dao = dao;
        this.categoryMapper = categoryMapper;
        this.categoryDao = categoryDao;
        refresh();
    }

    @Override
    public void refresh() {
        repositoryMembers = fetchAll();
    }

    @Override
    public void create(Passenger passenger) {
        createAndGetID(passenger);

    }
    @Override
    public long createAndGetID(Passenger passenger) {
        Passenger newPassenger = Stream.of(passenger)
                .map(mapper::mapToEntity)
                .map(dao::create)
                .map(mapper::mapToModel)
                .findFirst().orElse(null);
        newPassenger.setCategories(passenger.getCategories());
        dao.createJoins(newPassenger.getId(), passenger.getCategories());
        repositoryMembers.add(newPassenger);
        return newPassenger.getId();
    }

    @Override
    public void update(Passenger passenger) {
        int index = repositoryMembers.indexOf(passenger);
        Passenger newPassenger = Stream.of(passenger)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel).findFirst().orElse(null);
        dao.deletePassengerCategories(passenger.getId());
        dao.createJoins(passenger.getId(), passenger.getCategories());
        newPassenger.setCategories(passenger.getCategories());
        repositoryMembers.set(index, newPassenger);

    }

    @Override
    public void deleteByIndex(int index) {
        Passenger passenger = repositoryMembers.get(index);
        dao.deletePassengerCategories(passenger.getId());
        dao.deleteById(passenger.getId());
        repositoryMembers.remove(index);
    }

    private List<PassengerCategory> findCategoriesForPassenger(Passenger passenger) {
        return dao.findCategoriesByPassengerId(passenger.getId())
                .stream()
                .map(categoryDao::findById)
                .map(Optional::orElseThrow)
                .map(x -> categoryMapper.mapToModel(x))
                .toList();
    }


    private List<Passenger> fetchAll() {
        List<Passenger> newPassengers = new ArrayList<>();
        List<Passenger> passengers = dao.findAll().stream().map(mapper::mapToModel).toList();
        for(Passenger passenger: passengers) {
            passenger.setCategories(findCategoriesForPassenger(passenger));
            newPassengers.add(passenger);
        }
        return newPassengers;
    }
}

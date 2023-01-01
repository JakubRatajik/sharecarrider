package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.PassengerCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.PassengerCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class PassengerCategoryRepository extends AbstractRepository<PassengerCategory> {

    private PassengerCategoryMapper mapper;
    private PassengerCategoryDao dao;

    public PassengerCategoryRepository(PassengerCategoryMapper mapper, PassengerCategoryDao dao) {
        super();
        this.mapper = mapper;
        this.dao = dao;
        refresh();
    }

    @Override
    public void refresh() {
        repositoryMembers = fetchAll();
    }

    @Override
    public void create(PassengerCategory category) {
        createAndGetID(category);
    }
    @Override
    public long createAndGetID(PassengerCategory category) {
        PassengerCategory newCategory = Stream.of(category)
                .map(mapper::mapToEntity)
                .map(dao::create)
                .map(mapper::mapToModel)
                .findFirst().orElse(null);
        repositoryMembers.add(newCategory);
        return newCategory.getId();
    }

    @Override
    public void update(PassengerCategory category) {
        int index = repositoryMembers.indexOf(category);
        Stream.of(category)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel)
                .forEach(d -> repositoryMembers.set(index, d));
    }

    @Override
    public void deleteByIndex(int index) {
        PassengerCategory passengerCategory = repositoryMembers.get(index);
        dao.deleteById(passengerCategory.getId());
        repositoryMembers.remove(index);
    }


    private List<PassengerCategory> fetchAll() {
        return dao.findAll().stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}

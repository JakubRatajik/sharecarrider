package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;
import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.RideCategoryDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.RideCategoryMapper;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class RideCategoryRepository extends AbstractRepository<RideCategory> {

    private RideCategoryMapper mapper;
    private RideCategoryDao dao;
    public RideCategoryRepository(RideCategoryMapper mapper, RideCategoryDao dao) {
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
    public void create(RideCategory category) {
        this.introduceEntity(category);
    }

    @Override
    public long introduceEntity(RideCategory category) {
        RideCategory newCategory = Stream.of(category)
                .map(mapper::mapToEntity)
                .map(dao::create)
                .map(mapper::mapToModel)
                .findFirst().orElse(null);
        repositoryMembers.add(newCategory);
        return newCategory.getId();
    }

    @Override
    public void update(RideCategory category) {
        int index = repositoryMembers.indexOf(category);
        Stream.of(category)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel)
                .forEach(d -> repositoryMembers.set(index, d));
    }

    @Override
    public void deleteByIndex(int index) {
        RideCategory rideCategory = repositoryMembers.get(index);
        dao.deleteById(rideCategory.getId());
        repositoryMembers.remove(index);
    }


    private List<RideCategory> fetchAll() {
        return dao.findAll().stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void deleteAll() {
        super.deleteAll(TableCategory.RIDE_CATEGORY);
    }
}

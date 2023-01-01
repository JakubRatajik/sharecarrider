package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.FuelDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.FuelMapper;
import cz.muni.fi.pv168.seminar01.beta.model.Fuel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class FuelRepository extends AbstractRepository<Fuel> {

    private final FuelMapper mapper;
    private final FuelDao dao;

    public FuelRepository(FuelMapper mapper, FuelDao dao) {
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
    public void update(Fuel fuel) {
        int index = repositoryMembers.indexOf(fuel);
        Stream.of(fuel)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel)
                .forEach(d -> repositoryMembers.set(index, d));
    }

    @Override
    public void deleteByIndex(int index) {
        Fuel fuel = repositoryMembers.get(index);
        dao.deleteByFuelType(fuel.getFuelType().name());
        repositoryMembers.remove(index);
    }


    private List<Fuel> fetchAll() {
        return dao.findAll().stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

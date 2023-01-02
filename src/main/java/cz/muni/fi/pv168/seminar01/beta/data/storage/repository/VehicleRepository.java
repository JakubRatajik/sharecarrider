package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.dao.VehicleDao;
import cz.muni.fi.pv168.seminar01.beta.data.storage.mapper.VehicleMapper;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jan Macecek
 */
public class VehicleRepository extends AbstractRepository<Vehicle> {

    private VehicleMapper mapper;
    private VehicleDao dao;

    public VehicleRepository(VehicleMapper mapper, VehicleDao dao) {
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
    public void create(Vehicle vehicle) {
        Stream.of(vehicle)
                .map(mapper::mapToEntity)
                .map(dao::create)
                .map(mapper::mapToModel)
                .forEach(e ->repositoryMembers.add(e));
    }

    @Override
    public void update(Vehicle vehicle) {
        int index = repositoryMembers.indexOf(vehicle);
        Stream.of(vehicle)
                .map(mapper::mapToEntity)
                .map(dao::update)
                .map(mapper::mapToModel)
                .forEach(d -> repositoryMembers.set(index, d));
    }

    @Override
    public void deleteByIndex(int index) {
        Vehicle vehicle = repositoryMembers.get(index);
        dao.deleteById(vehicle.getId());
        repositoryMembers.remove(index);
    }


    private List<Vehicle> fetchAll() {
        return dao.findAll().stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}


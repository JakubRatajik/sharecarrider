package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class VehicleRepository implements Repository<Vehicle> {

    // --TODO Add Mapper, Dao as attributes
    private List<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return vehicles.size();
    }

    @Override
    public Optional<Vehicle> findById(long id) {
        return vehicles.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Vehicle> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(vehicles.get(index));
        return Optional.empty();
    }

    @Override
    public List<Vehicle> findAll() {
        return Collections.unmodifiableList(vehicles);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

    @Override
    public void create(Vehicle newEntity) {
        //--TODO db functionality add
        vehicles.add(newEntity);
    }

    @Override
    public void update(Vehicle entity) {
        //--TODO db functionality add
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> vehicles.remove(x));
    }

    @Override
    public int findIndexByEntity(Vehicle entity) {
        if (vehicles.contains(entity)) {
            return vehicles.indexOf(entity);
        }
        return -1;
    }
}

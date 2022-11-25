package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class FuelRepository implements Repository<Fuel>{
    // --TODO Add Mapper, Dao as attributes

    private List<Fuel> fuels = new ArrayList<>();

    public FuelRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return fuels.size();
    }

    @Override
    public Optional<Fuel> findById(long id) {
        return fuels.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Fuel> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(fuels.get(index));
        return Optional.empty();
    }

    @Override
    public List<Fuel> findAll() {
        return Collections.unmodifiableList(fuels);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

    @Override
    public void create(Fuel newEntity) {
        //--TODO db functionality add
        fuels.add(newEntity);
    }

    @Override
    public void update(Fuel entity) {
        //--TODO db functionality add
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> fuels.remove(x));
    }

    @Override
    public int findIndexByEntity(Fuel entity) {
        if (fuels.contains(entity)) {
            return fuels.indexOf(entity);
        }
        return -1;
    }
}

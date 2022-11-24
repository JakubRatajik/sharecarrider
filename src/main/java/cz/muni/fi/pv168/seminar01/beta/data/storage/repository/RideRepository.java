package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class RideRepository implements Repository<Ride>{

    // --TODO Add Mapper, Dao as attributes
    private List<Ride> rides = new ArrayList<>();

    public RideRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return rides.size();
    }

    @Override
    public Optional<Ride> findById(long id) {
        return rides.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Ride> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(rides.get(index));
        return Optional.empty();
    }

    @Override
    public List<Ride> findAll() {
        return Collections.unmodifiableList(rides);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

    @Override
    public void create(Ride newEntity) {
        //--TODO db functionality add
        rides.add(newEntity);
    }

    @Override
    public void update(Ride entity) {
        //--TODO db functionality add
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> rides.remove(x));
    }
}

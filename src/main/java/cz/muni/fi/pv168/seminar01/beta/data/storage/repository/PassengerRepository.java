package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class PassengerRepository implements Repository<Passenger>{

    private List<Passenger> passengers = new ArrayList<>();

    public PassengerRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return passengers.size();
    }

    @Override
    public Optional<Passenger> findById(long id) {
        return passengers.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Passenger> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(passengers.get(index));
        return Optional.empty();
    }

    @Override
    public List<Passenger> findAll() {
        return Collections.unmodifiableList(passengers);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void create(Passenger newEntity) {
        //--TODO db functionality add
        passengers.add(newEntity);
    }

    @Override
    public void update(Passenger entity) {

    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> passengers.remove(x));
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class PassengerCategoryRepository implements Repository<PassengerCategory> {

    private List<PassengerCategory> passengerCategories = new ArrayList<>();
    // --TODO Add Mapper, Dao as attributes
    public PassengerCategoryRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return passengerCategories.size();
    }

    @Override
    public Optional<PassengerCategory> findById(long id) {
        return passengerCategories.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<PassengerCategory> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(passengerCategories.get(index));
        return Optional.empty();
    }

    @Override
    public List<PassengerCategory> findAll() {
        return Collections.unmodifiableList(passengerCategories);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

    @Override
    public void create(PassengerCategory newEntity) {
        //--TODO db functionality add
        passengerCategories.add(newEntity);
    }

    @Override
    public void update(PassengerCategory entity) {
        //--TODO db functionality add
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> passengerCategories.remove(x));
    }
}

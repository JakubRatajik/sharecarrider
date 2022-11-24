package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public class RideCategoriesRepository implements Repository<RideCategory> {
    // --TODO Add Mapper, Dao as attributes

    private List<RideCategory> rideCategories = new ArrayList<>();

    public RideCategoriesRepository() {

        this.refresh();
    }
    @Override
    public int getSize() {
        return rideCategories.size();
    }

    @Override
    public Optional<RideCategory> findById(long id) {
        return rideCategories.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<RideCategory> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(rideCategories.get(index));
        return Optional.empty();
    }

    @Override
    public List<RideCategory> findAll() {
        return Collections.unmodifiableList(rideCategories);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

@Override
    public void create(RideCategory newEntity) {
        //--TODO db functionality add
        rideCategories.add(newEntity);
    }

    @Override
    public void update(RideCategory entity) {
        //--TODO db functionality add
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> rideCategories.remove(x));
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import java.util.List;
import java.util.Optional;

/**
 * Represents a repository for any model
 *
 * @param <M> the type of the model
 */
public interface Repository<M> {

    int getSize();

    Optional<M> findById(long id);

    Optional<M> findByIndex(int index);

    List<M> findAll();

    void refresh();

    void create(M newEntity);

    /**
     * This method should introduce new entity to application world.
     * That means to add given entity to repository, add new row to
     * its corresponding table model as well as return ID of given entity.
     * @param newEntity to be added to repository, table model and gain ID
     * @return ID of new introduced entity
     */
    long introduceEntity(M newEntity);

    void update(M entity);

    void deleteByIndex(int index);

    int findIndexByEntity(M entity);

    void deleteAll();
}

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

    long createAndGetID(M newEntity);

    void update(M entity);

    void deleteByIndex(int index);

    int findIndexByEntity(M entity);

}

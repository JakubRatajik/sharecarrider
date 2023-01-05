package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.model.HasID;
import cz.muni.fi.pv168.seminar01.beta.ui.model.ShareCarRiderTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.CommonElementSupplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Jan Macecek
 */
public abstract class AbstractRepository<T extends HasID> implements Repository<T>{
    // --TODO Add Mapper, Dao as attributes

    AbstractRepository() {
        //this.refresh();
    }

    protected List<T> repositoryMembers = new ArrayList<>();

    @Override
    public int getSize() {
        return repositoryMembers.size();
    }

    @Override
    public Optional<T> findById(long id) {
        return repositoryMembers.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<T> findByIndex(int index) {
        if (index < getSize())
            return Optional.of(repositoryMembers.get(index));
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return Collections.unmodifiableList(repositoryMembers);
    }

    @Override
    public void refresh() {
        //--TODO db functionality add
    }

    @Override
    public void create(T newEntity) {
        //--TODO db functionality add
        repositoryMembers.add(newEntity);
    }

    @Override
    public abstract long introduceEntity(T newEntity);

    @Override
    public void update(T entity) {
        //--TODO db functionality add
        int index = findIndexByEntity(findById(entity.getId()).orElse(null));
        repositoryMembers.add(index, entity);
        repositoryMembers.remove(index);
    }

    @Override
    public void deleteByIndex(int index) {
        //--TODO connect this to DAO
        findByIndex(index).ifPresent(x -> repositoryMembers.remove(x));
    }

    @Override
    public int findIndexByEntity(T entity) {
        if (repositoryMembers.contains(entity)) {
            return repositoryMembers.indexOf(entity);
        }
        return -1;
    }

    @Override
    public void deleteAll() {
        repositoryMembers.clear();
    }

    public void deleteAll(TableCategory category){
        deleteAll();
        ShareCarRiderTableModel<?> tableModel = CommonElementSupplier.getTableModel(category);
        tableModel.fireTableRowsDeleted(0, tableModel.getRowCount() - 1);
    }
}

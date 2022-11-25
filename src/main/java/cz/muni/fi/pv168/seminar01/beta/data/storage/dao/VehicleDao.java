package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.VehicleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public class VehicleDao implements DataAccessObject<VehicleEntity> {

    private final Supplier<ConnectionHandler> connections;

    public VehicleDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public VehicleEntity create(VehicleEntity entity) {
        String sql = "INSERT INTO Vehicle (licensePlate, brand, type, capacity, consumption, fuelType) VALUES (?, ?, ?, ?, ?, ?);";

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.licensePlate());
            statement.setString(2, entity.brand());
            statement.setString(3, entity.type());
            statement.setInt(4, entity.capacity());
            statement.setDouble(5, entity.consumption());
            statement.setString(6, entity.fuelType());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long departmentId;

                if (keyResultSet.next()) {
                    departmentId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(departmentId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }

    }

    @Override
    public Collection<VehicleEntity> findAll() {
        return null;
    }

    @Override
    public Optional<VehicleEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public VehicleEntity update(VehicleEntity entity) {
        return null;
    }

    @Override
    public void deleteById(long entityId) {

    }
}

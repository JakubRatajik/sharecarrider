package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.VehicleEntity;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class VehicleDao implements DataAccessObject<VehicleEntity> {

    private final Supplier<ConnectionHandler> connections;

    public VehicleDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public VehicleEntity create(VehicleEntity entity) {
        String sql = """
                   INSERT INTO "Vehicle" ("licensePlate", "brand", "type", "capacity", "consumption", "fuelType") VALUES (?, ?, ?, ?, ?, ?);
                   """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.licensePlate());
            statement.setString(2, entity.brand());
            statement.setString(3, entity.type());
            statement.setInt(4, entity.capacity());
            statement.setFloat(5, entity.consumption());
            statement.setString(6, entity.fuelType().name().toLowerCase());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long vehicleId;

                if (keyResultSet.next()) {
                    vehicleId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(vehicleId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }

    }

    @Override
    public Collection<VehicleEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "id",
                       "licensePlate",
                       "brand",
                       "type",
                       "capacity",
                       "consumption",
                       "fuelType"
                    FROM "Vehicle"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<VehicleEntity> vehicles = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var vehicle = vehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
            }

            return vehicles;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all vehicles", ex);
        }
    }

    @Override
    public Optional<VehicleEntity> findById(long id) {
        var sql = """
                SELECT "id",
                       "licensePlate",
                       "brand",
                       "type",
                       "capacity",
                       "consumption",
                       "fuelType"
                    FROM "Vehicle"
                    WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(vehicleFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for id {}", id);
                // vehicle not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load vehicle by id", ex);
        }
    }

    @Override
    public VehicleEntity update(VehicleEntity entity) {
        Objects.requireNonNull(entity.id(), "Entity id cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "Vehicle"
                SET
                    "licensePlate" = ? ,
                    "brand" = ?,
                    "type" = ?,
                    "capacity" = ?,
                    "consumption" = ?,
                    "fuelType" = ?
                WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setString(1, entity.licensePlate());
            statement.setString(2, entity.brand());
            statement.setString(3, entity.type());
            statement.setInt(4, entity.capacity());
            statement.setDouble(5, entity.consumption());
            statement.setString(6, entity.fuelType().name().toLowerCase());
            statement.setLong(7, entity.id());

            if (statement.executeUpdate() == 0) {
                throw new DataStorageException("Failed to update non-existing vehicles: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update vehicle with id: " + entity.id(), ex);
        }

        return findById(entity.id()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        Logger.debug("Deleting entity with id {}", entityId);
        var sql = """
                DELETE FROM "Vehicle" WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, entityId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Vehicle not found: %d".formatted(entityId));
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 vehicle (rows=%d) has been deleted: %d"
                        .formatted(rowsUpdated, entityId));
            }
            Logger.debug("Successfully deleted entity with id {}", entityId);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete vehicle: %d".formatted(entityId), ex);
        }
    }

    private static VehicleEntity vehicleFromResultSet(ResultSet resultSet) throws SQLException {
        return new VehicleEntity(
                resultSet.getLong("id"),
                resultSet.getString("licensePlate"),
                resultSet.getString("brand"),
                resultSet.getString("type"),
                resultSet.getInt("capacity"),
                resultSet.getFloat("consumption"),
                FuelType.valueOf(resultSet.getString("fuelType").toUpperCase())
        );
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.FuelEntity;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class FuelDao implements DataAccessObject<FuelEntity> {

    private final Supplier<ConnectionHandler> connections;

    public FuelDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public FuelEntity create(FuelEntity entity) {
        throw new DataStorageException("Create new fuel type is banned operation.");
        // TODO - should not be possible
        /*
        String sql = """
                   INSERT INTO "Fuel" ("fuelType", "price") VALUES (?, ?);
                   """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.fuelType());
            statement.setBigDecimal(2, entity.price());
            statement.executeUpdate();

            return findByFuelType(entity.fuelType()).orElseThrow();

        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }
         */
    }

    @Override
    public Collection<FuelEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "fuelType",
                       "price"
                    FROM "Fuel"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<FuelEntity> fuels = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var fuel = fuelFromResultSet(resultSet);
                    fuels.add(fuel);
                }
            }

            return fuels;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all fuels", ex);
        }
    }

    @Override
    public Optional<FuelEntity> findById(long id) {
        throw new DataStorageException("FuelType does not have ID. Use findByFuelType method instead.");
    }

    @Override
    public FuelEntity update(FuelEntity entity) {
        Objects.requireNonNull(entity.fuelType(), "Fuel type cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "Fuel"
                SET "price" = ?
                WHERE "fuelType" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setBigDecimal(1, entity.price());
            statement.setString(2, entity.fuelType());

            if (statement.executeUpdate() == 0) {
                throw new DataStorageException("Failed to update non-existing fuels: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update fuel: " + entity.fuelType(), ex);
        }

        return findByFuelType(entity.fuelType()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        throw new DataStorageException("FuelType does not have ID. Use deleteByFuelType method instead.");
    }

    public Optional<FuelEntity> findByFuelType(String fuelType) {
        var sql = """
                SELECT "fuelType",
                       "price"
                    FROM "Fuel"
                    WHERE "fuelType" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setString(1, fuelType);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(fuelFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for fuel type {}", fuelType);
                // fuel not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load fuel by fuel type", ex);
        }
    }

    public void deleteByFuelType(String fuelType) {
        Logger.debug("Deleting entity with fuel type {}", fuelType);
        var sql = """
                DELETE FROM "Fuel" WHERE "fuelType" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setString(1, fuelType);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Fuel not found: " + fuelType);
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 fuel (rows=%d) has been deleted: "
                        .formatted(rowsUpdated) + fuelType);
            }
            Logger.debug("Successfully deleted entity with fuel type {}", fuelType);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete vehicle: " + fuelType, ex);
        }
    }

    private static FuelEntity fuelFromResultSet(ResultSet resultSet) throws SQLException {
        return new FuelEntity(
                resultSet.getString("fuelType"),
                resultSet.getBigDecimal("price")
        );
    }
}

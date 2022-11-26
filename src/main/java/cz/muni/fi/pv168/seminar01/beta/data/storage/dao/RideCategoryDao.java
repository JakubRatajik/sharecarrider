package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideCategoryEntity;
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

public class RideCategoryDao implements DataAccessObject<RideCategoryEntity> {

    private final Supplier<ConnectionHandler> connections;

    public RideCategoryDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public RideCategoryEntity create(RideCategoryEntity entity) {
        String sql = """
                   INSERT INTO "RideCategory" ("name") VALUES (?);
                   """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.name());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long rideCategoryId;

                if (keyResultSet.next()) {
                    rideCategoryId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(rideCategoryId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }

    }

    @Override
    public Collection<RideCategoryEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "id",
                       "name"
                    FROM "RideCategory"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<RideCategoryEntity> rideCategories = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var rideCategory = rideCategoryFromResultSet(resultSet);
                    rideCategories.add(rideCategory);
                }
            }

            return rideCategories;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all rideCategories", ex);
        }
    }

    @Override
    public Optional<RideCategoryEntity> findById(long id) {
        var sql = """
                SELECT "id",
                       "name"
                    FROM "RideCategory"
                    WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(rideCategoryFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for id {}", id);
                // rideCategory not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load rideCategories by id", ex);
        }
    }

    @Override
    public RideCategoryEntity update(RideCategoryEntity entity) {
        Objects.requireNonNull(entity.id(), "Entity id cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "RideCategory"
                SET
                    "name" = ?
                WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setString(1, entity.name());
            statement.setLong(2, entity.id());

            if (statement.executeUpdate() == 0) {
                throw new DataStorageException("Failed to update non-existing rideCategories: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update rideCategory with id: " + entity.id(), ex);
        }

        return findById(entity.id()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        Logger.debug("Deleting entity with id {}", entityId);
        var sql = """
                DELETE FROM "RideCategory" WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, entityId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("RideCategory not found: %d".formatted(entityId));
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 rideCategory (rows=%d) has been deleted: %d"
                        .formatted(rowsUpdated, entityId));
            }
            Logger.debug("Successfully deleted entity with id {}", entityId);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete rideCategory: %d".formatted(entityId), ex);
        }
    }

    private static RideCategoryEntity rideCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return new RideCategoryEntity(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }
}

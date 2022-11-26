package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoryEntity;
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

public class PassengerCategoryDao implements DataAccessObject<PassengerCategoryEntity> {

    private final Supplier<ConnectionHandler> connections;

    public PassengerCategoryDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public PassengerCategoryEntity create(PassengerCategoryEntity entity) {
        String sql = """
                   INSERT INTO "PassengerCategory" ("name") VALUES (?);
                   """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.name());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long passengerCategoryId;

                if (keyResultSet.next()) {
                    passengerCategoryId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(passengerCategoryId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }

    }

    @Override
    public Collection<PassengerCategoryEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "id",
                       "name"
                    FROM "PassengerCategory"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<PassengerCategoryEntity> passengerCategories = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var passengerCategory = passengerCategoryFromResultSet(resultSet);
                    passengerCategories.add(passengerCategory);
                }
            }

            return passengerCategories;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all passengerCategories", ex);
        }
    }

    @Override
    public Optional<PassengerCategoryEntity> findById(long id) {
        var sql = """
                SELECT "id",
                       "name"
                    FROM "PassengerCategory"
                    WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(passengerCategoryFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for id {}", id);
                // passengerCategory not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load passengerCategories by id", ex);
        }
    }

    @Override
    public PassengerCategoryEntity update(PassengerCategoryEntity entity) {
        Objects.requireNonNull(entity.id(), "Entity id cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "PassengerCategory"
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
                throw new DataStorageException("Failed to update non-existing passengerCategories: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update passengerCategory with id: " + entity.id(), ex);
        }

        return findById(entity.id()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        Logger.debug("Deleting entity with id {}", entityId);
        var sql = """
                DELETE FROM "PassengerCategory" WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, entityId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("PassengerCategory not found: %d".formatted(entityId));
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 passengerCategory (rows=%d) has been deleted: %d"
                        .formatted(rowsUpdated, entityId));
            }
            Logger.debug("Successfully deleted entity with id {}", entityId);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete passengerCategory: %d".formatted(entityId), ex);
        }
    }

    private static PassengerCategoryEntity passengerCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return new PassengerCategoryEntity(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }
}

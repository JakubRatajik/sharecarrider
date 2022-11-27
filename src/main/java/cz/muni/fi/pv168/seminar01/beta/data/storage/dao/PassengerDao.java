package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoriesEntity;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerEntity;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
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

public class PassengerDao implements DataAccessObject<PassengerEntity> {

    private final Supplier<ConnectionHandler> connections;

    public PassengerDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public PassengerEntity create(PassengerEntity entity) {
        String sql = """
                INSERT INTO "Passenger" ("firstName", "lastName", "phoneNumber") VALUES (?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.firstName());
            statement.setString(2, entity.lastName());
            statement.setString(3, entity.phoneNumber());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long passengerId;

                if (keyResultSet.next()) {
                    passengerId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(passengerId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }
    }

    @Override
    public Collection<PassengerEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "id",
                       "firstName",
                       "lastName",
                       "phoneNumber"
                    FROM "Passenger"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<PassengerEntity> passengers = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var passenger = passengerFromResultSet(resultSet);
                    passengers.add(passenger);
                }
            }

            return passengers;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all passengers", ex);
        }
    }

    @Override
    public Optional<PassengerEntity> findById(long id) {
        var sql = """
                SELECT "id",
                       "firstName",
                       "lastName",
                       "phoneNumber"
                    FROM "Passenger"
                    WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(passengerFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for id {}", id);
                // passenger not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load passenger by id", ex);
        }
    }

    @Override
    public PassengerEntity update(PassengerEntity entity) {
        Objects.requireNonNull(entity.id(), "Entity id cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "Passenger"
                SET
                    "firstName" = ?,
                    "lastName" = ?,
                    "phoneNumber" = ?
                WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setString(1, entity.firstName());
            statement.setString(2, entity.lastName());
            statement.setString(3, entity.phoneNumber());
            statement.setLong(4, entity.id());

            if (statement.executeUpdate() == 0) {
                throw new DataStorageException("Failed to update non-existing passengers: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update passenger with id: " + entity.id(), ex);
        }

        return findById(entity.id()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        Logger.debug("Deleting entity with id {}", entityId);
        var sql = """
                DELETE FROM "Passenger" WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, entityId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Passenger not found: %d".formatted(entityId));
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 passenger (rows=%d) has been deleted: %d"
                        .formatted(rowsUpdated, entityId));
            }
            Logger.debug("Successfully deleted entity with id {}", entityId);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete passenger: %d".formatted(entityId), ex);
        }
    }

    public List<Long> findCategoriesByPassengerId(long id) {
        Logger.debug("Fetching passenger categories");
        var sql = """
                SELECT "passengerCategoryId"
                    FROM "PassengerCategories"
                    WHERE "passengerId" = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            statement.setLong(1, id);
            List<Long> passengerCategoryIds = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var passengerCategoryId = resultSet.getLong("passengerCategoryId");
                    passengerCategoryIds.add(passengerCategoryId);
                }
            }

            return passengerCategoryIds;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all categories for one passenger specified by ID", ex);
        }
    }


    public void deletePassengerCategories(long id) {
        Logger.debug("Deleting Passenger-category join");
        var sql = """
                DELETE FROM "PassengerCategories" WHERE "passengerId" = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            statement.setLong(1, id);
            int rowsUpdated = statement.executeUpdate();
            Logger.debug("Successfully deleted entity with id {}", id);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete passenger: %d".formatted(id), ex);
        }
    }

    private static PassengerEntity passengerFromResultSet(ResultSet resultSet) throws SQLException {
        return new PassengerEntity(
                resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phoneNumber")
        );
    }


    public void createJoins(long passengerId, List<PassengerCategory> categories) {
        String sql = """
                INSERT INTO "PassengerCategories" ("passengerId", "passengerCategoryId") VALUES (?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            for (PassengerCategory category: categories) {
                statement.setLong(1, passengerId);
                statement.setLong(2, category.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + categories.size(), ex);
        }
    }
}

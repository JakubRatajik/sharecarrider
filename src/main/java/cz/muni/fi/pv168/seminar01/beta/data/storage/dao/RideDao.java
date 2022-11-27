package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.ConnectionHandler;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideEntity;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import org.tinylog.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class RideDao implements DataAccessObject<RideEntity> {

    private final Supplier<ConnectionHandler> connections;

    public RideDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public RideEntity create(RideEntity entity) {
        String sql = """
                INSERT INTO "Ride" ("date",
                                    "departure",
                                    "arrival",
                                    "startDest",
                                    "endDest",
                                    "distance",
                                    "vehicle",
                                    "repetition") VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setDate(1, Date.valueOf(entity.date()));
            statement.setTime(2, Time.valueOf(entity.departure()));
            statement.setTime(3, Time.valueOf(entity.arrival()));
            statement.setString(4, entity.startDest());
            statement.setString(5, entity.endDest());
            statement.setInt(6, entity.distance());
            statement.setLong(7, entity.vehicleId());
            statement.setString(8, entity.repetition().name().toLowerCase());
            statement.executeUpdate();

            try (ResultSet keyResultSet = statement.getGeneratedKeys()) {
                long rideId;

                if (keyResultSet.next()) {
                    rideId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + entity);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + entity);
                }

                return findById(rideId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + entity, ex);
        }
    }

    @Override
    public Collection<RideEntity> findAll() {
        Logger.debug("Fetching all");
        var sql = """
                SELECT "id",
                       "date",
                       "departure",
                       "arrival",
                       "startDest",
                       "endDest",
                       "distance",
                       "vehicle",
                       "repetition"
                    FROM "Ride"
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            List<RideEntity> rides = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var ride = rideFromResultSet(resultSet);
                    rides.add(ride);
                }
            }

            return rides;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all rides", ex);
        }
    }

    @Override
    public Optional<RideEntity> findById(long id) {
        var sql = """
                SELECT "id",
                       "date",
                       "departure",
                       "arrival",
                       "startDest",
                       "endDest",
                       "distance",
                       "vehicle",
                       "repetition"
                    FROM "Ride"
                    WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(rideFromResultSet(resultSet));
            } else {
                Logger.debug("No entity for id {}", id);
                // ride not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load ride by id", ex);
        }
    }

    @Override
    public RideEntity update(RideEntity entity) {
        Objects.requireNonNull(entity.id(), "Entity id cannot be null");
        Logger.debug("Updating entity {}", entity);

        final var sql = """
                UPDATE "Ride"
                SET
                    "date" = ?,
                    "departure" = ?,
                    "arrival" = ?,
                    "startDest" = ?,
                    "endDest" = ?,
                    "distance" = ?,
                    "vehicle" = ?,
                    "repetition" = ?
                WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setDate(1, Date.valueOf(entity.date()));
            statement.setTime(2, Time.valueOf(entity.departure()));
            statement.setTime(3, Time.valueOf(entity.arrival()));
            statement.setString(4, entity.startDest());
            statement.setString(5, entity.endDest());
            statement.setInt(6, entity.distance());
            statement.setLong(7, entity.vehicleId());
            statement.setString(8, entity.repetition().name().toLowerCase());
            statement.setLong(9, entity.id());

            if (statement.executeUpdate() == 0) {
                throw new DataStorageException("Failed to update non-existing rides: " + entity);
            }
            Logger.debug("Update successful for {}", entity);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update ride with id: " + entity.id(), ex);
        }

        return findById(entity.id()).orElseThrow();
    }

    @Override
    public void deleteById(long entityId) {
        Logger.debug("Deleting entity with id {}", entityId);
        var sql = """
                DELETE FROM "Ride" WHERE "id" = ?
                """;

        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)
        ) {
            statement.setLong(1, entityId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Ride not found: %d".formatted(entityId));
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 ride (rows=%d) has been deleted: %d"
                        .formatted(rowsUpdated, entityId));
            }
            Logger.debug("Successfully deleted entity with id {}", entityId);
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete ride: %d".formatted(entityId), ex);
        }
    }

    public Collection<Long> findCategoriesByRideId(long id) {
        Logger.debug("Fetching ride categories");
        var sql = """
                SELECT "rideCategoryId"
                    FROM "RideCategories"
                    WHERE "rideId" = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            statement.setLong(1, id);
            List<Long> rideCategoryIds = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var rideCategoryId = resultSet.getLong("rideCategoryId");
                    rideCategoryIds.add(rideCategoryId);
                }
            }

            return rideCategoryIds;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all categories for one ride specified by ID", ex);
        }
    }

    public Collection<Long> findPassengersByRideId(long id) {
        Logger.debug("Fetching ride passengers");
        var sql = """
                SELECT "passenger"
                    FROM "RidePassengers"
                    WHERE "rideId" = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql)) {

            statement.setLong(1, id);
            List<Long> passengerIds = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var passengerId = resultSet.getLong("passenger");
                    passengerIds.add(passengerId);
                }
            }

            return passengerIds;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all passengers for one ride specified by ID", ex);
        }
    }

    private static RideEntity rideFromResultSet(ResultSet resultSet) throws SQLException {
        return new RideEntity(
                resultSet.getLong("id"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getTime("departure").toLocalTime(),
                resultSet.getTime("arrival").toLocalTime(),
                resultSet.getString("startDest"),
                resultSet.getString("endDest"),
                resultSet.getInt("distance"),
                resultSet.getLong("vehicle"),
                Repetition.valueOf(resultSet.getString("repetition").toUpperCase())
        );
    }
}

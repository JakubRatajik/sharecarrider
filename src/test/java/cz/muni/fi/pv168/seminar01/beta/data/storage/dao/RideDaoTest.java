package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideEntity;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class RideDaoTest {
    private DatabaseManager manager;

    private RideDao rideDao;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
        this.rideDao = new RideDao(manager::getConnectionHandler);
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    @Test
    void listAllRides() {
        var allRides = rideDao.findAll();
        assertThat(allRides)
                .as("There should be 3 testing rides")
                .hasSize(3);
    }

    @Test
    void findExistingRide() {
        var firstRide = rideDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        var foundRide = rideDao.findById(firstRide.id());

        assertThat(foundRide)
                .isPresent()
                .contains(firstRide);
    }


    @Test
    void createNewRide() {
        RideEntity newRideParams = new RideEntity(LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno město", "Brno venkov", 10, 3, Repetition.MONTHLY, "");

        var createdRide = rideDao.
                create(newRideParams);

        assertThat(createdRide)
                .as("There should be a new ride created")
                .isNotNull();

        assertThat(createdRide.id())
                .isGreaterThan(1);

        assertThat(createdRide.startDest()).isEqualTo(newRideParams.startDest());
        assertThat(createdRide.endDest()).isEqualTo(newRideParams.endDest());
        assertThat(createdRide.distance()).isEqualTo(newRideParams.distance());
        assertThat(createdRide.vehicleId()).isEqualTo(newRideParams.vehicleId());
        assertThat(createdRide.repetition()).isEqualTo(newRideParams.repetition());
        assertThat(createdRide.description()).isEqualTo(newRideParams.description());

        var allRides = rideDao.findAll();

        assertThat(allRides)
                .as("The newly created ride has to be stored in the database")
                .contains(createdRide);
    }

    @Test
    void deleteVilhemoviceRide() {
        var allRides = rideDao.findAll();
        var vilhemovice = allRides.stream()
                .filter(d -> d.startDest().equals("Vilhemovice"))
                .findFirst();

        assertThat(vilhemovice)
                .isPresent();

        rideDao.deleteById(vilhemovice.map(RideEntity::id).orElseThrow());

        var allRidesAfterDelete = rideDao.findAll();

        var salesAfterDelete = allRidesAfterDelete.stream()
                .filter(d -> d.startDest().equals("Vilhemovice"))
                .findFirst();

        assertThat(salesAfterDelete)
                .isEmpty();
    }

    @Test
    void deleteVilhemoviceRideTwoTimes() {
        var allRides = rideDao.findAll();
        var vilhemovice = allRides.stream()
                .filter(d -> d.startDest().equals("Vilhemovice"))
                .findFirst()
                .orElseThrow();

        // delete first time
        rideDao.deleteById(vilhemovice.id());

        // delete second time should produce an exception

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> rideDao.deleteById(vilhemovice.id()))
                .withMessageContaining("not found");
    }

    @Test
    void deleteNotStoredRide() {
        RideEntity rideEntity = createRideEntity();

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> rideDao.deleteById(rideEntity.id()));
    }

    private static RideEntity createRideEntity() {

        return new RideEntity(
                -1008L,
                LocalDate.MIN,
                LocalTime.MIN,
                LocalTime.MAX,
                "Chánov",
                "Ústí",
                20,
                1,
                Repetition.MONTHLY,
                "ahoj"
        );
    }

}

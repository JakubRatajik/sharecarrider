package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class PassengerDaoTest {
    private DatabaseManager manager;

    private PassengerDao passengerDao;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
        this.passengerDao = new PassengerDao(manager::getConnectionHandler);
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    @Test
    void listAllPassengers() {
        var allPassengers = passengerDao.findAll();
        assertThat(allPassengers)
                .as("There should be 3 testing passengers")
                .hasSize(3);
    }

    @Test
    void findExistingPassenger() {
        var firstPassenger = passengerDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        var foundPassenger = passengerDao.findById(firstPassenger.id());

        assertThat(foundPassenger)
                .isPresent()
                .contains(firstPassenger);
    }

    @Test
    void findExistingPassengerById() {
        var firstPassenger = passengerDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        assertThat(passengerDao.findById(firstPassenger.id()))
                .isPresent()
                .contains(firstPassenger);

    }

    @Test
    void createNewPassenger() {
        PassengerEntity newPassengerParams = new PassengerEntity("Věra", "Čáslavská", "775676887");

        var createdPassenger = passengerDao.
                create(newPassengerParams);

        assertThat(createdPassenger)
                .as("There should be a new passenger created")
                .isNotNull();

        assertThat(createdPassenger.id())
                .isGreaterThan(1);

        assertThat(createdPassenger.firstName()).isEqualTo(newPassengerParams.firstName());
        assertThat(createdPassenger.lastName()).isEqualTo(newPassengerParams.lastName());
        assertThat(createdPassenger.phoneNumber()).isEqualTo(newPassengerParams.phoneNumber());

        var allPassengers = passengerDao.findAll();

        assertThat(allPassengers)
                .as("The newly created passenger has to be stored in the database")
                .contains(createdPassenger);
    }

    @Test
    void deletePeter() {
        var allPassengers = passengerDao.findAll();
        var peter = allPassengers.stream()
                .filter(d -> d.firstName().equals("Peter"))
                .findFirst();

        assertThat(peter)
                .isPresent();

        passengerDao.deleteById(peter.map(PassengerEntity::id).orElseThrow());

        var allPassengersAfterDelete = passengerDao.findAll();

        var salesAfterDelete = allPassengersAfterDelete.stream()
                .filter(d -> d.firstName().equals("Peter"))
                .findFirst();

        assertThat(salesAfterDelete)
                .isEmpty();
    }

    @Test
    void deletePeterTwoTimes() {
        var allPassengers = passengerDao.findAll();
        var peter = allPassengers.stream()
                .filter(d -> d.firstName().equals("Peter"))
                .findFirst()
                .orElseThrow();

        // delete first time
        passengerDao.deleteById(peter.id());

        // delete second time should produce an exception

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> passengerDao.deleteById(peter.id()))
                .withMessageContaining("not found");
    }

    @Test
    void deleteNotStoredPassenger() {
        PassengerEntity passengerEntity = createPassengerEntity();

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> passengerDao.deleteById(passengerEntity.id()));
    }

    private static PassengerEntity createPassengerEntity() {

        return new PassengerEntity(
                -1000L,
                "Sára",
                "Vodická",
                "666777888"
        );
    }

}

package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jakub Ratajik
 */
public class PassengerRepositoryTest {
    private DatabaseManager databaseManager;
    private Repository<Passenger> passengerRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        passengerRepository = dependencyProvider.getPassengerRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void createNewPassenger() {
        Passenger passengerToCreate = new Passenger("Artur", "Šímek", "+420123456789", new ArrayList<>());
        passengerRepository.create(passengerToCreate);

        Passenger storedPassenger = passengerRepository
                .findByIndex(passengerRepository.getSize() - 1) // get latest
                .orElseThrow();

        assertThat(storedPassenger.getFirstName()).isEqualTo(passengerToCreate.getFirstName());
        assertThat(storedPassenger.getLastName()).isEqualTo(passengerToCreate.getLastName());
        assertThat(storedPassenger.getPhoneNumber()).isEqualTo(passengerToCreate.getPhoneNumber());
        assertThat(storedPassenger.getCategoryNames()).isEqualTo(passengerToCreate.getCategoryNames());
        assertThat(storedPassenger.getId()).isNotNull();
    }

    @Test
    void listAllTestingPassengers() {
        List<Passenger> storedPassengers = passengerRepository
                .findAll();

        assertThat(storedPassengers).hasSize(3);
    }

    @Test
    void findPassengerByIndex() {
        Optional<Passenger> storedPassenger = passengerRepository
                .findByIndex(1);

        assertThat(storedPassenger).isPresent();
    }

    @Test
    void findPassengerByIndexShouldFailForIndexTooHigh() {
        Optional<Passenger> storedPassenger = passengerRepository
                .findByIndex(1000);

        assertThat(storedPassenger).isEmpty();
    }

    @Test
    void findPassengerById() {
        Optional<Passenger> storedPassenger = passengerRepository
                .findById(1);

        assertThat(storedPassenger).isPresent();
    }

    @Test
    void findPassengerByIndexShouldFailForIdTooHigh() {
        Optional<Passenger> storedPassenger = passengerRepository
                .findById(1000);

        assertThat(storedPassenger).isEmpty();
    }

    @Test
    void updatePassenger() {
        var peter = passengerRepository.findAll()
                .stream()
                .filter(e -> e.getFirstName().equals("Peter"))
                .findFirst()
                .orElseThrow();

        peter.setPhoneNumber("+421879456345");

        passengerRepository.update(peter);

        Passenger updatedPassenger = passengerRepository.findById(peter.getId()).orElseThrow();

        assertThat(updatedPassenger.getPhoneNumber()).isEqualTo("+421879456345");
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jakub Ratajik
 */
public class RideRepositoryTest {
    private DatabaseManager databaseManager;
    private Repository<Ride> rideRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        rideRepository = dependencyProvider.getRideRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void createNewRide() {
        Collection<Passenger> passengers = List.of(new Passenger(1, "Jana", "Sedmohorská", "7894561567", new ArrayList<>()));
        Vehicle vehicle = new Vehicle(1, "3C55448", "BMW", "X5", 5, 10.1, FuelType.GASOLINE);
        Ride rideToCreate = new Ride(4, LocalDate.of(2022, 12, 4), LocalTime.of(12, 24), LocalTime.of(12, 56), "Breclav", "Velke Zaluzie", 78, new ArrayList<>(), passengers, vehicle, Repetition.DAILY, "vyjizdka");
        rideRepository.create(rideToCreate);

        Ride storedRide = rideRepository
                .findByIndex(rideRepository.getSize() - 1) // get latest
                .orElseThrow();

        assertThat(storedRide.getDate()).isEqualTo(rideToCreate.getDate());
        assertThat(storedRide.getDeparture()).isEqualTo(rideToCreate.getDeparture());
        assertThat(storedRide.getArrival()).isEqualTo(rideToCreate.getArrival());
        assertThat(storedRide.getFrom()).isEqualTo(rideToCreate.getFrom());
        assertThat(storedRide.getTo()).isEqualTo(rideToCreate.getTo());
        assertThat(storedRide.getDistance()).isEqualTo(rideToCreate.getDistance());
        assertThat(storedRide.getCategoryNames()).isEqualTo(rideToCreate.getCategoryNames());
        assertThat(storedRide.getPassengers()).isEqualTo(rideToCreate.getPassengers());
        assertThat(storedRide.getVehicle()).isEqualTo(rideToCreate.getVehicle());
        assertThat(storedRide.getRepetition()).isEqualTo(rideToCreate.getRepetition());
        assertThat(storedRide.getDescription()).isEqualTo(rideToCreate.getDescription());
        assertThat(storedRide.getId()).isNotNull();
    }

    @Test
    void listAllTestingRides() {
        List<Ride> storedRides = rideRepository
                .findAll();

        assertThat(storedRides).hasSize(3);
    }

    @Test
    void findRideByIndex() {
        Optional<Ride> storedRide = rideRepository
                .findByIndex(1);

        assertThat(storedRide).isPresent();
    }

    @Test
    void findRideByIndexShouldFailForIndexTooHigh() {
        Optional<Ride> storedRide = rideRepository
                .findByIndex(1000);

        assertThat(storedRide).isEmpty();
    }

    @Test
    void findRideById() {
        Optional<Ride> storedRide = rideRepository
                .findById(1);

        assertThat(storedRide).isPresent();
    }

    @Test
    void findRideByIndexShouldFailForIdTooHigh() {
        Optional<Ride> storedRide = rideRepository
                .findById(1000);

        assertThat(storedRide).isEmpty();
    }

    @Test
    void updateRide() {
        var ride = rideRepository.findAll()
                .stream()
                .filter(e -> e.getTo().equals("Opava"))
                .findFirst()
                .orElseThrow();

        ride.setDistance(300);

        rideRepository.update(ride);

        Ride updatedRide = rideRepository.findById(ride.getId()).orElseThrow();

        assertThat(updatedRide.getDistance()).isEqualTo(300);
    }
}

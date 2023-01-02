package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


final class VehicleRepositoryTest {

    private DatabaseManager databaseManager;
    private Repository<Vehicle> vehicleRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        vehicleRepository = dependencyProvider.getVehicleRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void createNewVehicle() {
        Vehicle vehicleToCreate = new Vehicle("BL123IT", "Fiat", "Multipla", 7, 8.3, FuelType.GASOLINE);
        vehicleRepository.create(vehicleToCreate);

        Vehicle storedVehicle = vehicleRepository
                .findByIndex(vehicleRepository.getSize() - 1) // get latest
                .orElseThrow();

        assertThat(storedVehicle.getLicensePlate()).isEqualTo(vehicleToCreate.getLicensePlate());
        assertThat(storedVehicle.getBrand()).isEqualTo(vehicleToCreate.getBrand());
        assertThat(storedVehicle.getType()).isEqualTo(vehicleToCreate.getType());
        assertThat(storedVehicle.getCapacity()).isEqualTo(vehicleToCreate.getCapacity());
        assertThat(storedVehicle.getConsumption()).isEqualTo(vehicleToCreate.getConsumption());
        assertThat(storedVehicle.getFuelType()).isEqualTo(vehicleToCreate.getFuelType());
        assertThat(storedVehicle.getId()).isNotNull();
    }

    @Test
    void listAllTestingVehicles() {
        List<Vehicle> storedVehicles = vehicleRepository
                .findAll();

        assertThat(storedVehicles).hasSize(3);
    }

    @Test
    void findVehicleByIndex() {
        Optional<Vehicle> storedVehicle = vehicleRepository
                .findByIndex(1);

        assertThat(storedVehicle).isPresent();
    }

    @Test
    void findVehicleByIndexShouldFailForIndexTooHigh() {
        Optional<Vehicle> storedVehicle = vehicleRepository
                .findByIndex(1000);

        assertThat(storedVehicle).isEmpty();
    }

    @Test
    void findVehicleById() {
        Optional<Vehicle> storedVehicle = vehicleRepository
                .findById(1);

        assertThat(storedVehicle).isPresent();
    }

    @Test
    void findVehicleByIndexShouldFailForIdTooHigh() {
        Optional<Vehicle> storedVehicle = vehicleRepository
                .findById(1000);

        assertThat(storedVehicle).isEmpty();
    }

    @Test
    void updateVehicle() {
        var yetti = vehicleRepository.findAll()
                .stream()
                .filter(e -> e.getType().equals("Yetti"))
                .findFirst()
                .orElseThrow();

        yetti.setLicensePlate("ZA497VR");

        vehicleRepository.update(yetti);

        Vehicle updatedVehicle = vehicleRepository.findById(yetti.getId()).orElseThrow();

        assertThat(updatedVehicle.getLicensePlate()).isEqualTo("ZA497VR");
    }
}
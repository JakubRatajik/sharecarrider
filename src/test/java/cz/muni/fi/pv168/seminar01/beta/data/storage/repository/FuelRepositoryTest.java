package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jakub Ratajik
 */
public class FuelRepositoryTest {
    private DatabaseManager databaseManager;
    private Repository<Fuel> fuelRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        fuelRepository = dependencyProvider.getFuelRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void listAllTestingFuels() {
        List<Fuel> storedFuels = fuelRepository
                .findAll();

        assertThat(storedFuels).hasSize(5);
    }

    @Test
    void findFuelByIndex() {
        Optional<Fuel> storedFuel = fuelRepository
                .findByIndex(1);

        assertThat(storedFuel).isPresent();
    }

    @Test
    void findFuelByIndexShouldFailForIndexTooHigh() {
        Optional<Fuel> storedFuel = fuelRepository
                .findByIndex(1000);

        assertThat(storedFuel).isEmpty();
    }

    @Test
    void findFuelById() {
        Optional<Fuel> storedFuel = fuelRepository
                .findById(1);

        assertThat(storedFuel).isPresent();
    }

    @Test
    void findFuelByIndexShouldFailForIdTooHigh() {
        Optional<Fuel> storedFuel = fuelRepository
                .findById(1000);

        assertThat(storedFuel).isEmpty();
    }

    @Test
    void updateFuel() {
        var gasoline = fuelRepository.findAll()
                .stream()
                .filter(e -> e.getFuelType().equals(FuelType.GASOLINE))
                .findFirst()
                .orElseThrow();

        gasoline.setPrice(new BigDecimal("37"));

        fuelRepository.update(gasoline);

        Fuel updatedFuel = fuelRepository.findById(gasoline.getId()).orElseThrow();

        assertThat(updatedFuel.getPrice()).isEqualTo(new BigDecimal("37"));
    }
}

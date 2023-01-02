package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jakub Ratajik
 */
public class PassengerCategoryRepositoryTest {
    private DatabaseManager databaseManager;
    private Repository<PassengerCategory> passengerCategoryRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        passengerCategoryRepository = dependencyProvider.getPassengerCategoryRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void createNewPassengerCategory() {
        PassengerCategory passengerCategoryToCreate = new PassengerCategory(4, "nudni lide");
        passengerCategoryRepository.create(passengerCategoryToCreate);

        PassengerCategory storedPassengerCategory = passengerCategoryRepository
                .findByIndex(passengerCategoryRepository.getSize() - 1) // get latest
                .orElseThrow();

        assertThat(storedPassengerCategory.getName()).isEqualTo(passengerCategoryToCreate.getName());
        assertThat(storedPassengerCategory.getId()).isNotNull();
    }

    @Test
    void listAllTestingPassengerCategories() {
        List<PassengerCategory> storedPassengerCategories = passengerCategoryRepository
                .findAll();

        assertThat(storedPassengerCategories).hasSize(3);
    }

    @Test
    void findPassengerCategoryByIndex() {
        Optional<PassengerCategory> storedPassengerCategory = passengerCategoryRepository
                .findByIndex(1);

        assertThat(storedPassengerCategory).isPresent();
    }

    @Test
    void findPassengerCategoryByIndexShouldFailForIndexTooHigh() {
        Optional<PassengerCategory> storedPassengerCategory = passengerCategoryRepository
                .findByIndex(1000);

        assertThat(storedPassengerCategory).isEmpty();
    }

    @Test
    void findPassengerCategoryById() {
        Optional<PassengerCategory> storedPassengerCategory = passengerCategoryRepository
                .findById(1);

        assertThat(storedPassengerCategory).isPresent();
    }

    @Test
    void findPassengerCategoryByIndexShouldFailForIdTooHigh() {
        Optional<PassengerCategory> storedPassengerCategory = passengerCategoryRepository
                .findById(1000);

        assertThat(storedPassengerCategory).isEmpty();
    }

    @Test
    void updatePassengerCategory() {
        var family = passengerCategoryRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals("Rodina"))
                .findFirst()
                .orElseThrow();

        family.setName("Pribuzni");

        passengerCategoryRepository.update(family);

        PassengerCategory updatedPassengerCategory = passengerCategoryRepository.findById(family.getId()).orElseThrow();

        assertThat(updatedPassengerCategory.getName()).isEqualTo("Pribuzni");
    }
}

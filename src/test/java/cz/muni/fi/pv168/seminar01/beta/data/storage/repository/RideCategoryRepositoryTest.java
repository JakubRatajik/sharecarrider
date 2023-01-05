package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
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
public class RideCategoryRepositoryTest {
    private DatabaseManager databaseManager;
    private Repository<RideCategory> rideCategoryRepository;

    @BeforeEach
    void setUp() {
        databaseManager = DatabaseManager.createTestInstance();
        var dependencyProvider = new TestDependencyProvider(databaseManager);
        rideCategoryRepository = dependencyProvider.getRideCategoryRepository();
    }

    @AfterEach
    void tearDown() {
        databaseManager.destroySchema();
    }

    @Test
    void createNewRideCategory() {
        RideCategory rideCategoryToCreate = new RideCategory(4, "okruzni jizda");
        rideCategoryRepository.create(rideCategoryToCreate);

        RideCategory storedRideCategory = rideCategoryRepository
                .findByIndex(rideCategoryRepository.getSize() - 1) // get latest
                .orElseThrow();

        assertThat(storedRideCategory.getName()).isEqualTo(rideCategoryToCreate.getName());
        assertThat(storedRideCategory.getId()).isNotNull();
    }

    @Test
    void listAllTestingRideCategorys() {
        List<RideCategory> storedRideCategories = rideCategoryRepository
                .findAll();

        assertThat(storedRideCategories).hasSize(3);
    }

    @Test
    void findRideCategoryByIndex() {
        Optional<RideCategory> storedRideCategory = rideCategoryRepository
                .findByIndex(1);

        assertThat(storedRideCategory).isPresent();
    }

    @Test
    void findRideCategoryByIndexShouldFailForIndexTooHigh() {
        Optional<RideCategory> storedRideCategory = rideCategoryRepository
                .findByIndex(1000);

        assertThat(storedRideCategory).isEmpty();
    }

    @Test
    void findRideCategoryById() {
        Optional<RideCategory> storedRideCategory = rideCategoryRepository
                .findById(1);

        assertThat(storedRideCategory).isPresent();
    }

    @Test
    void findRideCategoryByIndexShouldFailForIdTooHigh() {
        Optional<RideCategory> storedRideCategory = rideCategoryRepository
                .findById(1000);

        assertThat(storedRideCategory).isEmpty();
    }

    @Test
    void updateRideCategory() {
        var secret = rideCategoryRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals("Tajné"))
                .findFirst()
                .orElseThrow();

        secret.setName("Cesty z mesta");

        rideCategoryRepository.update(secret);

        RideCategory updatedRideCategory = rideCategoryRepository.findById(secret.getId()).orElseThrow();

        assertThat(updatedRideCategory.getName()).isEqualTo("Cesty z mesta");
    }
}

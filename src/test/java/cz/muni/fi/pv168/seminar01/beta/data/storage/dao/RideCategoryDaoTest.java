package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.RideCategoryEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class RideCategoryDaoTest {
    private DatabaseManager manager;

    private RideCategoryDao rideCategoryDao;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
        this.rideCategoryDao = new RideCategoryDao(manager::getConnectionHandler);
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    @Test
    void listAllRideCategories() {
        var allRideCategories = rideCategoryDao.findAll();
        assertThat(allRideCategories)
                .as("There should be 3 testing rides categories")
                .hasSize(3);
    }

    @Test
    void findExistingRideCategory() {
        var firstRideCategory = rideCategoryDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        var foundRideCategory = rideCategoryDao.findById(firstRideCategory.id());

        assertThat(foundRideCategory)
                .isPresent()
                .contains(firstRideCategory);
    }


    @Test
    void createNewRideCategory() {
        RideCategoryEntity newRideCategoryParams = new RideCategoryEntity("Testing RideCategory");

        var createdRideCategory = rideCategoryDao.
                create(newRideCategoryParams);

        assertThat(createdRideCategory)
                .as("There should be a new rideCategory created")
                .isNotNull();

        assertThat(createdRideCategory.id())
                .isGreaterThan(1);

        assertThat(createdRideCategory.name()).isEqualTo(newRideCategoryParams.name());

        var allRideCategories = rideCategoryDao.findAll();

        assertThat(allRideCategories)
                .as("The newly created rideCategory has to be stored in the database")
                .contains(createdRideCategory);
    }


    @Test
    void deleteTajneRideCategory() {
        var allRideCategories = rideCategoryDao.findAll();
        var tajne = allRideCategories.stream()
                .filter(d -> d.name().equals("Tajné"))
                .findFirst();

        assertThat(tajne)
                .isPresent();

        rideCategoryDao.deleteById(tajne.map(RideCategoryEntity::id).orElseThrow());

        var allRideCategoriesAfterDelete = rideCategoryDao.findAll();

        var salesAfterDelete = allRideCategoriesAfterDelete.stream()
                .filter(d -> d.name().equals("Tajné"))
                .findFirst();

        assertThat(salesAfterDelete)
                .isEmpty();
    }

    @Test
    void deleteTajneRideCategoryTwoTimes() {
        var allRideCategories = rideCategoryDao.findAll();
        var tajne = allRideCategories.stream()
                .filter(d -> d.name().equals("Tajné"))
                .findFirst()
                .orElseThrow();

        // delete first time
        rideCategoryDao.deleteById(tajne.id());

        // delete second time should produce an exception

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> rideCategoryDao.deleteById(tajne.id()))
                .withMessageContaining("not found");
    }

    @Test
    void deleteNotStoredRideCategory() {
        RideCategoryEntity rideCategoryEntity = createRideCategoryEntity();

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> rideCategoryDao.deleteById(rideCategoryEntity.id()));
    }

    private static RideCategoryEntity createRideCategoryEntity() {

        return new RideCategoryEntity(
                -1000L,
                "pracovní"
        );
    }
}
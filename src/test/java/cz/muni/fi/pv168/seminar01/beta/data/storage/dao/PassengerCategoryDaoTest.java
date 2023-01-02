package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.PassengerCategoryEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class PassengerCategoryDaoTest {
    private DatabaseManager manager;

    private PassengerCategoryDao passengerCategoryDao;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
        this.passengerCategoryDao = new PassengerCategoryDao(manager::getConnectionHandler);
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    @Test
    void listAllPassengerCategories() {
        var allPassengerCategories = passengerCategoryDao.findAll();
        assertThat(allPassengerCategories)
                .as("There should be 3 testing passenger categories")
                .hasSize(3);
    }

    @Test
    void findExistingPassengerCategory() {
        var firstPassengerCategory = passengerCategoryDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        var foundPassengerCategory = passengerCategoryDao.findById(firstPassengerCategory.id());

        assertThat(foundPassengerCategory)
                .isPresent()
                .contains(firstPassengerCategory);
    }

    @Test
    void createNewPassengerCategory() {
        PassengerCategoryEntity newPassengerCategoryParams = new PassengerCategoryEntity("Testing PassengerCategory");

        var createdPassengerCategory = passengerCategoryDao.
                create(newPassengerCategoryParams);

        assertThat(createdPassengerCategory)
                .as("There should be a new passengerCategory created")
                .isNotNull();

        assertThat(createdPassengerCategory.id())
                .isGreaterThan(1);

        assertThat(createdPassengerCategory.name()).isEqualTo(newPassengerCategoryParams.name());

        var allPassengerCategories = passengerCategoryDao.findAll();

        assertThat(allPassengerCategories)
                .as("The newly created passengerCategory has to be stored in the database")
                .contains(createdPassengerCategory);
    }

    @Test
    void deleteRodinaPassengerCategory() {
        var allPassengerCategories = passengerCategoryDao.findAll();
        var rodina = allPassengerCategories.stream()
                .filter(d -> d.name().equals("Rodina"))
                .findFirst();

        assertThat(rodina)
                .isPresent();

        passengerCategoryDao.deleteById(rodina.map(PassengerCategoryEntity::id).orElseThrow());

        var allPassengerCategoriesAfterDelete = passengerCategoryDao.findAll();

        var salesAfterDelete = allPassengerCategoriesAfterDelete.stream()
                .filter(d -> d.name().equals("Rodina"))
                .findFirst();

        assertThat(salesAfterDelete)
                .isEmpty();
    }

    @Test
    void deleteRodinaPassengerCategoryTwoTimes() {
        var allPassengerCategories = passengerCategoryDao.findAll();
        var rodina = allPassengerCategories.stream()
                .filter(d -> d.name().equals("Rodina"))
                .findFirst()
                .orElseThrow();

        // delete first time
        passengerCategoryDao.deleteById(rodina.id());

        // delete second time should produce an exception

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> passengerCategoryDao.deleteById(rodina.id()))
                .withMessageContaining("not found");
    }

    @Test
    void deleteNotStoredPassengerCategory() {
        PassengerCategoryEntity passengerCategoryEntity = createPassengerCategoryEntity();

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> passengerCategoryDao.deleteById(passengerCategoryEntity.id()));
    }

    private static PassengerCategoryEntity createPassengerCategoryEntity() {

        return new PassengerCategoryEntity(
                -1000L,
                "test"
        );
    }

}

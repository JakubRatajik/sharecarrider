package cz.muni.fi.pv168.seminar01.beta.data.storage.dao;

import cz.muni.fi.pv168.seminar01.beta.data.storage.DataStorageException;
import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
import cz.muni.fi.pv168.seminar01.beta.data.storage.entity.VehicleEntity;
import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class VehicleDaoTest {
    private DatabaseManager manager;

    private VehicleDao vehicleDao;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
        this.vehicleDao = new VehicleDao(manager::getConnectionHandler);
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    @Test
    void listAllVehicles() {
        var allVehicles = vehicleDao.findAll();
        assertThat(allVehicles)
                .as("There should be 3 testing vehicles")
                .hasSize(3);
    }

    @Test
    void findExistingVehicle() {
        var firstVehicle = vehicleDao.findAll().
                stream().
                findFirst()
                .orElseThrow();

        var foundVehicle = vehicleDao.findById(firstVehicle.id());

        assertThat(foundVehicle)
                .isPresent()
                .contains(firstVehicle);
    }

    @Test
    void createNewVehicle() {
        VehicleEntity newVehicleParams = new VehicleEntity(null, "3C56787", "VW", "Golf", 5, 5, FuelType.CNG);

        var createdVehicle = vehicleDao.
                create(newVehicleParams);

        assertThat(createdVehicle)
                .as("There should be a new vehicle created")
                .isNotNull();

        assertThat(createdVehicle.id())
                .isGreaterThan(1);

        assertThat(createdVehicle.licensePlate()).isEqualTo(newVehicleParams.licensePlate());
        assertThat(createdVehicle.brand()).isEqualTo(newVehicleParams.brand());
        assertThat(createdVehicle.type()).isEqualTo(newVehicleParams.type());
        assertThat(createdVehicle.capacity()).isEqualTo(newVehicleParams.capacity());
        assertThat(createdVehicle.consumption()).isEqualTo(newVehicleParams.consumption());
        assertThat(createdVehicle.fuelType()).isEqualTo(newVehicleParams.fuelType());

        var allVehicles = vehicleDao.findAll();

        assertThat(allVehicles)
                .as("The newly created vehicle has to be stored in the database")
                .contains(createdVehicle);
    }


    @Test
    void deleteSkodaVehicles() {
        var allDepartments = vehicleDao.findAll();
        var bmw = allDepartments.stream()
                .filter(d -> d.brand().equals("BMW"))
                .findFirst();

        assertThat(bmw)
                .isPresent();

        vehicleDao.deleteById(bmw.map(VehicleEntity::id).orElseThrow());

        var allVehiclesAfterDelete = vehicleDao.findAll();

        var bmwAfterDelete = allVehiclesAfterDelete.stream()
                .filter(d -> d.brand().equals("BMW"))
                .findFirst();

        assertThat(bmwAfterDelete)
                .isEmpty();
    }

    @Test
    void deleteBMWVehicleTwoTimes() {
        var allVehicles = vehicleDao.findAll();
        var bmw = allVehicles.stream()
                .filter(d -> d.brand().equals("BMW"))
                .findFirst()
                .orElseThrow();

        // delete first time
        vehicleDao.deleteById(bmw.id());

        // delete second time should produce an exception

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> vehicleDao.deleteById(bmw.id()))
                .withMessageContaining("not found");
    }

    @Test
    void deleteNotStoredVehicle() {
        VehicleEntity vehicleEntity = createVehicleEntity();

        assertThatExceptionOfType(DataStorageException.class)
                .isThrownBy(() -> vehicleDao.deleteById(vehicleEntity.id()));
    }

    private static VehicleEntity createVehicleEntity() {

        return new VehicleEntity(
                -1000L,
                "2K93030",
                "Toyota",
                "Yaris",
                4,
                5.8,
                FuelType.DIESEL
        );
    }
}



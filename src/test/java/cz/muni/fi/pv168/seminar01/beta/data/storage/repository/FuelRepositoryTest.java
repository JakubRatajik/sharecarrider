//package cz.muni.fi.pv168.seminar01.beta.data.storage.repository;
//
//import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;
//import cz.muni.fi.pv168.seminar01.beta.model.Fuel;
//import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
//import cz.muni.fi.pv168.seminar01.beta.wiring.TestDependencyProvider;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Jakub Ratajik
// */
//public class FuelRepositoryTest {
//    private DatabaseManager databaseManager;
//    private Repository<Fuel> fuelRepository;
//
//    @BeforeEach
//    void setUp() {
//        databaseManager = DatabaseManager.createTestInstance();
//        var dependencyProvider = new TestDependencyProvider(databaseManager);
//        fuelRepository = dependencyProvider.getFuelRepository();
//    }
//
//    @AfterEach
//    void tearDown() {
//        databaseManager.destroySchema();
//    }
//
//    @Test
//    void createNewFuel() {
//        Fuel fuelToCreate = new Fuel(FuelType.GASOLINE, new BigDecimal(""));
//        fuelRepository.create(fuelToCreate);
//
//        Fuel storedFuel = fuelRepository
//                .findByIndex(fuelRepository.getSize() - 1) // get latest
//                .orElseThrow();
//
//        assertThat(storedFuel.getFuelType()).isEqualTo(fuelToCreate.getFuelType());
//        assertThat(storedFuel.getPrice()).isEqualTo(fuelToCreate.getPrice());
//        assertThat(storedFuel.getId()).isNotNull();
//    }
//
//    @Test
//    void listAllTestingFuels() {
//        List<Fuel> storedFuels = fuelRepository
//                .findAll();
//
//        assertThat(storedFuels).hasSize(3);
//    }
//
//    @Test
//    void findFuelByIndex() {
//        Optional<Fuel> storedFuel = fuelRepository
//                .findByIndex(1);
//
//        assertThat(storedFuel).isPresent();
//    }
//
//    @Test
//    void findDepartmentByIndexShouldFailForIndexTooHigh() {
//        Optional<Department> storedDepartment = fuelRepository
//                .findByIndex(1000);
//
//        assertThat(storedDepartment).isEmpty();
//    }
//
//    @Test
//    void findDepartmentById() {
//        Optional<Department> storedDepartment = fuelRepository
//                .findById(1);
//
//        assertThat(storedDepartment).isPresent();
//    }
//
//    @Test
//    void findDepartmentByIndexShouldFailForIdTooHigh() {
//        Optional<Department> storedDepartment = fuelRepository
//                .findById(1000);
//
//        assertThat(storedDepartment).isEmpty();
//    }
//
//    @Test
//    void updateDepartment() {
//        var sales = fuelRepository.findAll()
//                .stream()
//                .filter(e -> e.getName().equals("Sales"))
//                .findFirst()
//                .orElseThrow();
//
//        sales.setName("Updated Sales");
//
//        fuelRepository.update(sales);
//
//        Department updatedDepartment = fuelRepository.findById(sales.getId()).orElseThrow();
//
//        assertThat(updatedDepartment.getName()).isEqualTo("Updated Sales");
//    }
//
//}

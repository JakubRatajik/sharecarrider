package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class VehicleImporterTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    @Test
    void noVehicle() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("empty.csv");
        Collection<Vehicle> vehicles = VehicleImporter.importVehicles(new File(importFilePath.toString()));

        assertThat(vehicles).isEmpty();
    }

    @Test
    void singleVehicle() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("single-vehicle.csv");
        Collection<Vehicle> vehicles = VehicleImporter.importVehicles(new File(importFilePath.toString()));

        assertThat(vehicles).containsExactlyInAnyOrder(
                new Vehicle(250, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL)
        );
    }

    @Test
    void multipleVehicles() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-vehicles.csv");
        Collection<Vehicle> vehicles = VehicleImporter.importVehicles(new File(importFilePath.toString()));

        assertThat(vehicles).containsExactlyInAnyOrder(
                new Vehicle(253, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL),
                new Vehicle(251, "BL87675", "Škoda", "Octavia", 5, 9.5, FuelType.GASOLINE),
                new Vehicle(252, "7T64354", "Hyundai", "i30", 5, 6.7, FuelType.CNG)
        );
    }

    @Test
    void nonExistingFile() {
        Path importFilePath = TEST_RESOURCES.resolve("non-existing-file.csv");

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> VehicleImporter.importVehicles(new File(importFilePath.toString())))
                .withMessageContaining("No such file");
    }
}

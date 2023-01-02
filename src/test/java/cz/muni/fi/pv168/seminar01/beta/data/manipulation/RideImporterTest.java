package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RideImporterTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    @Test
    void noRide() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("empty.csv");
        Collection<Ride> rides = RideImporter.importRides(new File(importFilePath.toString()));

        assertThat(rides).isEmpty();
    }

    @Test
    void singleRide() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("single-ride.csv");
        Collection<Ride> rides = RideImporter.importRides(new File(importFilePath.toString()));
        var vehicle = new Vehicle(279, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL);
        assertThat(rides).containsExactlyInAnyOrder(
                new Ride(280, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Košice", 380, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, "")
        );
    }

    @Test
    void multipleRides() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-rides.csv");
        Collection<Ride> rides = RideImporter.importRides(new File(importFilePath.toString()));
        var vehicle = new Vehicle(279, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL);
        assertThat(rides).containsExactlyInAnyOrder(
                new Ride(281, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Košice", 380, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, ""),
                new Ride(282, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Komárov", "Zálší", 13, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.NONE, ""),
                new Ride(283, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Praha", 279, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, "halo halo")
        );
    }

    @Test
    void nonExistingFile() {
        Path importFilePath = TEST_RESOURCES.resolve("non-existing-file.csv");

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> RideImporter.importRides(new File(importFilePath.toString())))
                .withMessageContaining("No such file");
    }
}

package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class PassengerImporterTest {

    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    @Test
    void noPassenger() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("empty.csv");
        Collection<Passenger> passengers = PassengerImporter.importPassengers(new File(importFilePath.toString()));

        assertThat(passengers).isEmpty();
    }

    @Test
    void singlePassenger() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("single-passenger.csv");
        Collection<Passenger> passengers = PassengerImporter.importPassengers(new File(importFilePath.toString()));

        assertThat(passengers).containsExactlyInAnyOrder(
                new Passenger(900, "Jana", "Nová", "777890985", new ArrayList<>())
        );
    }

    @Test
    void multiplePassengers() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-passengers.csv");
        Collection<Passenger> passengers = PassengerImporter.importPassengers(new File(importFilePath.toString()));

        assertThat(passengers).containsExactlyInAnyOrder(
                new Passenger(901, "Petr", "Pařez", "909867777", new ArrayList<>()),
                new Passenger(902, "Eva", "Nováková", "675786766", new ArrayList<>()),
                new Passenger(903, "Jaroslav", "Pokorný", "445432345", new ArrayList<>())
        );
    }

    @Test
    void nonExistingFile() {
        Path importFilePath = TEST_RESOURCES.resolve("non-existing-file.csv");

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> PassengerImporter.importPassengers(new File(importFilePath.toString())))
                .withMessageContaining("No such file");
    }
}


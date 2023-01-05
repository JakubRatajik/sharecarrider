package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RideCategoryImporterTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    @Test
    void noRideCategory() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("empty.csv");
        Collection<RideCategory> rideCategories = RideCategoryImporter.importRideCategories(new File(importFilePath.toString()));

        assertThat(rideCategories).isEmpty();
    }

    @Test
    void singleRideCategory() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("single-rideCategory.csv");
        Collection<RideCategory> rideCategories = RideCategoryImporter.importRideCategories(new File(importFilePath.toString()));

        assertThat(rideCategories.stream().map(Category::getName)).containsExactlyInAnyOrder("práce");
    }

    @Test
    void multipleRideCategories() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-rideCategories.csv");
        Collection<RideCategory> rideCategories = RideCategoryImporter.importRideCategories(new File(importFilePath.toString()));

        assertThat(rideCategories.stream().map(Category::getName)).containsExactlyInAnyOrder("rodina", "zábava", "práce");
    }

    @Test
    void nonExistingFile() {
        Path importFilePath = TEST_RESOURCES.resolve("non-existing-file.csv");

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> RideCategoryImporter.importRideCategories(new File(importFilePath.toString())))
                .withMessageContaining("No such file");
    }
}

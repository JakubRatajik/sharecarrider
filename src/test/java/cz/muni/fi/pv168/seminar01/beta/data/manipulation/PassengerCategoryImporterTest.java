package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PassengerCategoryImporterTest {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("project.basedir", "")).toAbsolutePath();
    private static final Path TEST_RESOURCES = PROJECT_ROOT.resolve(Path.of("src", "test", "resources"));

    @Test
    void noPassengerCategory() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("empty.csv");
        Collection<PassengerCategory> passengerCategories = PassengerCategoryImporter.importPassengerCategories(new File(importFilePath.toString()));

        assertThat(passengerCategories).isEmpty();
    }

    @Test
    void singlePassengerCategory() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("single-passengerCategory.csv");
        List<PassengerCategory> passengerCategories = PassengerCategoryImporter.importPassengerCategories(new File(importFilePath.toString()));

        assertThat(passengerCategories.stream().map(Category::getName)).containsExactlyInAnyOrder("Rodina");
    }

    @Test
    void multiplePassengerCategories() throws FileNotFoundException {
        Path importFilePath = TEST_RESOURCES.resolve("multiple-passengerCategories.csv");
        Collection<PassengerCategory> passengerCategories = PassengerCategoryImporter.importPassengerCategories(new File(importFilePath.toString()));

        assertThat(passengerCategories.stream().map(Category::getName)).containsExactlyInAnyOrder("Rodina", "Tajné", "Práce");
    }

    @Test
    void nonExistingFile() {
        Path importFilePath = TEST_RESOURCES.resolve("non-existing-file.csv");

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> PassengerCategoryImporter.importPassengerCategories(new File(importFilePath.toString())))
                .withMessageContaining("No such file");
    }
}

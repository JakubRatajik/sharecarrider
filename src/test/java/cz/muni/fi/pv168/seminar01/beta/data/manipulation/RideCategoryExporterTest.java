package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.RideCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class RideCategoryExporterTest {

    @TempDir
    private static Path testDir;

    private final Path exportFilePath = testDir.resolve(Instant.now().toString().replace(':', '_'));
    private final RideCategoryExporter exporter = new RideCategoryExporter();

    @Test
    void noRideCategory() throws IOException {
        exporter.export(List.of(), exportFilePath.toString());

        assertExportedContent("");
    }

    @Test
    void singleRideCategory() throws IOException {
        var rideCategory = new RideCategory(320, "práce");

        exporter.export(List.of(rideCategory), exportFilePath.toString());

        assertExportedContent("""
                320;práce
                """);
    }

    @Test
    void multipleRideCategorys() throws IOException {
        List<RideCategory> rideCategories = List.of(
                new RideCategory(321, "práce"),
                new RideCategory(322,"rodina"),
                new RideCategory(323,"zábava")
        );

        exporter.export(rideCategories, exportFilePath.toString());

        assertExportedContent("""
                321;práce
                322;rodina
                323;zábava
                """);
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}


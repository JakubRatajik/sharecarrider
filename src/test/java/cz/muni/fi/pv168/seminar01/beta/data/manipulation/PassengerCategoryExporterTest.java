package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PassengerCategoryExporterTest {

    @TempDir
    private static Path testDir;

    private final Path exportFilePath = testDir.resolve(Instant.now().toString().replace(':', '_'));
    private final PassengerCategoryExporter exporter = new PassengerCategoryExporter();

    @Test
    void noPassengerCategory() throws IOException {
        exporter.export(List.of(), exportFilePath.toString());

        assertExportedContent("");
    }

    @Test
    void singlePassengerCategory() throws IOException {
        var passengerCategory = new PassengerCategory(400,"Rodina");

        exporter.export(List.of(passengerCategory), exportFilePath.toString());

        assertExportedContent("""
                400;Rodina
                """);
    }

    @Test
    void multiplePassengerCategorys() throws IOException {
        List<PassengerCategory> passengerCategories = List.of(
                new PassengerCategory(401, "Rodina"),
                new PassengerCategory(402,"Tajné"),
                new PassengerCategory(403, "Práce")
        );

        exporter.export(passengerCategories, exportFilePath.toString());

        assertExportedContent("""
                401;Rodina
                402;Tajné
                403;Práce
                """);
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}

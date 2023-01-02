package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class PassengerExporterTest {

    @TempDir
    private static Path testDir;

    private final Path exportFilePath = testDir.resolve(Instant.now().toString().replace(':', '_'));
    private final PassengerExporter exporter = new PassengerExporter();

    @Test
    void noPassenger() throws IOException {
        exporter.export(new ArrayList<>(), exportFilePath.toString());

        assertExportedContent("");
    }

    @Test
    void singlePassenger() throws IOException {
        var passenger = new Passenger(900, "Jana", "Nová", "777890985", new ArrayList<>());

        exporter.export(List.of(passenger), exportFilePath.toString());

        assertExportedContent("""
                900;Jana;Nová;777890985;[]
                """);
    }

    @Test
    void multiplePassengers() throws IOException {

        List<Passenger> passengers = List.of(
                new Passenger(901,"Petr", "Pařez","909867777", new ArrayList<>()),
                new Passenger(902,"Eva", "Nováková", "675786766", new ArrayList<>()),
                new Passenger(903,"Jaroslav", "Pokorný", "445432345", new ArrayList<>())
        );

        exporter.export(passengers, exportFilePath.toString());

        assertExportedContent("""
                901;Petr;Pařez;909867777;[]
                902;Eva;Nováková;675786766;[]
                903;Jaroslav;Pokorný;445432345;[]
                """);
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}

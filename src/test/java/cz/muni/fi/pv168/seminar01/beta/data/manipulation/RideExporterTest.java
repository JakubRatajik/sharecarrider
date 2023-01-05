package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class RideExporterTest {

    @TempDir
    private static Path testDir;

    private final Path exportFilePath = testDir.resolve(Instant.now().toString().replace(':', '_'));
    private final RideExporter exporter = new RideExporter();

    @Test
    void noRide() throws IOException {
        exporter.export(List.of(), exportFilePath.toString());

        assertExportedContent("");
    }

    @Test
    void singleRide() throws IOException {
        var vehicle = new Vehicle(279, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL);
        var ride = new Ride(280, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Košice", 380, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, "");

        exporter.export(List.of(ride), exportFilePath.toString());

        assertExportedContent("""
                280;-999999999-01-01;00:00;23:59;Brno;Košice;380;[];[];279;MONTHLY;
                """);
    }

    @Test
    void multipleRides() throws IOException {
        var vehicle = new Vehicle(278, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL);
        List<Ride> rides = List.of(
                new Ride(281, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Košice", 380, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, ""),
                new Ride(282, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Komárov", "Zálší", 13, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.NONE, ""),
                new Ride(283, LocalDate.MIN, LocalTime.MIN, LocalTime.MAX, "Brno", "Praha", 250, new ArrayList<>(), new ArrayList<>(), vehicle, Repetition.MONTHLY, "halo halo")
        );

        exporter.export(rides, exportFilePath.toString());

        assertExportedContent("""
                281;-999999999-01-01;00:00;23:59;Brno;Košice;380;[];[];278;MONTHLY;
                282;-999999999-01-01;00:00;23:59;Komárov;Zálší;13;[];[];278;NONE;
                283;-999999999-01-01;00:00;23:59;Brno;Praha;250;[];[];278;MONTHLY;halo halo
                """);
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}


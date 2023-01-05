package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.FuelType;
import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class VehiclesExporterTest {

    @TempDir
    private static Path testDir;

    private final Path exportFilePath = testDir.resolve(Instant.now().toString().replace(':', '_'));
    private final VehicleExporter exporter = new VehicleExporter();

    @Test
    void noVehicle() throws IOException {
        exporter.export(List.of(), exportFilePath.toString());

        assertExportedContent("");
    }

    @Test
    void singleVehicle() throws IOException {
        var vehicle = new Vehicle(250, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL);

        exporter.export(List.of(vehicle), exportFilePath.toString());

        assertExportedContent("""
                250;1H79065;Mercedes;GLA;7;10.3;DIESEL
                """);
    }

    @Test
    void multipleVehicles() throws IOException {
        List<Vehicle> vehicles = List.of(
                new Vehicle(253, "1H79065", "Mercedes", "GLA", 7, 10.3, FuelType.DIESEL),
                new Vehicle(251, "BL87675", "Škoda", "Octavia", 5, 9.5, FuelType.GASOLINE),
                new Vehicle(252, "7T64354", "Hyundai", "i30",5, 6.7, FuelType.CNG)
        );

        exporter.export(vehicles, exportFilePath.toString());

        assertExportedContent("""
                253;1H79065;Mercedes;GLA;7;10.3;DIESEL
                251;BL87675;Škoda;Octavia;5;9.5;GASOLINE
                252;7T64354;Hyundai;i30;5;6.7;CNG
                """);
    }

    private void assertExportedContent(String expectedContent) throws IOException {
        assertThat(Files.readString(exportFilePath))
                .isEqualToIgnoringNewLines(expectedContent);
    }
}


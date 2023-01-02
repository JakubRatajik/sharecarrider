package cz.muni.fi.pv168.seminar01.beta.data.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class VehicleValidatorTest {
    @Test
    void validVehicle() {
        assertThatCode(() -> VehicleValidator.validateVehicle(null, "3C56787", "VW", "Golf", "5", "5.5", "LPG"))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidBrandFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> VehicleValidator.validateVehicle(null, "3C56787", "99!!", "Golf", "5", "5.5", "LPG"))
                .withMessageContaining("Značka");
    }

    @Test
    void invalidModelFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> VehicleValidator.validateVehicle(null, "3C56787", "VW", "##", "5", "5.5", "LPG"))
                .withMessageContaining("Model");
    }

    @Test
    void invalidCapacityFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> VehicleValidator.validateVehicle(null, "3C56787", "VW", "Golf", "pět", "5.5", "LPG"))
                .withMessageContaining("Kapacita");
    }

    @Test
    void invalidConsumptionFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> VehicleValidator.validateVehicle(null, "3C56787", "VW", "Golf", "5", "pět", "LPG"))
                .withMessageContaining("Spotřeba");
    }

    @Test
    void invalidFuelFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> VehicleValidator.validateVehicle(null, "3C56787", "VW", "Golf", "5", "5.5", "voda"))
                .withMessageContaining("typ paliva");
    }

    @Test
    void validLicensePlate() {
        assertThat(VehicleValidator.isLicensePlateValid("3C56767")).isTrue();
    }

    @Test
    void invalidLicensePlate() {
        assertThat(VehicleValidator.isLicensePlateValid("##")).isFalse();
    }

    @Test
    void validFuelType() {
        assertThat(VehicleValidator.isFuelTypeValid("CNG")).isTrue();
    }

    @Test
    void invalidFuelType() {
        assertThat(VehicleValidator.isFuelTypeValid("22")).isFalse();
    }
}

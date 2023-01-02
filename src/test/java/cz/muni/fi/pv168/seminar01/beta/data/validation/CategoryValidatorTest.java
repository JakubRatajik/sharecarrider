package cz.muni.fi.pv168.seminar01.beta.data.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CategoryValidatorTest {
    @Test
    void validRideCategory() {
        assertThatCode(() -> CategoryValidator.validateRideCategory("1", "kategorie"))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidRideCategory() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> CategoryValidator.validateRideCategory(null, "kategorie"))
                .withMessageContaining("Kategorie");
    }

    @Test
    void validPassengerCategory() {
        assertThatCode(() -> CategoryValidator.validatePassengerCategory("1", "kategorie"))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidPassenegerCategory() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> CategoryValidator.validatePassengerCategory(null, "kategorie"))
                .withMessageContaining("Kategorie");
    }


}

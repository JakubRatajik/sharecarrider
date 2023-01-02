package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Jakub Ratajik
 */
public class PassengerValidatorTest {
    @Test
    void validPassenger() {
        assertThatCode(() -> PassengerValidator.validatePassenger("Katerina", "Kacicka", "+420918299032"))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidPhoneNumber() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> PassengerValidator.validatePassenger("Romek", "Tatka", "+42089745"))
                .withMessageContaining("číslo");
    }

    @Test
    void invalidPassenger() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> PassengerValidator.validatePassenger("{} or Ø", "is a sign of empty set", "789456123"));
    }
}

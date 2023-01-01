package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.model.Repetition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Jakub Ratajik
 */
public class RideValidatorTest {
    @Test
    void validRide() {
        assertThatCode(() -> RideValidator.validateRide(null, "2022-02-06", "22:49", "23:39", "Katerinske jeskyne", "Strasne paliva india", "780", null, null, null, null, ""))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidDateFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> RideValidator.validateRide(null, "42", "22:40", "23:30", "Brno", "Blansko", "42", null, null, null, null, ""))
                .withMessageContaining("datum");
    }

    @Test
    void invalidTimeFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> RideValidator.validateRide(null, "2022-05-06", "2240", "23:30", "Brno", "Blansko", "42", null, null, null, null, ""))
                .withMessageContaining("čas");
    }

    @Test
    void invalidFromFormat() {
        String longFrom = new String(new char[101]).replace('\0', ' ');

        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> RideValidator.validateRide(null, "2022-05-06", "22:40", "23:30", longFrom, "Blansko", "42", null, null, null, null, ""))
                .withMessageContaining("znak");
    }

    @Test
    void invalidDistanceFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> RideValidator.validateRide(null, "2022-05-06", "22:40", "23:30", "Brno", "Blansko", "2.5", null, null, null, null, ""))
                .withMessageContaining("Vzdálenost");
    }

    @Test
    void validRepetitionFormat() {
        assertThatCode(() -> RideValidator.validateRide(null, "2022-02-06", "22:49", "23:39", "Katerinske jeskyne", "Strasne paliva india", "780", null, null, null, Repetition.MONTHLY.name(), ""))
                .doesNotThrowAnyException();

    }

    @Test
    void invalidRepetitionFormat() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> RideValidator.validateRide(null, "2022-05-06", "22:40", "23:30", "Brno", "Blansko", "42", null, null, null, "null", ""))
                .withMessageContaining("opakování");
    }




}

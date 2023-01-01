package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Jakub Ratajik
 */
public class CommonValidatorTest {
    @Test
    void validLongCzechNumInput() {
        assertThat(CommonValidator.isValidLongCzechNumInput("Rejstřík číslo 55")).isTrue();
    }

    @Test
    void invalidLongCzechNumInput() {
        assertThat(CommonValidator.isValidLongCzechNumInput("{} or Ø is a empty set")).isFalse();
    }

    @Test
    void validIdList() {
        assertThat(CommonValidator.isValidLongCzechInput("{} or Ø is a empty set")).isFalse();
    }

    @Test
    void invalidPassengerIdList() {
        assertThat(CommonValidator.isValidIdList("[1, 2, a]", TableCategory.PASSENGERS)).isFalse();
    }

    @Test
    void validEmptyPassengerIdList() {
        assertThat(CommonValidator.isValidIdList("[]", TableCategory.PASSENGERS)).isTrue();
    }

    @Test
    void invalidDoubleParsing() {
        assertThat(CommonValidator.isValidDoubleParsing("145.7a8663")).isFalse();
    }

    @Test
    void validDoubleParsing() {
        assertThat(CommonValidator.isValidDoubleParsing("124878.7984")).isTrue();
    }
}

package racingcar.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class InputValidatorTest {

    @ParameterizedTest
    @NullSource
    void null값을_입력할_경우_예외를_발생한다(String value) {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> InputValidator.blankValue(value));
    }


    @ParameterizedTest
    @EmptySource
    void 비어있는_값을_입력할_경우_예외를_발생한다(String value) {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> InputValidator.blankValue(value));
    }
}

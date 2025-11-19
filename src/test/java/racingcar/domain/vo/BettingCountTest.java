package racingcar.domain.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingCountTest {

    @Test
    void 유효한_범위_내의_값이_아니면_예외를_발생한다() {
        //when
        int number = 44;
        //then
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new BettingCount(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10})
    void 경계값은_유효성_검증을_통과한다(int number) {
        //then
        Assertions.assertDoesNotThrow(() -> new BettingCount(number));
    }
}

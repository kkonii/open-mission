package racingcar.v2.view;

import java.io.ByteArrayInputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.common.ConsoleScanner;

public class InputViewTest {

    @Test
    void 자동차_모델과_이름에_대한_입력을_받는다() {
        // given
        String input = "BIKE-강냉이,CAR-돌고래";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // when
        String result = ConsoleScanner.readLine();
        // then
        Assertions.assertThat(result).isEqualTo(input);
    }
}

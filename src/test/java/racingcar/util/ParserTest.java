package racingcar.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    void 입력값을_그룹_단위로_구분하여_반환한다() {
        //given
        String consoleInput = "CAR-오렌지,TAXI-이루카";
        //when
        List<String> parseGroups = Parser.toGroups(consoleInput);
        //then
        Assertions.assertThat(parseGroups).containsExactly("CAR-오렌지", "TAXI-이루카");
    }

    @Test
    void 차량과_이름_입력값을_구분자_단위로_구분하여_반환한다() {
        //given
        String consoleInput = "BUS-꼬북";
        //when
        List<String> parsedAttributes = Parser.separateAttributes(consoleInput);
        //then
        Assertions.assertThat(parsedAttributes)
                .containsExactlyElementsOf(List.of("CAR", "꼬북"));
    }
}

package racingcar.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.AttributeDto;
import racingcar.exception.CommonError;

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
                .containsExactlyElementsOf(List.of("BUS", "꼬북"));
    }

    @Test
    void 차량과_이름_속성값이_모두_입력되지_않으면_예외를_반환한다() {
        //when
        String consoleInput = "BUS-";
        //then
        Assertions.assertThatThrownBy(() -> Parser.separateAttributes(consoleInput))
                .hasMessage(CommonError.ATTRIBUTE_FORMAT_IS_NOT_VALID.message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"BIKE", "BIKE-이름-홍삼", "-BUS-버스",})
    void 구분자로_연결된_요소가_두_개가_아니면_예외를_발생한다(String consoleInput) {
        //then
        Assertions.assertThatThrownBy(() -> Parser.separateAttributes(consoleInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CommonError.ATTRIBUTE_FORMAT_IS_NOT_VALID.message());
    }

    @Test
    void 구분자로_연결된_요소가_두_개면_검증을_통과한다() {
        //when
        String consoleInput = "BIKE-홍삼";
        //then
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> Parser.separateAttributes(consoleInput));
    }

    @ParameterizedTest
    @CsvSource({"TAXI-이루카,TAXI,이루카", "BUS-당근,BUS,당근"})
    void 입력값을_파싱하여_차량과_이름_속성값을_반환한다(String consoleInput, String modelName, String riderName) {
        //when
        List<AttributeDto> attributes = Parser.toAttributes(consoleInput);
        //then
        attributes.forEach(attribute ->
                Assertions.assertThat(attribute).isEqualTo(new AttributeDto(modelName, riderName)));
    }
}

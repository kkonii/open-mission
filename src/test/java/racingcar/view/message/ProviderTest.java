package racingcar.view.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.system.Language;

public class ProviderTest {

    @ParameterizedTest
    @MethodSource("racingcar.fixture.Provider#languageMessageArguments")
    void 선택한_언어로_출력문구를_패치한다(Language language, MessageKey messageKey, String expectedMessage) {
        //given
        Provider provider = Provider.fetchBy(language);
        //when
        String actualMessage = provider.messageOf(messageKey);
        //then
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}

package racingcar.system;

import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Utf8ResourceBundleTest {

    @Test
    void UTF8_인코딩_규칙을_통해_문자를_로딩하는_데에_성공한다() {
        // given
        Utf8ResourceBundle control = new Utf8ResourceBundle();
        String baseName = "messages";
        // when
        Locale locale = Locale.KOREAN;
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, control);
        // then
        Assertions.assertEquals("테스트용 문구", bundle.getString("TEST"));
        Assertions.assertEquals("한글과 english", bundle.getString("KO_AND_EN"));
    }
}

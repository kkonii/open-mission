package racingcar.system;

import java.util.Arrays;
import java.util.Locale;

public enum Language {

    KOREAN(1, Locale.of("ko", "KR")),
    ENGLISH(2, Locale.of("en", "US")),
    JAPANESE(3, Locale.of("ja", "JP"));

    private final int number;
    private final Locale locale;

    Language(int number, Locale locale) {
        this.number = number;
        this.locale = locale;
    }

    public static Language findBy(int number) {
        return Arrays.stream(values())
                .filter(lang -> lang.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요."));
    }

    public Locale locale() {
        return this.locale;
    }
}

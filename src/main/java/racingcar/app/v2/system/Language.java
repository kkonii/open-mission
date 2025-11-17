package racingcar.app.v2.system;

import java.util.Arrays;

public enum Language {

    KOREAN(1),
    ENGLISH(2),
    JAPANESE(3);

    private final int number;

    Language(int number) {
        this.number = number;
    }

    public static Language findBy(int number) {
        return Arrays.stream(values())
                .filter(lang -> lang.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요."));
    }
}

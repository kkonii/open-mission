package racingcar.common;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.exception.CommonError;

public class InputValidator {

    private static final Pattern NUMERIC = Pattern.compile("^[+-]?\\d+$");

    public static void blankValue(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException(CommonError.INPUT_IS_BLANK.message());
        }
    }

    public static void numericType(String value) {
        Matcher matcher = NUMERIC.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(CommonError.NOT_A_NUMBER.message());
        }
    }

    public static void rangeOf(String value) {
        long parsed = Long.parseLong(value);

        if (parsed < Integer.MIN_VALUE || parsed > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(CommonError.OUT_OF_INTEGER_RANGE.message());
        }
    }
}

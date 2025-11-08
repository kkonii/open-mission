package racingcar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.exception.CommonError;

public class InputValidator {

    private static final Pattern NAME_FORMAT_PATTERN = Pattern.compile("^[\\w가-힣]+(,+[\\w가-힣]+)*$");
    private static final Pattern NUMERIC = Pattern.compile("^[+-]?\\d+$");

    public static void blankValue(String value) {
        if (value.isBlank()) {
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

    public static void nameFormat(String consoleInput) {
        Matcher matcher = NAME_FORMAT_PATTERN.matcher(consoleInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(CommonError.NAME_FORMAT_IS_NOT_VALID_PATTERN.message());
        }
    }
}

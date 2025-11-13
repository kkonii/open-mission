package racingcar.app.v1.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import racingcar.common.InputValidator;
import racingcar.exception.CommonError;

public class Parser {

    private static final Pattern NAME_FORMAT_PATTERN = Pattern.compile("^[\\w가-힣]+(,+[\\w가-힣]+)*$");
    private static final String DELIMITER = ",";

    public static int toInteger(String value) {
        InputValidator.numericType(value);
        InputValidator.rangeOf(value);

        return Integer.parseInt(value);
    }

    public static List<String> toNames(String consoleInput) {
        nameFormat(consoleInput);

        return Arrays.stream(consoleInput.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static void nameFormat(String consoleInput) {
        Matcher matcher = NAME_FORMAT_PATTERN.matcher(consoleInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(CommonError.NAME_FORMAT_IS_NOT_VALID_PATTERN.message());
        }
    }
}

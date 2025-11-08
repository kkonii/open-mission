package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private static final String DELIMITER = ",";

    public static int toInteger(String value) {
        InputValidator.numericType(value);
        InputValidator.rangeOf(value);

        return Integer.parseInt(value);
    }

    public static List<String> toNames(String consoleInput) {
        InputValidator.nameFormat(consoleInput);

        return Arrays.stream(consoleInput.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }
}

package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private static final String GROUP_DELIMITER = ",";
    private static final String ATTRIBUTE_DELIMITER = "-";

    public static int toInteger(String value) {
        InputValidator.numericType(value);
        InputValidator.rangeOf(value);

        return Integer.parseInt(value);
    }

    public static List<String> toNames(String consoleInput) {
        InputValidator.nameFormat(consoleInput);

        return Arrays.stream(consoleInput.split(GROUP_DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static List<String> toGroups(String consoleInput) {
        // [ 그룹, 그룹, 그룹 ]
        return Arrays.stream(consoleInput.strip()
                .split(GROUP_DELIMITER)).toList();
    }

    public static List<String> separateAttributes(String separated) {
        return Arrays.stream(separated.split(ATTRIBUTE_DELIMITER))
                .toList();
    }
}

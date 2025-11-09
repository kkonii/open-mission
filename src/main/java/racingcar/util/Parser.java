package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.exception.CommonError;

public class Parser {

    private static final String GROUP_DELIMITER = ",";
    private static final String ATTRIBUTE_DELIMITER = "-";
    private static final int COUNT_OF_ATTRIBUTE = 2;

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
        List<String> parsedAttributes = Arrays.stream(separated.split(ATTRIBUTE_DELIMITER))
                .toList();

        validateFormatOf(parsedAttributes);

        return parsedAttributes;
    }

    private static void validateFormatOf(List<String> attributes) {
        if (attributes.size() != COUNT_OF_ATTRIBUTE) {
            throw new IllegalArgumentException(CommonError.ATTRIBUTE_FORMAT_IS_NOT_VALID.message());
        }
    }
}

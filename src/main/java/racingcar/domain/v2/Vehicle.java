package racingcar.domain.v2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.domain.strategy.VehicleModel;
import racingcar.exception.RaceError;

public class Vehicle {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]+$");
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final int MOVABLE_POINT = 4;

    private final VehicleModel model;
    private final String name;
    private int distance;

    private Vehicle(VehicleModel model, String name) {
        this.model = model;
        validateBlank(name);
        validateLength(name);
        validatePattern(name);
        this.name = name;
    }

    public static Vehicle createOf(String modelName, String riderName) {
        VehicleModel model = VehicleModel.findBy(modelName);

        return new Vehicle(model, riderName);
    }

    private void validateLength(String name) {
        if (name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(RaceError.NAME_LENGTH_IS_OVER.messageOf(MAXIMUM_NAME_LENGTH));
        }
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(RaceError.NAME_IS_BLANK.message());
        }
    }

    private void validatePattern(String name) {
        Matcher nameMatcher = NAME_PATTERN.matcher(name);

        if (!nameMatcher.matches()) {
            throw new IllegalArgumentException(RaceError.NAME_IS_NOT_VALID_PATTERN.message());
        }
    }
}

package racingcar.domain.v2;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.domain.strategy.VehicleModel;
import racingcar.exception.RaceError;

public class Vehicle {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]+$");
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final int MOVABLE_POINT = 4;

    private final VehicleModel model;
    private final String riderName;
    private int distance;

    private Vehicle(VehicleModel model, String riderName) {
        this.model = model;
        validateBlank(riderName);
        validateLength(riderName);
        validatePattern(riderName);
        this.riderName = riderName;
    }

    public static Vehicle createOf(String modelName, String riderName) {
        VehicleModel model = validatePresentOf(modelName);

        return new Vehicle(model, riderName);
    }

    private static VehicleModel validatePresentOf(String modelName) {
        Optional<VehicleModel> model = VehicleModel.findBy(modelName);
        if (model.isEmpty()) {
            throw new IllegalArgumentException(RaceError.VEHICLE_MODEL_NOT_EXIST.message());
        }

        return model.get();
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

    public VehicleModel getModel() {
        return model;
    }
}

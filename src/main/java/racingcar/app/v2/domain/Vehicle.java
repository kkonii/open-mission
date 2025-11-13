package racingcar.app.v2.domain;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.app.v2.domain.rule.VehicleModel;
import racingcar.exception.RaceError;

public class Vehicle implements Comparable<Vehicle> {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]+$");
    private static final int MAXIMUM_NAME_LENGTH = 5;

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

    public static Vehicle createOf(String modelName, String name) {
        VehicleModel model = validatePresentOf(modelName);

        return new Vehicle(model, name);
    }

    private static VehicleModel validatePresentOf(String modelName) {
        Optional<VehicleModel> model = VehicleModel.findBy(modelName);
        if (model.isEmpty()) {
            throw new IllegalArgumentException(RaceError.MODEL_NOT_EXIST.message());
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

    public void move(int number) {
        if (model.canMove(number)) {
            distance += model.getForward();
        }
    }

    public boolean movedFor(int distance) {
        return this.distance == distance;
    }

    public VehicleModel getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Vehicle v) {
        return Integer.compare(this.distance, v.distance);
    }
}

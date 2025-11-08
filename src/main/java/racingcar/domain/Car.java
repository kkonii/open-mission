package racingcar.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.exception.RaceError;

public class Car {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]+$");
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final int MOVABLE_POINT = 4;

    private final String name;
    private int distance;

    private Car(String name) {
        validateBlank(name);
        validateLength(name);
        validatePattern(name);
        this.name = name;
    }

    public static Car withName(String name) {
        return new Car(name);
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
        if (isMovable(number)) {
            distance++;
        }
    }

    private boolean isMovable(int number) {
        return number >= MOVABLE_POINT;
    }

    public int compareWith(int maxDistance) {
        return Math.max(this.distance, maxDistance);
    }

    public boolean equalsDistance(int distance) {
        return this.distance == distance;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
}

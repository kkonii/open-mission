package racingcar.domain.vo;

import racingcar.exception.RaceError;

public class BettingCount {

    private static final int MINIMUM_VALUE = 2;
    private static final int MAXIMUM_VALUE = 10;

    private final int number;

    public BettingCount(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int bettingCount) {
        if (bettingCount > MAXIMUM_VALUE || bettingCount < MINIMUM_VALUE) {
            throw new IllegalArgumentException(RaceError.BETTING_COUNT_IS_OVER.messageOf(MINIMUM_VALUE, MAXIMUM_VALUE));
        }
    }
}

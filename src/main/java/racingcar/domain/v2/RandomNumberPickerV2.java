package racingcar.domain.v2;

import java.util.Random;

public class RandomNumberPickerV2 {

    private static final int MIN_BOUND = 0;
    private static final int MAX_BOUND = 10;
    private static final Random RANDOM = new Random();

    public int pick() {
        return RANDOM.nextInt(MAX_BOUND - MIN_BOUND + 1) + MIN_BOUND;
    }
}

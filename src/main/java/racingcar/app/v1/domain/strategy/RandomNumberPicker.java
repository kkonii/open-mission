package racingcar.app.v1.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.common.NumberPickerBase;

public class RandomNumberPicker implements NumberPickerBase {

    private static final int MINIMUM = 0;
    private static final int MAXIMUM = 9;

    public int pick() {
        return Randoms.pickNumberInRange(MINIMUM, MAXIMUM);
    }
}

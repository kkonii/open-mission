package racingcar.domain.v2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.strategy.VehicleModel;
import racingcar.exception.RaceError;

public class VehicleTest {

    @ParameterizedTest
    @EnumSource(VehicleModel.class)
    void 차량_기종이_존재하면_검증을_통과한다(VehicleModel model) {
        //given
        String riderName = "임시이름";
        //then
        Assertions.assertDoesNotThrow(() -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"다", "다섯", "다섯글", "다섯글자", "다섯글자야"})
    void 이름의_길이제한_검증을_통과한다(String riderName) {
        //given
        VehicleModel model = VehicleModel.BUS;
        //then
        Assertions.assertDoesNotThrow(() -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void 공백값인_이름에_대해_예외를_발생한다(String riderName) {
        //given
        VehicleModel model = VehicleModel.BUS;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {",", "+", "=", "&", "/", "🤔"})
    void 숫자_한글_영어가_아닌_이름에_대해_예외를_발생한다(String invalidName) {
        //givne
        //given
        VehicleModel model = VehicleModel.TAXI;

        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> Vehicle.createOf(model.name(), invalidName),
                RaceError.NAME_IS_NOT_VALID_PATTERN.message());
    }
}

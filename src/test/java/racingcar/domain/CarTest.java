package racingcar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.strategy.VehicleModel;

public class CarTest {

    @ParameterizedTest
    @EnumSource(VehicleModel.class)
    void 차량_기종이_존재하면_검증을_통과한다(VehicleModel model) {
        //given
        String riderName = "임시이름";
        //then
        Assertions.assertDoesNotThrow(() -> Car.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"다", "다섯", "다섯글", "다섯글자", "다섯글자야"})
    void 이름의_길이제한_검증을_통과한다(String riderName) {
        //given
        VehicleModel model = VehicleModel.BUS;
        //then
        Assertions.assertDoesNotThrow(() -> Car.createOf(model.name(), riderName));
    }
}

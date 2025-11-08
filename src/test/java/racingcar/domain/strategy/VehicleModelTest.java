package racingcar.domain.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VehicleModelTest {

    @Test
    void 존재하지_않는_차량_기종에_대해_예외를_발생한다() {
        //when
        String modelName = "SHIP";
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> VehicleModel.findBy(modelName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"BUS", "TAXI", "FERRARI", "BIKE", "CAR"})
    void 입력받은_이름과_일치하는_차량을_반환한다(String modelName) {
        //when
        VehicleModel vehicleModel = VehicleModel.findBy(modelName);
        //then
        Assertions.assertEquals(vehicleModel.name(), modelName);
    }
}

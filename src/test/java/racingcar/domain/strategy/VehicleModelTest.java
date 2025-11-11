package racingcar.domain.strategy;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VehicleModelTest {

    @Test
    void 존재하지_않는_차량_기종에_대해_예외를_발생한다() {
        //when
        String modelName = "SHIP";
        Optional<VehicleModel> vehicleModel = VehicleModel.findBy(modelName);
        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> vehicleModel.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"BUS", "TAXI", "FERRARI", "BIKE", "CAR"})
    void 입력받은_이름과_일치하는_차량을_반환한다(String modelName) {
        //when
        VehicleModel vehicleModel = VehicleModel.findBy(modelName).get();
        //then
        Assertions.assertEquals(vehicleModel.name(), modelName);
    }

    @ParameterizedTest
    @MethodSource("racingcar.fixture.Provider#movablePointArguments")
    void 입력받은_숫자가_전진_가능한지_여부를_반환한다(VehicleModel model, int number) {
        Assertions.assertTrue(model.canMove(number));
    }
}

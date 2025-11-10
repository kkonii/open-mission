package racingcar.domain.v2;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VehiclesTest {

    @Test
    void 중복되는_차량_기종_값이_입력되면_예외를_발생한다() {
        //given
        String model = "BIKE";
        String name1 = "코끼리";
        String name2 = "끼리코";

        //when
        List<Vehicle> vehicles = List.of(Vehicle.createOf(model, name1), Vehicle.createOf(model, name2));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicles.ofUnique(vehicles));
    }

    @Test
    void 중복되는_이름_값이_입력되면_예외를_발생한다() {
        //given
        String modelName1 = "BIKE";
        String modelName2 = "BIKE";
        String rider1 = "코끼리";

        //when
        List<Vehicle> vehicles = List.of(Vehicle.createOf(modelName1, rider1), Vehicle.createOf(modelName2, rider1));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicles.ofUnique(vehicles));
    }
}

package racingcar.domain.v2;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VehiclesTest {

    @Test
    void 중복되는_차량값이_입력되면_예외를_발생한다() {
        //given
        String modelName = "BIKE";
        String rider1 = "코끼리";
        String rider2 = "끼리코";

        //when
        List<Vehicle> vehicles = List.of(Vehicle.createOf(modelName, rider1), Vehicle.createOf(modelName, rider2));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicles.ofUnique(vehicles));
    }
}

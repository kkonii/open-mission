package racingcar.fixture;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import racingcar.domain.strategy.VehicleModel;

public class Provider {

    public static Stream<Arguments> movablePointArguments() {
        return Stream.of(
                Arguments.of(VehicleModel.BIKE, 3, 1),
                Arguments.of(VehicleModel.CAR, 3, 1),
                Arguments.of(VehicleModel.FERRARI, 9, 2),
                Arguments.of(VehicleModel.TAXI, 5, 1),
                Arguments.of(VehicleModel.BUS, 8, 1)
        );
    }
}

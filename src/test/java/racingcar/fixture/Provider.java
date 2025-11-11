package racingcar.fixture;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import racingcar.domain.strategy.VehicleModel;

public class Provider {

    public static Stream<Arguments> movablePointArguments() {
        return Stream.of(
                Arguments.of(VehicleModel.BIKE, 3),
                Arguments.of(VehicleModel.CAR, 3),
                Arguments.of(VehicleModel.FERRARI, 9),
                Arguments.of(VehicleModel.TAXI, 5),
                Arguments.of(VehicleModel.BUS, 8)
        );
    }
}

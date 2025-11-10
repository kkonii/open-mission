package racingcar.domain.strategy;

import java.util.Arrays;
import java.util.Optional;

public enum VehicleModel {

    BIKE, CAR, FERRARI, TAXI, BUS;

    public static Optional<VehicleModel> findBy(String modelName) {
        return Arrays.stream(values())
                .filter(model -> model.name().equals(modelName))
                .findFirst();
    }
}

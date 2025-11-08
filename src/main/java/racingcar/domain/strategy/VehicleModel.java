package racingcar.domain.strategy;

import java.util.Arrays;
import racingcar.exception.RaceError;

public enum VehicleModel {

    BIKE, CAR, FERRARI, TAXI, BUS;

    public static VehicleModel findBy(String modelName) {
        return Arrays.stream(values())
                .filter(model -> model.name().equals(modelName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(RaceError.VEHICLE_MODEL_NOT_EXIST.message()));
    }
}

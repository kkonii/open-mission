package racingcar.domain.strategy;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum VehicleModel {

    BIKE((num) -> num <= 4),
    CAR((num) -> num == 3),
    FERRARI((num) -> num >= 8),
    TAXI((num) -> num % 2 == 1),
    BUS((num) -> num % 2 == 0);

    private final Predicate<Integer> movablePoint;

    VehicleModel(Predicate<Integer> movablePoint) {
        this.movablePoint = movablePoint;
    }

    public static Optional<VehicleModel> findBy(String modelName) {
        return Arrays.stream(values())
                .filter(model -> model.name().equals(modelName))
                .findFirst();
    }

    public boolean canMove(int number) {
        return this.movablePoint.test(number);
    }
}

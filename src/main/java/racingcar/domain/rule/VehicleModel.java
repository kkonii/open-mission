package racingcar.domain.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public enum VehicleModel {

    BIKE((num) -> num <= 4, 1),
    CAR((num) -> num == 3, 3),
    FERRARI((num) -> num >= 7, 1),
    TAXI((num) -> num % 2 == 1, 1),
    BUS((num) -> num % 3 == 0, 1);

    private final Predicate<Integer> movablePoint;
    private final int forward;

    VehicleModel(Predicate<Integer> movablePoint, int forward) {
        this.movablePoint = movablePoint;
        this.forward = forward;
    }

    public static Optional<VehicleModel> findBy(String modelName) {
        return Arrays.stream(values())
                .filter(model -> model.name().equals(modelName))
                .findFirst();
    }

    public boolean canMove(int number) {
        return this.movablePoint.test(number);
    }

    public static List<String> getNames() {
        return Arrays.stream(VehicleModel.values())
                .map(Enum::name)
                .toList();
    }

    public int getForward() {
        return forward;
    }
}

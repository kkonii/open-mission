package racingcar.domain.v2;

import java.util.Set;

public class Vehicles {

    private final Set<Vehicle> vehicles;

    private Vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public static Vehicles ofUnique(Set<Vehicle> vehicles) {
        return new Vehicles(vehicles);
    }
}

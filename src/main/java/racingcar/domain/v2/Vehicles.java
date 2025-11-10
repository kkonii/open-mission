package racingcar.domain.v2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.domain.strategy.VehicleModel;
import racingcar.exception.RaceError;

public class Vehicles {

    private final Set<Vehicle> vehicles;

    private Vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public static Vehicles ofUnique(List<Vehicle> vehicles) {
        validateUniqueModel(vehicles);
        validateUniqueName(vehicles);
        Set<Vehicle> uniqueVehicles = new HashSet<>(vehicles);

        return new Vehicles(uniqueVehicles);
    }

    private static void validateUniqueModel(List<Vehicle> vehicles) {
        Set<VehicleModel> uniqueModels = new HashSet<>();

        for (Vehicle vehicle : vehicles) {
            if (!uniqueModels.add(vehicle.getModel())) {
                throw new IllegalArgumentException(RaceError.VEHICLE_MODELS_ARE_NOT_UNIQUE.message());
            }
        }
    }

    private static void validateUniqueName(List<Vehicle> vehicles) {
        Set<String> uniqueModels = new HashSet<>();

        for (Vehicle vehicle : vehicles) {
            if (!uniqueModels.add(vehicle.getName())) {
                throw new IllegalArgumentException(RaceError.NAMES_ARE_NOT_UNIQUE.message());
            }
        }
    }
}

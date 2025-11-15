package racingcar.app.v2.domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntSupplier;
import racingcar.app.v2.domain.rule.VehicleModel;
import racingcar.exception.RaceError;

public class Vehicles {

    private final Set<Vehicle> vehicles;

    private Vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public static Vehicles ofUnique(List<Vehicle> vehicles) {
        validateUniqueModel(vehicles);
        validateUniqueName(vehicles);
        Set<Vehicle> uniqueVehicles = new LinkedHashSet<>(vehicles);

        return new Vehicles(uniqueVehicles);
    }

    private static void validateUniqueModel(List<Vehicle> vehicles) {
        Set<VehicleModel> uniqueModels = new HashSet<>();

        for (Vehicle vehicle : vehicles) {
            if (!uniqueModels.add(vehicle.getModel())) {
                throw new IllegalArgumentException(RaceError.MODELS_ARE_NOT_UNIQUE.message());
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

    public void move(IntSupplier picked) {
        vehicles.forEach(vehicle -> {
            int number = picked.getAsInt();
            vehicle.move(number);
        });
    }

    public List<Vehicle> sortByDistance() {
        return vehicles.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }
}

package racingcar.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RaceStatistics {

    public Map<Integer, List<Vehicle>> calculateRanksOf(Vehicles vehicles) {
        List<Vehicle> sortedCars = vehicles.sortByDistance();
        Map<Integer, List<Vehicle>> statistics = new LinkedHashMap<>();

        int rank = 1;
        int currentDistance = sortedCars.getFirst().getDistance();

        statistics.computeIfAbsent(rank, key -> new ArrayList<>()).add(sortedCars.getFirst());

        for (int i = 1; i < sortedCars.size(); i++) {
            Vehicle car = sortedCars.get(i);

            if (!car.movedFor(currentDistance)) {
                rank = i + 1;
                currentDistance = car.getDistance();
            }
            statistics.computeIfAbsent(rank, key -> new ArrayList<>()).add(car);
        }

        return statistics;
    }
}

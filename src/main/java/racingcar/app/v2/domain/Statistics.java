package racingcar.app.v2.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Statistics {

    public Map<Integer, List<Vehicle>> calculateRanksOf(Vehicles vehicles) {
        List<Vehicle> sortedCars = vehicles.sortByDistance();
        Map<Integer, List<Vehicle>> statistics = new LinkedHashMap<>();

        int rank = 1;
        int currentDistance = sortedCars.getFirst().getDistance();

        statistics.put(rank, new ArrayList<>());
        statistics.get(rank).add(sortedCars.getFirst());

        for (int i = 1; i < sortedCars.size(); i++) {
            Vehicle car = sortedCars.get(i);
            int distance = car.getDistance();

            if (distance != currentDistance) {
                rank = i + 1;
                currentDistance = distance;
            }
            List<Vehicle> list = statistics.get(rank);

            if (list == null) {
                list = new ArrayList<>();
                statistics.put(rank, list);
            }
            list.add(car);
        }

        return statistics;
    }
}

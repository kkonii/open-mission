package racingcar.app.v2.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.app.v2.domain.rule.RandomNumberPicker;
import racingcar.app.v2.dto.AttributeDto;
import racingcar.app.v2.dto.RoundResultDto;

public class RaceProcessor {

    private final RandomNumberPicker randomNumberPicker;

    public RaceProcessor(RandomNumberPicker randomNumberPicker) {
        this.randomNumberPicker = randomNumberPicker;
    }

    public Vehicles registerCarsFrom(List<AttributeDto> attributes) {
        return Vehicles.ofUnique(attributes.stream()
                .map(attribute -> Vehicle.createOf(attribute.modelName(), attribute.riderName()))
                .toList());
    }

    public List<List<RoundResultDto>> runAllRound(int tryCount, Vehicles vehicles) {
        List<List<RoundResultDto>> allRoundResult = new ArrayList<>();

        for (int i = 0; i < tryCount; i++) {
            allRoundResult.add(runOneRound(vehicles));
        }

        return allRoundResult;
    }

    public List<RoundResultDto> runOneRound(Vehicles vehicles) {
        vehicles.move(randomNumberPicker::pick);

        return vehicles.getVehicles()
                .stream()
                .map(vehicle -> new RoundResultDto(vehicle.getName(), vehicle.getDistance()))
                .toList();
    }

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

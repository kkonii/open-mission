package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.dto.AttributeDto;
import racingcar.dto.FinalResultDto;
import racingcar.dto.RoundResultDto;

public class RaceProcessor {

    private static final int ROUNDS_PER_RACE = 6;

    private final RandomNumberPicker randomNumberPicker;
    private final Statistics statistics;

    public RaceProcessor(RandomNumberPicker randomNumberPicker, Statistics statistics) {
        this.randomNumberPicker = randomNumberPicker;
        this.statistics = statistics;
    }

    public Vehicles registerCarsFrom(List<AttributeDto> attributes) {
        return Vehicles.ofUnique(attributes.stream()
                .map(attribute -> Vehicle.createOf(attribute.modelName(), attribute.riderName()))
                .toList());
    }

    public List<List<RoundResultDto>> runAllRound(Vehicles vehicles) {
        List<List<RoundResultDto>> allRoundResult = new ArrayList<>();

        for (int i = 0; i < ROUNDS_PER_RACE; i++) {
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

    public List<FinalResultDto> statisticsOf(Vehicles vehicles) {
        Map<Integer, List<Vehicle>> statisticResult = statistics.calculateRanksOf(vehicles);

        return statisticResult.entrySet()
                .stream()
                .map(entry -> new FinalResultDto(entry.getKey(), namesOf(entry.getValue())))
                .toList();
    }

    private List<String> namesOf(List<Vehicle> values) {
        return values
                .stream()
                .map(Vehicle::getName)
                .toList();
    }
}

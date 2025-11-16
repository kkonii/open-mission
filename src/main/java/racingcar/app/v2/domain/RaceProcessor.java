package racingcar.app.v2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import racingcar.app.v2.domain.rule.RandomNumberPicker;
import racingcar.app.v2.dto.AttributeDto;
import racingcar.app.v2.dto.RoundResultDto;

public class RaceProcessor {

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

    public Map<Integer, List<Vehicle>> statisticsOf(Vehicles vehicles) {
        return statistics.calculateRanksOf(vehicles);
    }
}

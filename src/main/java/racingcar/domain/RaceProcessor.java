package racingcar.domain;

import java.util.List;
import java.util.Map;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.dto.AttributeDto;

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

    //자동차 이름 : 달린 거리
    public void runRace(Vehicles vehicles) {
        for (int i = 0; i < ROUNDS_PER_RACE; i++) {
            vehicles.move(randomNumberPicker::pick);
        }
    }

    // 순위: 이름 리스트
    public Map<Integer, List<Vehicle>> statisticsOf(Vehicles vehicles) {
        return statistics.calculateRanksOf(vehicles);
    }

    //순위가 1위인 것만 걸러냄
    public List<Vehicle> findWinners(Map<Integer, List<Vehicle>> ranks) {
        return List.copyOf(ranks.getOrDefault(1, List.of()));
    }
}

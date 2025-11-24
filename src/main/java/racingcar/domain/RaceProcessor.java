package racingcar.domain;

import java.util.List;
import java.util.Map;
import racingcar.domain.rule.RandomNumberPicker;

public class RaceProcessor {

    private static final int FIXED_ROUNDS = 6;

    private final RandomNumberPicker randomNumberPicker;
    private final RaceStatistics raceStatistics;

    public RaceProcessor(RandomNumberPicker randomNumberPicker, RaceStatistics raceStatistics) {
        this.randomNumberPicker = randomNumberPicker;
        this.raceStatistics = raceStatistics;
    }

    //자동차 이름 : 달린 거리
    public void runRace(Vehicles vehicles) {
        vehicles.resetAll();
        for (int i = 0; i < FIXED_ROUNDS; i++) {
            vehicles.move(randomNumberPicker::pick);
        }
    }

    // 순위: 이름 리스트
    public Map<Integer, List<Vehicle>> statisticsOf(Vehicles vehicles) {
        return raceStatistics.calculateRanksOf(vehicles);
    }

    //순위가 1위인 것만 걸러냄
    public List<Vehicle> findWinners(Map<Integer, List<Vehicle>> ranks) {
        return List.copyOf(ranks.getOrDefault(1, List.of()));
    }
}

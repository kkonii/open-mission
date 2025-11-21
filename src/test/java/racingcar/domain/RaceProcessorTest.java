package racingcar.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.domain.rule.VehicleModel;

public class RaceProcessorTest {

    @Test
    void 계산된_등수에서_우승자를_찾아내_반환한다() {
        //given
        RandomNumberPicker randomNumberPicker = new RandomNumberPicker();
        Statistics statistics = new Statistics();
        RaceProcessor raceProcessor = new RaceProcessor(randomNumberPicker, statistics);

        Map<Integer, List<Vehicle>> ranks = new LinkedHashMap<>();

        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.BUS.name(), "우테코");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.TAXI.name(), "포비");
        Vehicle vehicle3 = Vehicle.createOf(VehicleModel.CAR.name(), "경연");

        ranks.put(1, List.of(vehicle1, vehicle2));
        ranks.put(3, List.of(vehicle3));

        // when
        List<Vehicle> winners = raceProcessor.findWinners(ranks);
        // then
        Assertions.assertThat(winners).containsExactly(vehicle1, vehicle2);
    }
}

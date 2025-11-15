package racingcar.v2.domain;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.app.v2.domain.RaceProcessor;
import racingcar.app.v2.domain.Vehicle;
import racingcar.app.v2.domain.Vehicles;
import racingcar.app.v2.domain.rule.RandomNumberPicker;
import racingcar.app.v2.domain.rule.VehicleModel;

public class RaceProcessorTest {
    @Test
    void 자동차들의_경주_결과의_순위를_계산한다() {
        //given
        RandomNumberPicker randomNumberPicker = new RandomNumberPicker();
        RaceProcessor raceProcessor = new RaceProcessor(randomNumberPicker);

        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.BUS.name(), "스이카");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.TAXI.name(), "이루카");
        Vehicle vehicle3 = Vehicle.createOf(VehicleModel.CAR.name(), "조우");
        Vehicles vehicles = Vehicles.ofUnique(List.of(vehicle1, vehicle2, vehicle3));
        //when
        //2번 이동 (조건: 짝수번호)
        vehicle1.move(8);
        vehicle1.move(8);
        //0번 이동 (조건: 홀수번호)
        vehicle2.move(8);
        //1번 이동 (조건: 3)
        vehicle3.move(3);

        Map<Integer, List<Vehicle>> result = raceProcessor.calculateRanksOf(vehicles);

        //then
        Assertions.assertTrue(result.get(1).contains(vehicle1));
        Assertions.assertTrue(result.get(2).contains(vehicle3));
        Assertions.assertTrue(result.get(3).contains(vehicle2));
    }
}

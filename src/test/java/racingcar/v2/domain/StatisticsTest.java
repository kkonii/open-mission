package racingcar.v2.domain;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.app.v2.domain.Statistics;
import racingcar.app.v2.domain.Vehicle;
import racingcar.app.v2.domain.Vehicles;
import racingcar.app.v2.domain.rule.VehicleModel;

public class StatisticsTest {

    @Test
    void 자동차들의_경주_결과의_순위를_계산한다() {
        //given
        Statistics statistics = new Statistics();

        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.BUS.name(), "스이카");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.TAXI.name(), "이루카");
        Vehicle vehicle3 = Vehicle.createOf(VehicleModel.CAR.name(), "조우");
        Vehicle vehicle4 = Vehicle.createOf(VehicleModel.FERRARI.name(), "마네키");
        Vehicles vehicles = Vehicles.ofUnique(List.of(vehicle1, vehicle2, vehicle3, vehicle4));
        //when
        //2번 이동 (조건: 짝수번호)
        vehicle1.move(8);
        vehicle1.move(8);
        //0번 이동 (조건: 홀수번호)
        vehicle2.move(8);
        //1번 이동 (조건: 3)
        vehicle3.move(3);
        // 2번 이동 (조건: 8 이상)
        vehicle4.move(9);

        Map<Integer, List<Vehicle>> result = statistics.calculateRanksOf(vehicles);

        //then
        org.assertj.core.api.Assertions.assertThat(result.get(1)).containsExactly(vehicle1, vehicle4);
        Assertions.assertTrue(result.get(3).contains(vehicle3));
        Assertions.assertTrue(result.get(4).contains(vehicle2));
    }
}

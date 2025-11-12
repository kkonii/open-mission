package racingcar.domain.v2;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.VehicleModel;

public class VehiclesTest {

    @Test
    void 중복되는_차량_기종_값이_입력되면_예외를_발생한다() {
        //given
        String model = "BIKE";
        String name1 = "코끼리";
        String name2 = "끼리코";

        //when
        List<Vehicle> vehicles = List.of(Vehicle.createOf(model, name1), Vehicle.createOf(model, name2));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicles.ofUnique(vehicles));
    }

    @Test
    void 중복되는_이름_값이_입력되면_예외를_발생한다() {
        //given
        String modelName1 = "BIKE";
        String modelName2 = "BIKE";
        String rider1 = "코끼리";

        //when
        List<Vehicle> vehicles = List.of(Vehicle.createOf(modelName1, rider1), Vehicle.createOf(modelName2, rider1));

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicles.ofUnique(vehicles));
    }

    @Test
    void 입력된_순서를_보장하여_객체를_생성한다() {
        //given
        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.FERRARI.name(), "페가수스");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.BUS.name(), "버스기사");
        Vehicle vehicle3 = Vehicle.createOf(VehicleModel.TAXI.name(), "택시기사");
        Vehicle vehicle4 = Vehicle.createOf(VehicleModel.CAR.name(), "자동차");
        //when
        Vehicles vehicles = Vehicles.ofUnique(List.of(vehicle1, vehicle2, vehicle3, vehicle4));
        //then
        org.assertj.core.api.Assertions.assertThat(vehicles.getVehicles())
                .containsExactly(vehicle1, vehicle2, vehicle3, vehicle4);
    }

    @Test
    void 가장_멀리_이동한_거리값을_찾아_반환한다() {
        //given
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
        int maxDistance = vehicles.findMaxDistance();
        //then
        Assertions.assertEquals(maxDistance, 2);
    }
}

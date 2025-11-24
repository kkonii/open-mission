package racingcar.domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.rule.VehicleModel;
import racingcar.exception.RaceError;

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
    void 달린_거리_순으로_정렬하여_반환한다() {
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
        List<Vehicle> winner = vehicles.sortByDistance();
        //then
        org.assertj.core.api.Assertions.assertThat(winner).containsExactly(vehicle1, vehicle3, vehicle2);
    }

    @Test
    void 입력받은_예측_우승자의_이름이_존재하지_않을_경우_예외를_반환한다() {
        //given
        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.TAXI.name(), "푸딩");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.BUS.name(), "토스트");
        Vehicles vehicles = Vehicles.ofUnique(List.of(vehicle1, vehicle2));
        //when
        String notPresentName = "루피";
        //then
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> vehicles.validateContainsName(notPresentName), RaceError.NOT_PRESENT_NAME.message());
    }

    @Test
    void 모든_자동차들의_전진_거리를_리셋한다() {
        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.BIKE.name(), "우테코");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.CAR.name(), "포비");
        Vehicles vehicles = Vehicles.ofUnique(List.of(vehicle1, vehicle2));

        //when
        vehicle1.move(4);
        vehicle2.move(3);

        //then
        Assertions.assertEquals(1, vehicle1.getDistance());
        Assertions.assertEquals(1, vehicle2.getDistance());

        //when
        vehicles.resetAll();
        //then
        Assertions.assertEquals(0, vehicle1.getDistance());
        Assertions.assertEquals(0, vehicle2.getDistance());
    }
}

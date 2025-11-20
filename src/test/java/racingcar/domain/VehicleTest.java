package racingcar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.rule.VehicleModel;
import racingcar.exception.RaceError;

public class VehicleTest {

    @Test
    void 존재하지_않는_차량_기종은_예외를_발생한다() {
        //when
        String noExistModel = "SCOOTER";
        //then
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> Vehicle.createOf(noExistModel, "이름"));
    }

    @ParameterizedTest
    @EnumSource(VehicleModel.class)
    void 차량_기종이_존재하면_검증을_통과한다(VehicleModel model) {
        //given
        String riderName = "임시이름";
        //then
        Assertions.assertDoesNotThrow(() -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"다", "다섯", "다섯글", "다섯글자", "다섯글자야"})
    void 이름의_길이제한_검증을_통과한다(String riderName) {
        //given
        VehicleModel model = VehicleModel.BUS;
        //then
        Assertions.assertDoesNotThrow(() -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void 공백값인_이름에_대해_예외를_발생한다(String riderName) {
        //given
        VehicleModel model = VehicleModel.BUS;
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> Vehicle.createOf(model.name(), riderName));
    }

    @ParameterizedTest
    @ValueSource(strings = {",", "+", "=", "&", "/", "🤔"})
    void 숫자_한글_영어가_아닌_이름에_대해_예외를_발생한다(String invalidName) {
        //given
        VehicleModel model = VehicleModel.TAXI;

        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> Vehicle.createOf(model.name(), invalidName),
                RaceError.NAME_IS_NOT_VALID_PATTERN.message());
    }

    @ParameterizedTest
    @MethodSource("racingcar.fixture.Provider#movablePointArguments")
    void 전진_가능한_숫자가_나오면_전진한다(VehicleModel model, int number, int moveForward) {
        //given
        String name = "오렌지";
        //when
        Vehicle vehicle = Vehicle.createOf(model.name(), name);
        vehicle.move(number);
        //then
        Assertions.assertEquals(vehicle.getDistance(), moveForward);
    }

    @Test
    void 이동한_거리값을_기준으로_동등성을_판단한다() {
        //given
        Vehicle vehicle1 = Vehicle.createOf(VehicleModel.BUS.name(), "스이카");
        Vehicle vehicle2 = Vehicle.createOf(VehicleModel.TAXI.name(), "이루카");
        //when
        vehicle1.move(8);
        vehicle2.move(7);
        //then
        Assertions.assertEquals(vehicle1.compareTo(vehicle2), 0);
    }

    @Test
    void 외부에서_받은_거리와_동일하게_이동했는지_판단한다() {
        //given
        Vehicle vehicle = Vehicle.createOf(VehicleModel.BIKE.name(), "카미");
        //when
        vehicle.move(2);
        int distance = 1;
        //then
        Assertions.assertTrue(vehicle.movedFor(distance));
    }

    @Test
    void 이름값의_동등성을_판별한다() {
        //given
        Vehicle vehicle = Vehicle.createOf(VehicleModel.TAXI.name(), "캐럿");
        //when
        String someName = "캐럿";
        //then
        Assertions.assertTrue(vehicle.equals(someName));
    }
}

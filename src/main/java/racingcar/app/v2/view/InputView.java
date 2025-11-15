package racingcar.app.v2.view;

import racingcar.app.v2.domain.rule.VehicleModel;
import racingcar.common.ConsoleScanner;

public class InputView {

    private static final String CARS_ARE = "경주 차량 목록";
    private static final String ENTER_CAR_AND_NAMES_TO_RACE = "경주할 차량와 이름을 함께 입력하세요. (예시: BIKE-강냉이,CAR-돌고래)";
    private static final String ENTER_COUNT_TO_TRY = "시도할 횟수는 몇 회인가요?";

    public void guideRacingCars() {
        System.out.println(CARS_ARE);
        System.out.println(VehicleModel.getNames());
        System.out.println();
    }

    public String getNameInputs() {
        System.out.println(ENTER_CAR_AND_NAMES_TO_RACE);

        return ConsoleScanner.readLine();
    }

    public String getCountInput() {
        System.out.println(ENTER_COUNT_TO_TRY);

        return ConsoleScanner.readLine();
    }
}

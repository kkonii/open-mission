package racingcar.view;

import racingcar.util.ConsoleScanner;

public class InputView {

    private static final String ENTER_NAMES_TO_RACE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ENTER_COUNT_TO_TRY = "시도할 횟수는 몇 회인가요?";

    public String getNameInputs() {
        System.out.println(ENTER_NAMES_TO_RACE);

        return ConsoleScanner.readLine();
    }

    public String getCountInput() {
        System.out.println(ENTER_COUNT_TO_TRY);

        return ConsoleScanner.readLine();
    }
}

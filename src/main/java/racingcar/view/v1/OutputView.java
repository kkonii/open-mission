package racingcar.view.v1;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.CarDto;

public class OutputView {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String MOVE_FORWARD = "-";
    private static final String ROUND_RESULT_OF = "%s : %s";
    private static final String JOINING_DELIMITER = ", ";
    private static final String WINNER_IS = "최종 우승자 : %s";

    public void printHeader() {
        System.out.println();
        System.out.println(RESULT_HEADER);
    }

    public void printResultOf(List<CarDto> cars) {
        for (CarDto car : cars) {
            System.out.printf(ROUND_RESULT_OF, car.name(), MOVE_FORWARD.repeat(car.distance()));
            System.out.println();
        }
        System.out.println();
    }

    public void printNamesOf(List<CarDto> winners) {
        String joinedNames = winners.stream()
                .map(CarDto::name)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf(WINNER_IS, joinedNames);
        System.out.println();
    }
}

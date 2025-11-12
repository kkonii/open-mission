package racingcar.view.v2;

import java.util.List;
import racingcar.dto.RoundResultDto;

public class OutputViewV2 {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String MOVE_FORWARD = "-";
    private static final String ROUND_RESULT_OF = "%s : %s";

    public void printHeader() {
        System.out.println();
        System.out.println(RESULT_HEADER);
    }

    public void printResultOf(List<RoundResultDto> results) {
        for (RoundResultDto result : results) {
            System.out.printf(ROUND_RESULT_OF, result.name(), MOVE_FORWARD.repeat(result.distance()));
            System.out.println();
        }
        System.out.println();
    }
}

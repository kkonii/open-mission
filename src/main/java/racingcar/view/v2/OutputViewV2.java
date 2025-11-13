package racingcar.view.v2;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RoundResultDto;
import racingcar.dto.WinnerDto;

public class OutputViewV2 {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String MOVE_FORWARD = "-";
    private static final String ROUND_RESULT_OF = "%s : %s";
    private static final String JOINING_DELIMITER = ", ";
    private static final String WINNERS_ARE = "최종 우승자 : %s";

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

    public void printNamesOf(List<WinnerDto> winners) {
        String joinedNames = winners.stream()
                .map(WinnerDto::name)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf(WINNERS_ARE, joinedNames);
        System.out.println();
    }
}

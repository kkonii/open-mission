package racingcar.app.v2.view;

import java.util.List;
import racingcar.app.v2.dto.FinalResultDto;
import racingcar.app.v2.dto.RoundResultDto;

public class OutputView {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String MOVE_FORWARD = "-";
    private static final String ROUND_RESULT_OF = "%s : %s";
    private static final String FINAL_RANK_HEADER = "최종 순위 발표";
    private static final String JOINING_DELIMITER = ", ";
    private static final String RANK_IS = "%d등 : %s";

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

    public void printRankOf(List<FinalResultDto> results) {
        System.out.println(FINAL_RANK_HEADER);
        for (FinalResultDto result : results) {
            String joinedNames = String.join(JOINING_DELIMITER, result.names());

            System.out.printf(RANK_IS, result.rank(), joinedNames);
            System.out.println();
        }
    }
}

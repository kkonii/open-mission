package racingcar.view;

import java.util.List;
import racingcar.dto.BettingRoundDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.view.message.MessageKey;
import racingcar.view.message.Provider;

public class OutputView {

    private final Provider provider;

    private static final String MOVE_FORWARD = "-";
    private static final String ROUND_RESULT_OF = "%s : %s";
    private static final String JOINING_DELIMITER = ", ";

    public OutputView(Provider provider) {
        this.provider = provider;
    }

    public void printHeader() {
        System.out.println();
        System.out.println(provider.messageOf(MessageKey.ROUND_RESULT_HEADER));
    }

    //RaceResult
    public void printResultOf(List<RaceResultDto> raceResults) {
        for (RaceResultDto result : raceResults) {
            System.out.printf(ROUND_RESULT_OF, result.name(), MOVE_FORWARD.repeat(result.distance()));
            System.out.println();
        }
        System.out.println();
    }

    //RankResult
    public void printRankOf(List<RankResultDto> rankResults) {
        System.out.println(provider.messageOf(MessageKey.FINAL_RANK_HEADER));
        for (RankResultDto result : rankResults) {
            String joinedNames = String.join(JOINING_DELIMITER, result.names());

            String rankIs = provider.messageOf(MessageKey.RANK_IS);
            System.out.printf(rankIs, result.rank(), joinedNames);
            System.out.println();
        }
    }

    public void printBettingResult(BettingRoundDto bettingRound) {
        System.out.println(formatOf(MessageKey.WINNER_IS, String.join(JOINING_DELIMITER, bettingRound.winnerNames())));
        System.out.println(formatOf(MessageKey.PREDICTED_WINNER_IS, bettingRound.predictedName()));
        if (bettingRound.isSuccess()) {
            System.out.println(provider.messageOf(MessageKey.BETTING_SUCCESS));
        }
        System.out.println(provider.messageOf(MessageKey.BETTING_FAIL));
    }

    private String formatOf(MessageKey key, String value) {
        return String.format(provider.messageOf(key), value);
    }
}

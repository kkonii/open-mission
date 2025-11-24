package racingcar.view;

import java.util.List;
import racingcar.dto.BettingResultDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.dto.WinRateDto;
import racingcar.view.message.MessageKey;
import racingcar.view.message.Provider;

public class OutputView {

    private final Provider provider;

    private static final String ROUND_RESULT_OF = "%-5s  |  %s";
    private static final String RANK_RESULT_OF = "%-3s  |  %s";
    private static final String JOINING_DELIMITER = ", ";

    public OutputView(Provider provider) {
        this.provider = provider;
    }

    public void printHeader() {
        System.out.println();
        printWithBanner(provider.messageOf(MessageKey.ROUND_RESULT_HEADER));
    }

    //RaceResult
    public void printResultOf(List<RaceResultDto> raceResults) {
        System.out.println(Formatter.distanceScale());
        for (RaceResultDto result : raceResults) {
            System.out.printf(ROUND_RESULT_OF, result.name(), Formatter.distanceBlock(result.distance()));
            System.out.println();
        }
        System.out.println();
    }

    //RankResult
    public void printRankOf(List<RankResultDto> rankResults) {
        printWithBanner(provider.messageOf(MessageKey.FINAL_RANK_HEADER));
        for (RankResultDto result : rankResults) {
            String joinedNames = String.join(JOINING_DELIMITER, result.names());

            String rank = formatOf(MessageKey.RANK_IS, result.rank());
            System.out.printf(RANK_RESULT_OF, rank, joinedNames);
            System.out.println();
        }
        System.out.println(Formatter.bannerLine());
    }

    private void printWithBanner(String message) {
        System.out.println(Formatter.banner(message));
    }

    public void printBettingResult(BettingResultDto bettingRound) {
        System.out.println(formatOf(MessageKey.WINNER_IS, String.join(JOINING_DELIMITER, bettingRound.winnerNames())));
        if (bettingRound.isSuccess()) {
            System.out.println(provider.messageOf(MessageKey.BETTING_SUCCESS));
            return;
        }
        System.out.println(provider.messageOf(MessageKey.BETTING_FAIL));
    }

    public void printWinRate(WinRateDto winRate) {
        System.out.println();
        printWithBanner(provider.messageOf(MessageKey.WIN_RATE_HEADER));
        System.out.println(formatOf(MessageKey.WIN_RATE, winRate.value()));
    }

    private <T> String formatOf(MessageKey key, T value) {
        return String.format(provider.messageOf(key), value);
    }
}

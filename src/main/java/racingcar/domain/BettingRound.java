package racingcar.domain;

import java.util.List;

public record BettingRound(String predictedName, List<String> winnerNames) {

    public boolean isSuccess() {
        return winnerNames.stream()
                .anyMatch(winner -> winner.equals(predictedName));
    }
}

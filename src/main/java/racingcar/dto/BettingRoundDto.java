package racingcar.dto;

import java.util.List;

public record BettingRoundDto(String predictedName, List<String> winnerNames, boolean isSuccess) {
}

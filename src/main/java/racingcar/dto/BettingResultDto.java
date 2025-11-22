package racingcar.dto;

import java.util.List;

public record BettingResultDto(String predictedName, List<String> winnerNames, boolean isSuccess) {
}

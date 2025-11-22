package racingcar.dto;

import java.util.List;

public record RoundResultDto(List<RaceResultDto> raceResults,
                             List<RankResultDto> rankResults,
                             BettingRoundDto bettingResult) {
}

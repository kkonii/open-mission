package racingcar.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.Statistics;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.dto.RankResultDto;

public class RaceProcessorTest {

    @Test
    void 계산된_등수에서_우승자를_찾아내_반환한다() {
        //given
        RankResultDto winner = new RankResultDto(1, List.of("우테코", "경연"));
        RankResultDto second = new RankResultDto(2, List.of("포비"));
        List<RankResultDto> results = List.of(winner, second);

        RandomNumberPicker randomNumberPicker = new RandomNumberPicker();
        Statistics statistics = new Statistics();
        RaceProcessor raceProcessor = new RaceProcessor(randomNumberPicker, statistics);
        // when
        List<String> winners = raceProcessor.findWinners(results);
        // then
        Assertions.assertThat(winners).containsExactly("우테코", "경연");
    }
}

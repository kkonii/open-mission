package racingcar.service;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.BettingRound;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Statistics;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.dto.WinRateDto;
import racingcar.repository.BettingRepository;
import racingcar.repository.BettingRoundRepository;

public class BettingServiceTest {

    static class EmptyBettingRoundRepository implements BettingRepository {

        @Override
        public void save(BettingRound round) {
        }

        @Override
        public List<BettingRound> findAll() {
            return Collections.emptyList();
        }
    }

    @Test
    void 승률을_계산하여_반환한다() {
        // given
        RandomNumberPicker numberPicker = new RandomNumberPicker();
        Statistics statistics = new Statistics();
        BettingRepository repository = new BettingRoundRepository();
        RaceProcessor raceProcessor = new RaceProcessor(numberPicker, statistics);

        //배팅 성공
        repository.save(new BettingRound("조로", List.of("루피", "조로")));
        //배팅 실패
        repository.save(new BettingRound("조로", List.of("루피", "나미")));

        BettingService service = new BettingService(repository, raceProcessor);
        // when
        double expectedRate = 50.0;
        WinRateDto winRate = service.calculateWinRate();

        // then
        Assertions.assertThat(winRate.value()).isEqualTo(expectedRate);
    }

    @Test
    void 우승자가_없을_경우_0으로_계산_반환한다() {
        RandomNumberPicker numberPicker = new RandomNumberPicker();
        Statistics statistics = new Statistics();
        BettingRepository repository = new EmptyBettingRoundRepository();
        RaceProcessor raceProcessor = new RaceProcessor(numberPicker, statistics);

        BettingService service = new BettingService(repository, raceProcessor);

        // when
        double expectedRate = 0.0;
        WinRateDto winRate = service.calculateWinRate();

        // then
        Assertions.assertThat(winRate.value()).isEqualTo(expectedRate);
    }
}

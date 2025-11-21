package racingcar.service;

import java.util.List;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicles;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.repository.BettingRoundRepository;

public class BettingService {

    private final BettingRoundRepository roundRepository;
    private final RaceProcessor raceProcessor;

    public BettingService(BettingRoundRepository roundRepository, RaceProcessor raceProcessor) {
        this.roundRepository = roundRepository;
        this.raceProcessor = raceProcessor;
    }

    public void playOneRound(Vehicles vehicles) {
        List<RaceResultDto> finishedRace = raceProcessor.runRace(vehicles);
        List<RankResultDto> ranksOfRace = raceProcessor.statisticsOf(vehicles);
        List<String> winners = raceProcessor.findWinners(ranksOfRace);
    }
}

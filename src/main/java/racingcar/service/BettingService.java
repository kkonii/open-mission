package racingcar.service;

import java.util.List;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicles;
import racingcar.domain.vo.PredictedWinner;
import racingcar.dto.BettingRoundDto;
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

    public List<RaceResultDto> playOneRound(Vehicles vehicles, PredictedWinner predictedWinner) {
        List<RaceResultDto> finishedRace = raceProcessor.runRace(vehicles);
        List<RankResultDto> ranksOfRace = raceProcessor.statisticsOf(vehicles);
        List<String> winners = raceProcessor.findWinners(ranksOfRace);
        boolean isSuccess = winners.contains(predictedWinner.name());

        roundRepository.save(new BettingRoundDto(predictedWinner.name(), winners, isSuccess));

        return finishedRace;
    }
}

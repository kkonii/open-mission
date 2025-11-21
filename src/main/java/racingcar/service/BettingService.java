package racingcar.service;

import java.util.List;
import java.util.Map;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicle;
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
        raceProcessor.runRace(vehicles);
        Map<Integer, List<Vehicle>> ranks = raceProcessor.statisticsOf(vehicles);
        List<Vehicle> winners = raceProcessor.findWinners(ranks);
        boolean isSuccess = matchNameOf(predictedWinner, winners);

        BettingRoundDto bettingRound = new BettingRoundDto(predictedWinner.name(), namesOf(winners), isSuccess);
        roundRepository.save(bettingRound);

        return mapToRaceResultDto(vehicles);
    }

    private boolean matchNameOf(PredictedWinner predictedWinner, List<Vehicle> winners) {
        return winners.stream()
                .anyMatch(winner -> winner.equals(predictedWinner.name()));
    }

    public List<RaceResultDto> mapToRaceResultDto(Vehicles vehicles) {
        return vehicles.getVehicles()
                .stream()
                .map(vehicle -> new RaceResultDto(vehicle.getName(), vehicle.getDistance()))
                .toList();
    }

    public List<RankResultDto> mapToRankDto(Map<Integer, List<Vehicle>> rankResult) {
        return rankResult.entrySet()
                .stream()
                .map(entry -> new RankResultDto(entry.getKey(), namesOf(entry.getValue())))
                .toList();
    }

    private List<String> namesOf(List<Vehicle> values) {
        return values
                .stream()
                .map(Vehicle::getName)
                .toList();
    }


}
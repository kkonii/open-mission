package racingcar.service;

import java.util.List;
import java.util.Map;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicle;
import racingcar.domain.Vehicles;
import racingcar.domain.vo.PredictedWinner;
import racingcar.dto.AttributeDto;
import racingcar.dto.BettingRoundDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.dto.RoundResultDto;
import racingcar.repository.BettingRoundRepository;

public class BettingService {

    private final BettingRoundRepository roundRepository;
    private final RaceProcessor raceProcessor;

    public BettingService(BettingRoundRepository roundRepository, RaceProcessor raceProcessor) {
        this.roundRepository = roundRepository;
        this.raceProcessor = raceProcessor;
    }

    public Vehicles registerCarsFrom(List<AttributeDto> attributes) {
        List<Vehicle> vehicles = attributes.stream()
                .map(attr -> Vehicle.createOf(attr.modelName(), attr.riderName()))
                .toList();

        return Vehicles.ofUnique(vehicles);
    }

    public RoundResultDto playOneRound(PredictedWinner predictedWinner, Vehicles vehicles) {
        raceProcessor.runRace(vehicles);
        Map<Integer, List<Vehicle>> ranks = raceProcessor.statisticsOf(vehicles);
        List<Vehicle> winners = raceProcessor.findWinners(ranks);
        boolean isSuccess = matchNameOf(predictedWinner, winners);

        BettingRoundDto bettingRound = new BettingRoundDto(predictedWinner.name(), namesOf(winners), isSuccess);
        roundRepository.save(bettingRound);

        return new RoundResultDto(mapToRaceResultDto(vehicles), mapToRankDto(ranks), bettingRound);
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
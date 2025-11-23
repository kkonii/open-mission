package racingcar.service;

import java.util.List;
import java.util.Map;
import racingcar.domain.BettingRound;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicle;
import racingcar.domain.Vehicles;
import racingcar.domain.vo.PredictedWinner;
import racingcar.dto.AttributeDto;
import racingcar.dto.BettingResultDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.dto.RoundResultDto;
import racingcar.dto.WinRateDto;
import racingcar.repository.BettingRepository;

public class BettingService {

    private final BettingRepository roundRepository;
    private final RaceProcessor raceProcessor;

    public BettingService(BettingRepository roundRepository, RaceProcessor raceProcessor) {
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

        BettingRound bettingRound = new BettingRound(predictedWinner.name(), namesOf(winners));
        roundRepository.save(bettingRound);

        BettingResultDto bettingResult = new BettingResultDto(bettingRound.predictedName(), bettingRound.winnerNames(),
                bettingRound.isSuccess());

        return new RoundResultDto(mapToRaceResultDto(vehicles), mapToRankDto(ranks), bettingResult);
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

    public WinRateDto calculateWinRate() {
        List<BettingRound> rounds = roundRepository.findAll();
        if (rounds.isEmpty()) {
            return new WinRateDto(0.0);
        }

        long hits = rounds.stream()
                .filter(BettingRound::isSuccess)
                .count();

        return new WinRateDto((double) hits / rounds.size() * 100);
    }
}
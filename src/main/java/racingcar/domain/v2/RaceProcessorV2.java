package racingcar.domain.v2;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.AttributeDto;
import racingcar.dto.RoundResultDto;
import racingcar.dto.WinnerDto;

public class RaceProcessorV2 {

    private final RandomNumberPickerV2 randomNumberPicker;

    public RaceProcessorV2(RandomNumberPickerV2 randomNumberPicker) {
        this.randomNumberPicker = randomNumberPicker;
    }

    public Vehicles registerCarsFrom(List<AttributeDto> attributes) {
        return Vehicles.ofUnique(attributes.stream()
                .map(attribute -> Vehicle.createOf(attribute.modelName(), attribute.riderName()))
                .toList());
    }

    public List<List<RoundResultDto>> runAllRound(int tryCount, Vehicles vehicles) {
        List<List<RoundResultDto>> allRoundResult = new ArrayList<>();

        for (int i = 0; i < tryCount; i++) {
            allRoundResult.add(runOneRound(vehicles));
        }

        return allRoundResult;
    }

    public List<RoundResultDto> runOneRound(Vehicles vehicles) {
        vehicles.move(randomNumberPicker::pick);

        return vehicles.getVehicles()
                .stream()
                .map(vehicle -> new RoundResultDto(vehicle.getName(), vehicle.getDistance()))
                .toList();
    }

    public List<WinnerDto> sortWinners(Vehicles vehicles) {
        return vehicles.findWinners()
                .stream()
                .map(winner -> new WinnerDto(winner.getName()))
                .toList();
    }
}

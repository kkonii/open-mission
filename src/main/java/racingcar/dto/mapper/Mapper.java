package racingcar.dto.mapper;

import java.util.List;
import java.util.Map;
import racingcar.domain.Vehicle;
import racingcar.domain.Vehicles;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;

public class Mapper {

    public static List<RaceResultDto> toRaceResultDto(Vehicles vehicles) {
        return vehicles.getVehicles()
                .stream()
                .map(vehicle -> new RaceResultDto(vehicle.getName(), vehicle.getDistance()))
                .toList();
    }

    public static List<RankResultDto> toRankDto(Map<Integer, List<Vehicle>> rankResult) {
        return rankResult.entrySet()
                .stream()
                .map(entry -> new RankResultDto(entry.getKey(), namesOf(entry.getValue())))
                .toList();
    }

    public static List<String> namesOf(List<Vehicle> values) {
        return values
                .stream()
                .map(Vehicle::getName)
                .toList();
    }
}

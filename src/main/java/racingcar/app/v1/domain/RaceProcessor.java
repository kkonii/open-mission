package racingcar.app.v1.domain;

import java.util.List;
import racingcar.app.v1.dto.CarDto;
import racingcar.app.v1.dto.mapper.DtoMapper;
import racingcar.common.NumberPickerBase;
import racingcar.exception.RaceError;

public class RaceProcessor {

    private static final int RUNNABLE_POINT = 1;

    private final NumberPickerBase randomNumberPicker;

    public RaceProcessor(NumberPickerBase randomNumberPicker) {
        this.randomNumberPicker = randomNumberPicker;
    }

    public Cars registerCarsFrom(List<String> names) {
        return Cars.ofUnique(names.stream()
                .map(Car::withName)
                .toList());
    }

    public void validateRunnable(int tryCount) {
        if (tryCount < RUNNABLE_POINT) {
            throw new IllegalArgumentException(RaceError.TRY_COUNT_IS_NOT_RUNNABLE.message());
        }
    }

    public List<CarDto> runOneRound(Cars cars) {
        cars.move(randomNumberPicker::pick);

        return cars.asList()
                .stream()
                .map(DtoMapper::of)
                .toList();
    }

    public List<CarDto> sortWinners(Cars cars) {
        return cars.findWinners()
                .stream().map(DtoMapper::of)
                .toList();
    }
}

package racingcar.domain;

import java.util.List;
import racingcar.domain.strategy.NumberPickerBase;
import racingcar.dto.AttributeDto;
import racingcar.dto.CarDto;
import racingcar.dto.mapper.DtoMapper;
import racingcar.exception.RaceError;

public class RaceProcessor {

    private static final int RUNNABLE_POINT = 1;

    private final NumberPickerBase randomNumberPicker;

    public RaceProcessor(NumberPickerBase randomNumberPicker) {
        this.randomNumberPicker = randomNumberPicker;
    }

    public Cars registerCarsFrom(List<AttributeDto> attributes) {
        return Cars.ofUnique(attributes.stream()
                .map(attribute -> Car.createOf(attribute.modelName(), attribute.riderName()))
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

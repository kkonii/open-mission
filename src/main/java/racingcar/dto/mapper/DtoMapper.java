package racingcar.dto.mapper;

import racingcar.domain.Car;
import racingcar.dto.CarDto;

public class DtoMapper {

    public static CarDto of(Car car) {
        return new CarDto(car.getName(), car.getDistance());
    }
}

package racingcar.dto.mapper;

import java.util.List;
import racingcar.domain.Car;
import racingcar.dto.AttributeDto;
import racingcar.dto.CarDto;

public class DtoMapper {

    public static CarDto of(Car car) {
        return new CarDto(car.getName(), car.getDistance());
    }

    public static AttributeDto of(List<String> attributes) {
        return new AttributeDto(attributes.getFirst(), attributes.getLast());
    }
}

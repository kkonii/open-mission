package racingcar.app.v1.dto.mapper;

import java.util.List;
import racingcar.app.v1.domain.Car;
import racingcar.app.v1.dto.CarDto;
import racingcar.app.v2.dto.AttributeDto;

public class DtoMapper {

    public static CarDto of(Car car) {
        return new CarDto(car.getName(), car.getDistance());
    }

    public static AttributeDto of(List<String> attributes) {
        return new AttributeDto(attributes.getFirst(), attributes.getLast());
    }
}

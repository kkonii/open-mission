package racingcar.domain.v2;

import java.util.List;
import racingcar.dto.AttributeDto;

public class RaceProcessorV2 {

    public Vehicles registerCarsFrom(List<AttributeDto> attributes) {
        return Vehicles.ofUnique(attributes.stream()
                .map(attribute -> Vehicle.createOf(attribute.modelName(), attribute.riderName()))
                .toList());
    }
}

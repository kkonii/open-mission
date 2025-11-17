package racingcar.fixture;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import racingcar.app.v2.domain.rule.VehicleModel;
import racingcar.app.v2.system.Language;
import racingcar.app.v2.view.message.MessageKey;

public class Provider {

    public static Stream<Arguments> movablePointArguments() {
        return Stream.of(
                Arguments.of(VehicleModel.BIKE, 3, 1),
                Arguments.of(VehicleModel.CAR, 3, 1),
                Arguments.of(VehicleModel.FERRARI, 9, 2),
                Arguments.of(VehicleModel.TAXI, 5, 1),
                Arguments.of(VehicleModel.BUS, 8, 1)
        );
    }

    public static Stream<Arguments> languageMessageArguments() {
        return Stream.of(
                Arguments.of(Language.KOREAN, MessageKey.PARTICIPANTS_HEADER, "경주 차량 목록"),
                Arguments.of(Language.ENGLISH, MessageKey.PARTICIPANTS_HEADER, "List of Participants"),
                Arguments.of(Language.JAPANESE, MessageKey.PARTICIPANTS_HEADER, "参加車両リスト")
        );
    }
}

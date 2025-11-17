package racingcar.app.v2.view;

import java.util.stream.Collectors;
import racingcar.app.v2.domain.rule.VehicleModel;
import racingcar.app.v2.view.message.MessageKey;
import racingcar.app.v2.view.message.Provider;
import racingcar.common.ConsoleScanner;

public class InputView {

    private final Provider provider;

    public InputView(Provider provider) {
        this.provider = provider;
    }

    public void guideRacingCars() {
        System.out.println(provider.messageOf(MessageKey.PARTICIPANTS_HEADER));
        System.out.println(VehicleModel.getNames()
                .stream()
                .collect(Collectors.joining(", ", "<", ">")));
        System.out.println();
    }

    public String getNameInputs() {
        System.out.println(provider.messageOf(MessageKey.CAR_AND_NAMES_TO_RACE));

        return ConsoleScanner.readLine();
    }

    public String getCountInput() {
        System.out.println(provider.messageOf(MessageKey.COUNT_TO_TRY));

        return ConsoleScanner.readLine();
    }
}

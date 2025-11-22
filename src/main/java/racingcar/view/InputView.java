package racingcar.view;

import java.util.stream.Collectors;
import racingcar.common.ConsoleScanner;
import racingcar.domain.rule.VehicleModel;
import racingcar.view.message.MessageKey;
import racingcar.view.message.Provider;

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
        System.out.println();
        System.out.println(provider.messageOf(MessageKey.BETTING_COUNT));

        return ConsoleScanner.readLine();
    }

    public String getPredictedInput() {
        System.out.println();
        System.out.println(provider.messageOf(MessageKey.PREDICTED_WINNER));

        return ConsoleScanner.readLine();
    }
}

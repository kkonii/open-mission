package racingcar.controller.v2;

import java.util.List;
import racingcar.domain.v2.RaceProcessorV2;
import racingcar.domain.v2.Vehicles;
import racingcar.dto.AttributeDto;
import racingcar.util.InputValidator;
import racingcar.util.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceV2 {

    private final RaceProcessorV2 raceProcessor;
    private final InputView inputView;
    private final OutputView outputView;

    public RaceV2(RaceProcessorV2 raceProcessor, InputView inputView, OutputView outputView) {
        this.raceProcessor = raceProcessor;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Vehicles readyCars() {
        String nameInput = inputView.getNameInputs();

        InputValidator.blankValue(nameInput);
        List<AttributeDto> attributes = Parser.toAttributes(nameInput);

        return raceProcessor.registerCarsFrom(attributes);
    }

}

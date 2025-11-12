package racingcar.controller.v2;

import java.util.List;
import racingcar.domain.v2.RaceProcessorV2;
import racingcar.domain.v2.Vehicles;
import racingcar.dto.AttributeDto;
import racingcar.dto.RoundResultDto;
import racingcar.util.InputValidator;
import racingcar.util.v1.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputViewV2;

public class RaceV2 {

    private final RaceProcessorV2 raceProcessor;
    private final InputView inputView;
    private final OutputViewV2 outputView;

    public RaceV2(RaceProcessorV2 raceProcessor, InputView inputView, OutputViewV2 outputView) {
        this.raceProcessor = raceProcessor;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Vehicles cars = readyCars();
        int tryCount = inputTryCount();

        proceedRace(tryCount, cars);
    }

    public Vehicles readyCars() {
        String nameInput = inputView.getNameInputs();

        InputValidator.blankValue(nameInput);
        List<AttributeDto> attributes = Parser.toAttributes(nameInput);

        return raceProcessor.registerCarsFrom(attributes);
    }

    private int inputTryCount() {
        String countInput = inputView.getCountInput();
        InputValidator.blankValue(countInput);

        return Parser.toInteger(countInput);
    }

    private void proceedRace(int tryCount, Vehicles vehicles) {
        outputView.printHeader();
        List<List<RoundResultDto>> allResults = raceProcessor.runAllRound(tryCount, vehicles);
        allResults.forEach(outputView::printResultOf);
    }
}

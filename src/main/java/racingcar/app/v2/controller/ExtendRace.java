package racingcar.app.v2.controller;

import java.util.List;
import racingcar.app.v2.domain.RaceProcessor;
import racingcar.app.v2.domain.Vehicles;
import racingcar.app.v2.dto.AttributeDto;
import racingcar.app.v2.dto.RoundResultDto;
import racingcar.app.v2.dto.WinnerDto;
import racingcar.app.v2.util.Parser;
import racingcar.app.v2.view.InputView;
import racingcar.app.v2.view.OutputView;
import racingcar.common.InputValidator;
import racingcar.common.Race;
import racingcar.common.RetryHandler;

public class ExtendRace implements Race {

    private final RaceProcessor raceProcessor;
    private final InputView inputView;
    private final OutputView outputView;

    public ExtendRace(RaceProcessor raceProcessor, InputView inputView, OutputView outputView) {
        this.raceProcessor = raceProcessor;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Vehicles cars = RetryHandler.runUntilSuccess(this::inputVehicles);
        int tryCount = RetryHandler.runUntilSuccess(this::inputTryCount);

        proceedRace(tryCount, cars);
        printWinner(cars);
    }

    private Vehicles inputVehicles() {
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

    private void printWinner(Vehicles vehicles) {
        List<WinnerDto> winners = raceProcessor.sortWinners(vehicles);
        outputView.printNamesOf(winners);
    }
}

package racingcar.controller;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.RaceProcessor;
import racingcar.dto.CarDto;
import racingcar.util.InputValidator;
import racingcar.util.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Race {

    private final RaceProcessor raceProcessor;
    private final InputView inputView;
    private final OutputView outputView;

    public Race(RaceProcessor raceProcessor, InputView inputView, OutputView outputView) {
        this.raceProcessor = raceProcessor;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = readyCars();
        int tryCount = inputTryCount();

        proceedRace(tryCount, cars);
        printWinner(cars);
    }

    private Cars readyCars() {
        String nameInput = inputView.getNameInputs();

        InputValidator.blankValue(nameInput);
        List<String> names = Parser.toNames(nameInput);

        return raceProcessor.registerCarsFrom(names);
    }

    private int inputTryCount() {
        String countInput = inputView.getCountInput();

        InputValidator.blankValue(countInput);
        int tryCount = Parser.toInteger(countInput);
        raceProcessor.validateRunnable(tryCount);

        return tryCount;
    }

    private void proceedRace(int tryCount, Cars cars) {
        outputView.printHeader();
        for (int i = 0; i < tryCount; i++) {
            List<CarDto> carsDto = raceProcessor.runOneRound(cars);
            outputView.printResultOf(carsDto);
        }
    }

    private void printWinner(Cars cars) {
        List<CarDto> winners = raceProcessor.sortWinners(cars);
        outputView.printNamesOf(winners);
    }
}

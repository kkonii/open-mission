package racingcar.controller;

import java.util.List;
import racingcar.common.InputValidator;
import racingcar.common.RetryHandler;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Vehicles;
import racingcar.dto.AttributeDto;
import racingcar.dto.FinalResultDto;
import racingcar.dto.RoundResultDto;
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
        inputView.guideRacingCars();

        Vehicles cars = RetryHandler.runUntilSuccess(this::inputVehicles);
        int tryCount = RetryHandler.runUntilSuccess(this::inputTryCount);

        proceedRace(tryCount, cars);
        printResultOf(cars);
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
        List<RoundResultDto> finishedRace = raceProcessor.runAllRound(vehicles);
        outputView.printResultOf(finishedRace);
    }

    private void printResultOf(Vehicles vehicles) {
        List<FinalResultDto> statistics = raceProcessor.statisticsOf(vehicles);
        outputView.printRankOf(statistics);
    }
}

package racingcar.controller;

import java.util.List;
import racingcar.common.InputValidator;
import racingcar.common.RetryHandler;
import racingcar.domain.Vehicles;
import racingcar.domain.vo.PredictedWinner;
import racingcar.dto.AttributeDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RankResultDto;
import racingcar.service.RaceProcessor;
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
        int bettingCount = RetryHandler.runUntilSuccess(this::inputBettingCount);

        proceedRace(cars);
        printResultOf(cars);
    }

    private Vehicles inputVehicles() {
        String nameInput = inputView.getNameInputs();

        InputValidator.blankValue(nameInput);
        List<AttributeDto> attributes = Parser.toAttributes(nameInput);

        return raceProcessor.registerCarsFrom(attributes);
    }

    private int inputBettingCount() {
        String countInput = inputView.getCountInput();
        InputValidator.blankValue(countInput);

        return Parser.toInteger(countInput);
    }

    private PredictedWinner inputPredictedWinner(Vehicles vehicles) {
        String nameInput = inputView.getPredictedInput();
        InputValidator.blankValue(nameInput);

        vehicles.validateContainsName(nameInput);

        return new PredictedWinner(nameInput);
    }

    private void proceedRace(Vehicles vehicles) {
        outputView.printHeader();
        List<RaceResultDto> finishedRace = raceProcessor.runRace(vehicles);
        outputView.printResultOf(finishedRace);
    }

    private void printResultOf(Vehicles vehicles) {
        List<RankResultDto> statistics = raceProcessor.statisticsOf(vehicles);
        outputView.printRankOf(statistics);
    }
}

package racingcar.controller;

import java.util.List;
import racingcar.common.InputValidator;
import racingcar.common.RetryHandler;
import racingcar.domain.Vehicles;
import racingcar.domain.vo.PredictedWinner;
import racingcar.dto.AttributeDto;
import racingcar.dto.RoundResultDto;
import racingcar.dto.WinRateDto;
import racingcar.service.BettingService;
import racingcar.util.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Race {

    private final BettingService bettingService;
    private final InputView inputView;
    private final OutputView outputView;

    public Race(BettingService bettingService, InputView inputView, OutputView outputView) {
        this.bettingService = bettingService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.guideRacingCars();

        Vehicles cars = RetryHandler.runUntilSuccess(this::inputVehicles);
        int bettingCount = RetryHandler.runUntilSuccess(this::inputBettingCount);

        for (int i = 0; i < bettingCount; i++) {
            PredictedWinner predictedWinner = RetryHandler.runUntilSuccess(() -> inputPredictedWinner(cars));
            proceedRace(predictedWinner, cars);
        }
        printWinRate();
    }

    private Vehicles inputVehicles() {
        String nameInput = inputView.getNameInputs();

        InputValidator.blankValue(nameInput);
        List<AttributeDto> attributes = Parser.toAttributes(nameInput);

        return bettingService.registerCarsFrom(attributes);
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

    private void proceedRace(PredictedWinner predictedWinner, Vehicles vehicles) {
        outputView.printHeader();
        RoundResultDto finishedRound = bettingService.playOneRound(predictedWinner, vehicles);

        printResultOf(finishedRound);
    }

    private void printResultOf(RoundResultDto finishedRound) {
        outputView.printResultOf(finishedRound.raceResults());
        outputView.printRankOf(finishedRound.rankResults());
        outputView.printBettingResult(finishedRound.bettingResult());
    }

    private void printWinRate() {
        WinRateDto winRate = bettingService.calculateWinRate();
        outputView.printWinRate(winRate);
    }
}

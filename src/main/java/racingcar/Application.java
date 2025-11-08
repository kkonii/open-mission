package racingcar;

import racingcar.controller.Race;
import racingcar.domain.RaceProcessor;
import racingcar.domain.RandomNumberPicker;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RaceProcessor raceProcessor = new RaceProcessor(new RandomNumberPicker());
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Race race = new Race(raceProcessor, inputView, outputView);
        race.run();
    }
}

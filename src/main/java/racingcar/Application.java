package racingcar;

import racingcar.app.v1.controller.ClassicRace;
import racingcar.app.v1.domain.RaceProcessor;
import racingcar.app.v1.domain.strategy.RandomNumberPicker;
import racingcar.app.v1.view.InputView;
import racingcar.app.v1.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RaceProcessor raceProcessor = new RaceProcessor(new RandomNumberPicker());
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        ClassicRace race = new ClassicRace(raceProcessor, inputView, outputView);
        race.run();
    }
}

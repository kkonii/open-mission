package racingcar.config;

import racingcar.controller.Race;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Statistics;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.system.Language;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.message.Provider;

public class AppConfig {

    private static final RandomNumberPicker RANDOM_NUMBER_PICKER = new RandomNumberPicker();
    private static final Statistics STATISTICS = new Statistics();
    private static final RaceProcessor PROCESSOR = new RaceProcessor(RANDOM_NUMBER_PICKER, STATISTICS);

    public static Race raceWith(Language language) {
        Provider provider = Provider.fetchBy(language);
        InputView INPUT_VIEW = new InputView(provider);
        OutputView OUTPUT_VIEW = new OutputView(provider);

        return new Race(PROCESSOR, INPUT_VIEW, OUTPUT_VIEW);
    }
}

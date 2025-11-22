package racingcar.config;

import racingcar.controller.Race;
import racingcar.domain.RaceProcessor;
import racingcar.domain.Statistics;
import racingcar.domain.rule.RandomNumberPicker;
import racingcar.repository.BettingRoundRepository;
import racingcar.service.BettingService;
import racingcar.system.Language;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.message.Provider;

public class AppConfig {

    private static final BettingRoundRepository ROUND_REPOSITORY = new BettingRoundRepository();
    private static final RandomNumberPicker RANDOM_NUMBER_PICKER = new RandomNumberPicker();
    private static final Statistics STATISTICS = new Statistics();
    private static final RaceProcessor RACE_PROCESSOR = new RaceProcessor(RANDOM_NUMBER_PICKER, STATISTICS);
    private static final BettingService BETTING_SERVICE = new BettingService(ROUND_REPOSITORY, RACE_PROCESSOR);

    public static Race raceWith(Language language) {
        Provider provider = Provider.fetchBy(language);
        InputView INPUT_VIEW = new InputView(provider);
        OutputView OUTPUT_VIEW = new OutputView(provider);

        return new Race(BETTING_SERVICE, INPUT_VIEW, OUTPUT_VIEW);
    }
}

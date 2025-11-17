package racingcar.config;

import racingcar.app.v1.controller.ClassicRace;
import racingcar.app.v1.domain.RaceProcessor;
import racingcar.app.v1.domain.strategy.RandomNumberPicker;
import racingcar.app.v1.view.InputView;
import racingcar.app.v1.view.OutputView;
import racingcar.app.v2.controller.ExtendRace;
import racingcar.app.v2.domain.Statistics;
import racingcar.app.v2.system.Language;
import racingcar.app.v2.view.message.Provider;
import racingcar.common.Race;

public class AppConfig {

    //구버전
    private static final RandomNumberPicker RANDOM_NUMBER_PICKER_V1 = new RandomNumberPicker();
    private static final RaceProcessor PROCESSOR_V1 = new RaceProcessor(RANDOM_NUMBER_PICKER_V1);
    private static final InputView INPUT_V1 = new InputView();
    private static final OutputView OUTPUT_V1 = new OutputView();

    //신버전
    private static final racingcar.app.v2.domain.rule.RandomNumberPicker RANDOM_NUMBER_PICKER_V2 = new racingcar.app.v2.domain.rule.RandomNumberPicker();
    private static final Statistics STATISTICS_V2 = new Statistics();
    private static final racingcar.app.v2.domain.RaceProcessor PROCESSOR_V2 = new racingcar.app.v2.domain.RaceProcessor(
            RANDOM_NUMBER_PICKER_V2, STATISTICS_V2);

    //구버전
    public static Race classicRace() {
        return new ClassicRace(PROCESSOR_V1, INPUT_V1, OUTPUT_V1);
    }

    //신버전
    public static Race extendRace(Language language) {
        Provider provider = Provider.fetchBy(language);
        racingcar.app.v2.view.InputView INPUT_V2 = new racingcar.app.v2.view.InputView(provider);
        racingcar.app.v2.view.OutputView OUTPUT_V2 = new racingcar.app.v2.view.OutputView(provider);

        return new ExtendRace(PROCESSOR_V2, INPUT_V2, OUTPUT_V2);
    }
}

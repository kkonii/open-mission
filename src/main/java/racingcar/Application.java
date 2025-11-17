package racingcar;

import racingcar.app.v2.system.Language;
import racingcar.common.Race;
import racingcar.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        Race race = AppConfig.extendRace(Language.JAPANESE);
        race.run();
    }
}

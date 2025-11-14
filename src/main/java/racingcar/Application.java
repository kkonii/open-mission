package racingcar;

import racingcar.common.Race;
import racingcar.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        Race race = AppConfig.extendRace();
        race.run();
    }
}

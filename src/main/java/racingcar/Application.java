package racingcar;

import racingcar.common.ConsoleScanner;
import racingcar.common.RetryHandler;
import racingcar.config.AppConfig;
import racingcar.controller.Race;
import racingcar.system.Language;
import racingcar.util.Parser;

public class Application {
    public static void main(String[] args) {
        Language language = RetryHandler.runUntilSuccess(Application::chooseLanguage);
        Race race = AppConfig.raceWith(language);
        race.run();
    }

    private static Language chooseLanguage() {
        System.out.println("언어를 선택하세요 / Choose language / 言語を選択してください");
        System.out.println("1. 한국어");
        System.out.println("2. English");
        System.out.println("3. 日本語");

        int languageNumber = Parser.toInteger(ConsoleScanner.readLine());

        return Language.findBy(languageNumber);
    }
}

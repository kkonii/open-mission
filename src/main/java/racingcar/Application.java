package racingcar;

import racingcar.app.v2.system.Language;
import racingcar.app.v2.util.Parser;
import racingcar.common.ConsoleScanner;
import racingcar.common.Race;
import racingcar.common.RetryHandler;
import racingcar.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        Language language = RetryHandler.runUntilSuccess(Application::chooseLanguage);
        Race race = AppConfig.extendRace(language);
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

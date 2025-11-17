package racingcar.app.v2.view.message;

import java.util.EnumMap;
import java.util.Map;
import racingcar.app.v2.system.Language;

public class Provider {

    private final Map<MessageKey, String> messages;

    private Provider(Map<MessageKey, String> messages) {
        this.messages = messages;
    }

    public static Provider fetchBy(Language language) {
        Map<MessageKey, String> messages = new EnumMap<>(MessageKey.class);

        if (language == Language.KOREAN) {
            messages.put(MessageKey.PARTICIPANTS_HEADER, "경주 차량 목록");
            messages.put(MessageKey.CAR_AND_NAMES_TO_RACE, "차량와 이름을 함께 입력하세요. (예시: BIKE-강냉이,CAR-돌고래)");
            messages.put(MessageKey.COUNT_TO_TRY, "시도할 횟수를 입력하세요.");

            messages.put(MessageKey.ROUND_RESULT_HEADER, "실행 결과");
            messages.put(MessageKey.FINAL_RANK_HEADER, "최종 순위 발표");
            messages.put(MessageKey.RANK_IS, "%d등 : %s");
        }

        if (language == Language.ENGLISH) {
            messages.put(MessageKey.PARTICIPANTS_HEADER, "List of Participants");
            messages.put(MessageKey.CAR_AND_NAMES_TO_RACE, "Enter the vehicle models and names. (e.g. BIKE-me,CAR-hi)");
            messages.put(MessageKey.COUNT_TO_TRY, "Enter the count to try.");

            messages.put(MessageKey.ROUND_RESULT_HEADER, "Round Results");
            messages.put(MessageKey.FINAL_RANK_HEADER, "Final Rank");
            messages.put(MessageKey.RANK_IS, "%d place: %s");
        }

        if (language == Language.JAPANESE) {
            messages.put(MessageKey.PARTICIPANTS_HEADER, "参加車両リスト");
            messages.put(MessageKey.CAR_AND_NAMES_TO_RACE,
                    "車のタイプトニックを入力してください。（例：BIKE-トラ、CAR-鯨）");
            messages.put(MessageKey.COUNT_TO_TRY, "試行回数を入力してください。");

            messages.put(MessageKey.ROUND_RESULT_HEADER, "実行の結果");
            messages.put(MessageKey.FINAL_RANK_HEADER, "最終順位発表");
            messages.put(MessageKey.RANK_IS, "%d位：%s");
        }

        return new Provider(messages);
    }

    public String messageOf(MessageKey messageKey) {
        return messages.get(messageKey);
    }
}

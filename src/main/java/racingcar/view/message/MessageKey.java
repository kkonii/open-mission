package racingcar.view.message;

public enum MessageKey {

    PARTICIPANTS_HEADER,        // 경주 차량 목록 헤더
    CAR_AND_NAMES_TO_RACE,      // 차량과 이름값 요청
    BETTING_COUNT,               // 배팅 횟수 요청
    PREDICTED_WINNER,           // 예상하는 우승자

    ROUND_RESULT_HEADER,        // 횟수별 결과 헤더
    FINAL_RANK_HEADER,          // 최종 순위 발표 헤더
    RANK_IS,                    // %d등 : %s

    WINNER_IS,                  // 최종 우승자 : %s
    PREDICTED_WINNER_IS,        // 예측한 우승자 : %s
    BETTING_FAIL,               // 예측 배팅 실패
    BETTING_SUCCESS,            // 예측 배팅 성공

    WIN_RATE_HEADER,            // 최종 승률 발표 헤더
    WIN_RATE                    // 최종 승률
}

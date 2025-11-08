package racingcar.exception;

public enum RaceError {

    NAME_LENGTH_IS_OVER("자동차 이름은 최대 %d자 까지만 입력할 수 있습니다."),
    NAME_IS_BLANK("공백으로 이루어진 이름은 입력할 수 없습니다."),
    NAME_IS_NOT_VALID_PATTERN("이름은 한글, 영어, 숫자 조합으로만 입력할 수 있습니다. (띄어쓰기는 허용됩니다.)"),
    TRY_COUNT_IS_NOT_RUNNABLE("시도 횟수를 1회 이상 입력해 주세요."),
    NAMES_ARE_NOT_UNIQUE("자동차 이름은 중복해서 입력할 수 없습니다."),
    VEHICLE_MODEL_NOT_EXIST("입력한 차량 기종은 존재하지 않습니다.");

    private final String message;

    RaceError(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public String messageOf(int numberValue) {
        return String.format(message, numberValue);
    }
}

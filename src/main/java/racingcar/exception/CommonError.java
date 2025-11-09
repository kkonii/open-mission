package racingcar.exception;

public enum CommonError {

    NOT_A_NUMBER("숫자 값만 입력할 수 있습니다."),
    INPUT_IS_BLANK("공백으로만 이루어진 값은 입력할 수 없습니다."),
    OUT_OF_INTEGER_RANGE("입력 가능한 숫자의 범위를 초과했습니다."),
    NAME_FORMAT_IS_NOT_VALID_PATTERN("올바르지 않은 형식입니다. ,로 구분하여 입력해 주세요."),
    ATTRIBUTE_FORMAT_IS_NOT_VALID("입력 형식이 올바르지 않습니다. 차량과 이름은 -로 짝지어 입력해 주세요.");

    private final String message;

    CommonError(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

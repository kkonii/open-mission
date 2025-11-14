package racingcar.common;

import java.util.function.Supplier;

public class RetryHandler {

    public static <T> T runUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

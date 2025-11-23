package racingcar.common;

import java.util.Optional;
import java.util.function.Supplier;

public class RetryHandler {

    public static <T> T runUntilSuccess(Supplier<T> action) {
        ExecuteResult result;
        do {
            result = executeAction(action);
        } while (!result.isSuccess());

        return (T) result.result();
    }

    private static <T> ExecuteResult executeAction(Supplier<T> action) {
        try {
            return new ExecuteResult(action.get(), true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ExecuteResult(Optional.empty(), false);
    }

    private record ExecuteResult(Object result, boolean isSuccess) {
    }
}

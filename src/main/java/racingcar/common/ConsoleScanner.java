package racingcar.common;

import java.util.Scanner;

public final class ConsoleScanner {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleScanner() {

    }

    public static String readLine() {
        return SCANNER.nextLine();
    }
}

package racingcar.v2.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @Test
    void 데이터를_출력하는_데에_성공한다() {
        //given
        String input = "out";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // when
        System.out.println(input);
        // then
        Assertions.assertEquals(input + "\r\n", outContent.toString());
    }
}

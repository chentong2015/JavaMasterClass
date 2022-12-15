package JavaDebuggingExceptions.checked;

import java.util.Scanner;

public class TestExceptionDemo {

    public void test() throws TestException {
        try {
            int value = new Scanner(System.in).nextInt();
        } catch (Exception exception) {
            throw new TestException("Test Failed", true);
        }
    }

    public void testCallException() {
        try {
            test();
        } catch (TestException exception) {
            // 从异常中拿到指定的数据情况
            System.out.println(exception.isStatus());
        }
    }
}

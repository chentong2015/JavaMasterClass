package JavaLambdaExpressions.Streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class DemoStream {

    // 从String中直接获取Streams
    private void testStringStreams() {
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();
    }

    // 使用Arrays.stream(array)构建stream()
    private long testArrayStreams(int... array) {
        return array == null ? 0 : Arrays.stream(array).filter(i -> i == 9).count();
    }
}

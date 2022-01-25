package JavaLambdaStreams.operations;

import java.util.Arrays;
import java.util.stream.Stream;

// Terminal Operation:
// 终端操作可能会遍历该流以产生结果或副作用，立即开始执行
// 执行终端操作后，流管道被视为已消耗，无法再使用
// 如果需要再次遍历同一数据源，则必须返回到数据源以获取新的流
public class OperationTerminal {

    private static void testStreamOperations(int... array) {
        // 使用一行代码统计一组数据中满足条件的item的数目: 并非算法的具体实现
        long count = array == null ? 0 : Arrays.stream(array).filter(i -> i == 9).count();

        Stream<String> ioNumberStream = Stream.of("I24", "O90", "A12");
        Stream<String> inNumberStream = Stream.of("N40", "I26", "O23");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        long total = concatStream.distinct()
                // Intermediate operation：从结果流中消耗元素时对每个元素另外执行提供的操作
                .peek(System.out::println)
                .count();
        System.out.println(total);
    }

}

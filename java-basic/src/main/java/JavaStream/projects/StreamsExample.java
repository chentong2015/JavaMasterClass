package JavaStream.projects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsExample {

    // 使用一行代码统计一组数据中满足条件的item的数目
    private long countNumber(int... array) {
        return (array == null) ? 0 : Arrays.stream(array).filter(i -> i == 9).count();
    }

    // 获取一组数据中的Top3
    public void testIntermediateOperations() {
        Stream<String> s = Stream.of("m", "k", "c", "t").sorted().limit(3);
        System.out.println(s);
    }

    // .forEach()等效于stream().forEach()
    private void testStreamForEach() {
        List<String> values = List.of("item 1", "item 2");
        values.forEach(s -> System.out.println(s.startsWith("it")));
        values.stream().forEach(s -> System.out.println(s.startsWith("it")));
    }

    private void testStreamOperations() {
        Stream<String> ioNumberStream = Stream.of("I24", "O90", "A12");
        Stream<String> inNumberStream = Stream.of("N40", "I26", "O23");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        long total = concatStream.distinct()
                .peek(System.out::println)
                .count();
        System.out.println(total);
    }
}

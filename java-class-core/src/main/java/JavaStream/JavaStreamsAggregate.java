package JavaStream;

import java.util.*;
import java.util.stream.Collectors;

// Streams Aggregate Operations 集合流的聚合操作: > JDK 1.8
// 0. A pipeline is a sequence of aggregate operations
// 1. Aggregate operations process elements from a stream, not directly from a collection
// 2. Streams: A sequence of elements supporting and parallel aggregate operations 支持和并行聚合操作
// 3. Collectors: 实现各种有用的约简操作的Collector的实现，例如将元素累积到集合中，根据各种标准对元素进行汇总等 !!!!
public class JavaStreamsAggregate {

    // TODO. Streams 集合流操作特点
    // 1. 使用stream()时，不对源始数据造成影响
    // 2. 每一个操作都执行上一个操作所完成的结果，构成操作的链条
    // 3. 操作相互独立，不依赖于前一个操作的变量
    // 4. 必须是stateless无状态
    //    the result of an operation can't depend on any state outside of the operation
    private void testListStreams() {
        List<String> numbers = Arrays.asList("G15", "H67", "K56", "P56");
        numbers.stream() // Returns a sequential Stream
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);  // forEach() is Terminal Operation

        List<String> backNumber = numbers.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList()); // Terminal Operation: 获取到Stream操作完成的结果

        // TODO. 注意Aggregate聚合操作过程中的错误
        // Long::parseLong 解析字符串时可能报错
        // boxed() 将解析出来的long类型装箱成Long包装类型 => 存在性能问题
        // distinct() 对于unordered streams不保证稳定性
        List<String> numList = Arrays.asList("11", "2", "3", "four", "5", "6", "7", "8");
        List<Long> nums = numList.stream()
                .filter(str -> !str.contains("3"))
                .mapToLong(Long::parseLong)
                .boxed()
                .sorted()
                .distinct()
                .toList();

        List<String> strings = Arrays.asList("Stream", "Operations", "on", "Collections");
        strings.stream()
                .min(Comparator.comparing(String::length))
                .ifPresent(System.out::println); // Output: on
    }
}

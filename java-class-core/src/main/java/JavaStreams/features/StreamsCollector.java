package JavaStreams.features;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO. 使用Collectors对Streams流处理后的结果进行收集
// 收集器工厂: toList(), toSet(), toMap(), groupingBy(), joining()
public class StreamsCollector {

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("G15", "H67", "K56", "P56");
        List<String> backNumber = numbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .toList();
        // .collect(Collectors.toList());
        // .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        // 只收集字符串特定位置的字符数据
        List<Character> backChars = numbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .map(s -> s.charAt(2))
                .toList();

        // 直接使用Collectors中提供的静态方法，见Stream<T>转变成List<T>
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .toList();

        // partitioningBy() 划分之后，返回两组list数据
        Map<Boolean, List<Integer>> map = Stream.of(2, 34, 54, 23, 33, 20, 7)
                .collect(Collectors.partitioningBy(i -> i > 10));
    }
}

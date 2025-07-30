package JavaStreams.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO. Collectors对Streams流处理后的结果进行收集
// 收集器工厂: toList(), toSet(), toMap(), groupingBy(), joining()
public class StreamsCollector {

    public static void main(String[] args) {
        // 使用Collectors中提供的静态方法，将Stream<T>转变成List<T>
        // .collect(Collectors.toList());
        // .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).toList();

        List<String> numbers = Arrays.asList("G15", "H67", "K56", "P56");
        List<String> backNumber = numbers.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted().toList();

        // 只收集字符串特定位置的字符数据
        List<Character> backChars = numbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .map(s -> s.charAt(2))
                .toList();

        // partitioningBy() 划分之后，返回两组list数据
        Map<Boolean, List<Integer>> map = Stream.of(2, 34, 54, 23, 33, 20, 7)
                .collect(Collectors.partitioningBy(i -> i > 10));

        // toMap() 对流数据结果进行映射处理: Merge Function定义值的合并规则
        List<Pair<Long, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>(1L, "item1"));
        pairList.add(new Pair<>(2L, "item2"));
        pairList.add(new Pair<>(2L, "item3"));
        Map<Long, String> mapResult = pairList.stream()
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                        (oldValue, newValue) -> oldValue + "-" + newValue));
        System.out.println(mapResult); // {1=item1, 2=item2-item3}
    }

    static class Pair<T1, T2> {
        T1 first;
        T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
        }
    }
}

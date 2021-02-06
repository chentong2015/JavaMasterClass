package JavaLambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Collections Streams Aggregate Operations 集合流的聚合操作      ====> C#区别: LINQ语言集成查询
// 1. Streams: 支持和并行聚合操作的一系列元素
//    A sequence of elements supporting and parallel aggregate operations
// 2. 聚合操作方法的调用需要传递Lambda表达式的参数或者直接引用已经存在的方法
public class Steams {

    private List<String> numbers = Arrays.asList("G15", "H67", "K56", "P56");
    private List<String> backNumbers = new ArrayList<>();

    private void testBasics() {
        numbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                backNumbers.add(number);
            }
        });
        // backNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        backNumbers.sort(Comparator.naturalOrder()); // 以自然顺序比较可比较对象, 默认是字母排序大小的先后顺序 
        backNumbers.forEach((String s) -> System.out.println(s));
    }

    /**
     * 1. 使用stream()时，不对源始数据造成影响
     * 2. 必须是stateless无状态的，the result of an operation can't depend on any state outside of the operation
     * 3. 每一个操作都视为是独立的，它不依赖于前一个操作的变量，变量值
     */
    private void testStreams() {
        numbers.stream() // Returns a sequential Stream with this collection as its source 拿到原始数据的stream
                .map(String::toUpperCase)  // Function<? super T, ? extends R> mapper 直接引用已经存在的方法, 代替lambda表达式 !!!
                .filter(s -> s.startsWith("G"))  // Predicate<? super T> predicate 过滤的条件，返回boolean判读的结果
                .sorted()
                .forEach(System.out::println); // Consumer<? super T> action 消费者
    }

}

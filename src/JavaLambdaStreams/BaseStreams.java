package JavaLambdaStreams;

import JavaLambdaStreams.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

// Streams流: 代表元素有限或者无需的顺序
// - 有顺序的序列的数据，提供公共读写IO，unidirectional单向操作，不存储数据
// - 收集器工厂：toList(), toSet(), toMap(), groupingBy(), joining()
public class BaseStreams {

    // TODO: Stream pipeline: 代表元素的多级运算
    //  1. 包含多个中间操作(intermediate operation)和终止操作(Terminal Operation)
    //  2. 通常是Lazy的, 直到中止操作才开始, 如果无终止操作, 则是一个无操作的指令
    // They always return a new stream. This allows the operations to be connected.
    // They don't process the elements until a terminal operation is invoked.
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(new Product(23d, "potatoes"),
                new Product(15d, "orange"), new Product(13d, "lemon"),
                new Product(23d, "bread"), new Product(26d, "orange"));
        Stream<Product> orangeStream = productList.stream().filter(product -> product.getName().equals("orange"));
        List<Double> originalOranges = orangeStream.map(Product::getPrice).toList();
        System.out.println(originalOranges);

        // TODO. 执行终端操作后，流管道被视为已消耗，无法再使用
        // After the terminal operation is performed, the stream pipeline is consumed and can't be used anymore
        // 前面已使用Terminal Operations .collect()，报错.IllegalStateException
        List<Double> disCountedOranges = orangeStream.map(product -> product.getPrice() * 0.95).toList();
        System.out.println(disCountedOranges);
    }

    public static void testStreams() {
        List<String> numList = Arrays.asList("11", "2", "3", "four", "5", "6", "7", "8");
        numList.stream()
                .filter(str -> !str.contains("3"))  // 根据条件进行过滤
                .flatMap(str -> Stream.of(str + " " + " right? ")) // 组合构造成新的Stream
                .distinct()                         // 排除重复的
                .map(str -> str.split(" ")[0]) // 按照指定的规则进行映射
                .limit(3)                           // 限制显示的数据
                .skip(1)
                .sorted()                           // 对结果进行排序
                .forEach(System.out::println);

        List<String> stringList = Arrays.asList("2", "3", "3");
        int sum = stringList.stream()
                .filter(str -> !str.contains("3"))
                .mapToInt(Integer::parseInt)
                .sum();

        List<String> strings = Arrays.asList("Stream", "Operations", "on", "Collections");
        strings.stream()
                .min(Comparator.comparing((String s) -> s.length()))
                .ifPresent(System.out::println); // Output: on
    }
}

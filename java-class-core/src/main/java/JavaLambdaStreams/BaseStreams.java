package JavaLambdaStreams;

import JavaLambdaStreams.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
        List<Double> originalOranges = orangeStream.map(Product::getPrice).collect(Collectors.toList());
        System.out.println(originalOranges);

        // TODO. 执行终端操作后，流管道被视为已消耗，无法再使用
        // After the terminal operation is performed, the stream pipeline is consumed and can't be used anymore
        // 前面已使用Terminal Operations .collect()，报错.IllegalStateException
        List<Double> disCountedOranges = orangeStream
                .map(product -> product.getPrice() * 0.95)
                .collect(Collectors.toList());
        System.out.println(disCountedOranges);
    }

    public static void testStreams() {
        List<String> numList = Arrays.asList("11", "2", "3", "four", "5", "6", "7", "8");
        numList.stream().filter(str -> !str.contains("3"))
                .flatMap(str -> Stream.of(str + " " + " right? ")) // 返回新的Stream操作源
                .map(str -> str.split(" ")[0])
                .limit(3)
                .skip(1)
                .sorted()
                .forEach(System.out::println);

        // TODO. distinct()对于unordered streams不保证稳定性
        // Long::parseLong 解析字符串时可能报错
        // boxed() 将解析出来的long类型装箱成Long包装类型 => 存在性能问题
        List<Long> nums = numList.stream()
                .filter(str -> !str.contains("3"))
                .mapToLong(Long::parseLong)
                .boxed()
                .sorted()
                .distinct()
                .toList();

        List<String> strings = Arrays.asList("Stream", "Operations", "on", "Collections");
        strings.stream().min(Comparator.comparing(String::length))
                .ifPresent(System.out::println); // Output: on
    }
}

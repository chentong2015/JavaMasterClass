package JavaLambdaStreams;

import JavaLambdaStreams.base.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Streams: 流
// 1. 一个有顺序的序列的数据
// 2. 提供公共IO model: read and write
// 3. Stream types are unidirectional 只提供一个方向的操作
public class BaseStreams {

    // Streams的分类
    // 1. Byte Streams: interact as binary data
    // 2. Text Streams: interact as unicode characters

    // TODO: After the terminal operation is performed,
    //       the stream pipeline is consumed and can't be used anymore
    // 注意Stream pipeline被重复消费的情况 !!
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
                new Product(23d, "potatoes"),
                new Product(15d, "orange"),
                new Product(13d, "lemon"),
                new Product(23d, "bread"),
                new Product(26d, "orange"));

        // 1. 构建指定的Streams
        Stream<Product> orangeStream = productList.stream()
                .filter(product -> product.getName().equals("orange"));

        List<Double> originalOranges = orangeStream.map(product -> product.getPrice())
                .collect(Collectors.toList());

        // 2. 由于前面已经使用过.collect() Terminal Operations
        //    因此不能重复消费，报错.IllegalStateException
        List<Double> disCountedOranges = orangeStream.map(product -> product.getPrice() * 0.95)
                .collect(Collectors.toList());
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

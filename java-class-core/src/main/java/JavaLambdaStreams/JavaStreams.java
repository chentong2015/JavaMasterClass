package JavaLambdaStreams;

import JavaLambdaStreams.base.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Streams流: 代表元素有限或者无需的顺序
// 有顺序的序列的数据，提供公共读写IO Unidirectional单向操作，不存储数据
public class JavaStreams {

    // TODO: Stream pipeline: 代表元素的多级运算
    //  1. 包含多个中间操作(intermediate operation)和终止操作(Terminal Operation)
    //  2. 通常是Lazy的, 直到中止操作才开始, 如果无终止操作, 则是一个无操作的指令
    // They always return a new stream. This allows the operations to be connected.
    // They don't process the elements until a terminal operation is invoked.
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(new Product(23d, "potatoes"),
                new Product(15d, "orange"), new Product(13d, "lemon"),
                new Product(23d, "bread"), new Product(26d, "orange"));

        Stream<Product> orangeStream = productList.stream()
                .filter(product -> product.getName().equals("orange"));

        List<Double> originalOranges = orangeStream.map(Product::getPrice)
                .collect(Collectors.toList());

        // TODO. 执行终端操作后，orangeStream流管道被视为已消耗，无法再使用
        // After the terminal operation is performed, the stream pipeline is consumed and can't be used anymore
        List<Double> disCountedOranges = orangeStream
                .map(product -> product.getPrice() * 0.95)
                .toList();
    }
}

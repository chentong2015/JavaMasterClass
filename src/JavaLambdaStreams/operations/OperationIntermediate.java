package JavaLambdaStreams.operations;

import java.util.stream.Stream;

// Intermediate operation:
// 中间操作返回一个新流，延迟执行
// 执行诸如filter()之类的中间操作实际上并不执行任何过滤，而是创建一个新的流
// 该新流在遍历时将包含与给定谓词匹配的初始流的元素，在执行管道的终端操作之前，不会开始遍历管道源
public class OperationIntermediate {

    // They always return a new stream. This allows the operations to be connected.
    // They don't process the elements until a terminal operation is invoked;
    public void testIntermediateOperations() {
        Stream<String> s = Stream.of("m", "k", "c", "t")
                .sorted()
                .limit(3);
    }

}

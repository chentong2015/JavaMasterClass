package JavaLambdaStreams;

import JavaLambdaExpressions.base.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

// TODO. 不要任意并行Streams Pipeline
// 1. 在Streams上通过并行获得性能：使用能够轻松划分成任意大小的子范围的数据结构
//    ArrayList, HashMap, HashSet, ConcurrentHashMap, Array, int范围
// 2. 并行Stream可能会减低性能，导致结果出错
//    并行的Stream Pipeline都在一个通用的fork-join池中运行，一个pipeline异常会损害系统中其他不相关的性能
// 3. 只有在"适当"情况下，添加.parallel()才能在多核处理器下实现近乎线程的倍增

// Parallel streams split the stream into multiple parts.
// Each part is processed by a different thread at the same time (in parallel).
public class ParallelStreams {

    // TODO: .stream().parallel() & .parallelStream() 执行逻辑
    // Returns a possibly parallel Stream with this collection as its source
    // When a stream executes in parallel, the Java runtime partitions the stream into multiple sub_streams.
    // Aggregate operations iterate over and process these sub_streams in parallel and then combine the results.
    private static void testParallelism() {
        List<Employee> employees = new ArrayList<>();
        Map<String, List<Employee>> byGender = employees
                .parallelStream()
                .collect(Collectors.groupingBy(Employee::getGender));

        // Concurrent reduction
        // operation Collectors.toConcurrentMap performs better with parallel streams than Collectors.toMap
        ConcurrentMap<String, List<Employee>> byGenderConcurrent = employees
                .parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getGender));

        System.out.println(byGender);
        System.out.println(byGenderConcurrent);
    }

}

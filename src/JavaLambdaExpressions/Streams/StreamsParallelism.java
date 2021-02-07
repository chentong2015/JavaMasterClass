package JavaLambdaExpressions.Streams;

import JavaLambdaExpressions.Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * TODO: 并行的操作Streams
 * .parallelStream(): Returns a possibly parallel Stream with this collection as its source
 * When a stream executes in parallel, the Java runtime partitions the stream into multiple sub_streams.
 * Aggregate operations iterate over and process these sub_streams in parallel and then combine the results.
 */
public class StreamsParallelism {

    private static void testParallelism() {
        List<Employee> employees = new ArrayList<>();

        Map<String, List<Employee>> byGender = employees.parallelStream()
                .collect(Collectors.groupingBy(Employee::getGender));

        // Concurrent reduction
        // operation Collectors.toConcurrentMap performs better with parallel streams than the operation Collectors.toMap
        ConcurrentMap<String, List<Employee>> byGenderConcurrent = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getGender));

    }

}

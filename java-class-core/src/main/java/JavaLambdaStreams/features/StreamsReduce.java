package JavaLambdaStreams.features;

import JavaLambdaExpressions.base.Employee;

import java.util.*;
import java.util.stream.IntStream;

// Streams Reduction:
// 对流元素进行相邻数据的迭代处理，对元素进行删减/排除, 返回Optional结果
public class StreamsReduce {

    // TODO. 直接使用.reduce()统计一组数据和
    public static void main(String[] args) {
        int[] value = {1, 2, 3, 4, 5};
        int total = Arrays.stream(value)
                .reduce(0, Integer::sum);
        System.out.println(total);

        OptionalInt optionalInt = IntStream.of(1, 2, 3, 4, 5, 6)
                .reduce(Integer::sum);
        System.out.println(optionalInt.getAsInt());

        int total2 = IntStream.of(1, 2, 3, 4, 5, 6).reduce(0, Integer::sum);
        int total3 = IntStream.of(1, 2, 3, 4, 5, 6).reduce(0,(a, b) -> a+b);
        System.out.println(total2);
        System.out.println(total3);
    }

    // TODO. 自定义Reduce相邻元素的Accumulator比较器/累计器
    private static void testStreamsReduce(List<Employee> employees) {
        // 计算所有员工年龄总和
        Integer totalAge = employees.stream()
                .mapToInt(Employee::getAge)
                .reduce(0, Integer::sum);

        // 找到最小年龄的员工
        Optional<Employee> minAgeEmployee = employees.stream()
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2);
    }
}
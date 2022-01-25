package JavaLambdaStreams;

import JavaLambdaExpressions.base.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Reduction对stream element进行一个删减/排除, 返回一个optional的结果
public class StreamsReduction {

    private static void testStreamsReduce() {
        List<Employee> employees = new ArrayList<>();

        // 计算年龄的总和
        /**
         * int identity 初始值，默认值,
         * java.util.function.IntBinaryOperator op 累加器
         */
        Integer totalAge = employees.stream()
                .mapToInt(Employee::getAge)
                .reduce(0, (a, b) -> a + b);

        // 找到最小年龄的员工
        employees.stream()
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println); // Employee有重写toString()方法

        // 计算每种性别的年龄总和
        /**
         * U identity, 初始值，默认值
         * @NotNull java.util.function.Function<? super T, ? extends U> mapper, 隐射器：取Employee对象的年龄
         * @NotNull java.util.function.BinaryOperator<U> op 二元运算操作
         */
        Map<String, Integer> totalAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.reducing(0, Employee::getAge, Integer::sum))); // 操作分组的Streams

        // 计算每种性别的平均年龄
        Map<String, Double> averageAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge))); // 操作分组的Streams
    }

}

package JavaLambdaStreams;

import JavaLambdaExpressions.base.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Streams Reduction: 对流元素进行相邻数据的迭代处理，对元素进行删减/排除, 返回optional的结果
public class BaseStreamsReduction {

    // 使用.reduce()直接统计一组数据的累加和，添加计算方法
    private void testReduction() {
        OptionalInt total = IntStream.of(1, 2, 3, 4, 5, 6).reduce(Integer::sum);
        int total2 = IntStream.of(1, 2, 3, 4, 5, 6).reduce(0, Integer::sum);
        System.out.println(total);
        System.out.println(total2);
    }

    private static void testStreamsReduce() {
        List<Employee> employees = new ArrayList<>();

        // 计算年龄的总和
        // int identity 初始值，默认值,
        // java.util.function.IntBinaryOperator op 累加器
        Integer totalAge = employees.stream()
                .mapToInt(Employee::getAge)
                .reduce(0, (a, b) -> a + b);

        // 找到最小年龄的员工
        employees.stream()
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println); // Employee有重写toString()方法

        // 计算每种性别的年龄总和
        // U identity, 初始值，默认值
        // @NotNull Function<? super T, ? extends U> mapper, 隐射器：取Employee对象的年龄
        // @NotNull BinaryOperator<U> op 二元运算操作
        Map<String, Integer> totalAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.reducing(0, Employee::getAge, Integer::sum))); // 操作分组的Streams

        // 计算每种性别的平均年龄
        Map<String, Double> averageAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge))); // 操作分组的Streams
    }
}

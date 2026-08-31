package JavaStream.features;

import JavaLambdaExpression.base.Department;
import JavaLambdaExpression.base.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO. flatMap将一个类型对象Map映射到另一个Stream
public class StreamsFlatMap {

    public static void main(String[] args) {
        List<String> numList = Arrays.asList("11", "2", "3", "four", "5", "6", "7", "8");
        numList.stream()
                .filter(str -> !str.contains("3"))
                .flatMap(str -> Stream.of(str + " " + " right? "))
                .map(str -> str.split(" ")[0])
                .limit(3)
                .skip(1)
                .sorted()
                .forEach(System.out::println);
    }

    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    // 1. 传递一个参数, 返回一个Stream<R>/或它的任何subtypes(明确存在extends关系)
    // 2. 通过将提供的映射函数应用于每个元素而生成的映射流的内容而得到的结果
    private static void testFlatMap(List<Department> departments) {
        // 遍历所有部门下面的所有员工
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        // 对所有的员工按照年龄分组
        Map<Integer, List<Employee>> employeesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge));

        // 对所有的员工按照年龄分组，每组中只存储员工姓名
        Map<Integer, List<String>> namesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge,
                        Collectors.mapping(Employee::getName, Collectors.toList())));

        // 计算所有员工的平均年龄
        OptionalDouble average = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .mapToInt(Employee::getAge) // returns a new stream of type IntStream
                .average(); // Terminal Operation, returns OptionalDouble
    }
}

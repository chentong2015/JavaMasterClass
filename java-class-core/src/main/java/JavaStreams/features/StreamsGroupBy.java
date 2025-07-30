package JavaStreams.features;

import JavaLambdaExpressions.base.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO. 对特定数据流按照classifier进行分组，然后收集分组结果
public class StreamsGroupBy {

    private static void testStreamsReduce(List<Employee> employees) {
        // 计算每种性别的年龄总和
        Map<String, Integer> totalAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.reducing(0, Employee::getAge, Integer::sum)));

        // 计算每种性别的平均年龄
        Map<String, Double> averageAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.averagingInt(Employee::getAge)));
    }
}

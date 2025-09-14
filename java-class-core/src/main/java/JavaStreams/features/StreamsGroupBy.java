package JavaStreams.features;

import JavaLambdaExpressions.base.Employee;
import JavaStreams.base.RecordComment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO. Stream流分组的操作
// groupingBy() 根据特定的属性进行分组
// reducing() averagingInt() 对每组没的数据进行计算处理
public class StreamsGroupBy {

    private static void testStreamsReduce(List<Employee> employees) {
        // 计算每种性别的年龄总和
        Map<String, Integer> totalAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.reducing(0, Employee::getAge, Integer::sum)));

        // 计算每种性别的平均年龄
        Map<String, Double> averageAgeByGender = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
    }

    // TODO. 将List<RecordComment>分组成Map<String, List<KzRecordComment>>
    public static void main(String[] args) {
        List<RecordComment> commentList = Arrays.asList(
            new RecordComment("1", "Comment A"),
            new RecordComment("2", "Comment B"),
            new RecordComment("1", "Comment C"));
        Map<String, List<RecordComment>> groupedMap = commentList.stream()
                .collect(Collectors.groupingBy(RecordComment::getOid));

        groupedMap.forEach((key, value) -> {
            System.out.println("OID: " + key);
            value.forEach(comment -> System.out.println("  " + comment.getContent()));
        });
    }
}

package JavaLambdaStreams;

import JavaLambdaExpressions.base.Department;
import JavaLambdaExpressions.base.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Streams Aggregate Operations 集合流的聚合操作: > JDK 1.8
// 0. A pipeline is a sequence of aggregate operations
// 1. Aggregate operations process elements from a stream, not directly from a collection
// 2. Streams: A sequence of elements supporting and parallel aggregate operations 支持和并行聚合操作
// 3. Collectors: 实现各种有用的约简操作的Collector的实现，例如将元素累积到集合中，根据各种标准对元素进行汇总等 !!!!
public class BaseStreamsAggregate {

    // 1. 使用stream()时，不对源始数据造成影响
    // 2. 每一个操作都执行上一个操作所完成的结果，构成操作的链条
    // 3. 操作相互独立，不依赖于前一个操作的变量
    // 4. 必须是stateless无状态
    //    the result of an operation can't depend on any state outside of the operation
    private void testListStreams() {
        List<String> numbers = Arrays.asList("G15", "H67", "K56", "P56");
        List<String> backNumbers = new ArrayList<>();

        numbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                backNumbers.add(number);
            }
        });

        // backNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        backNumbers.sort(Comparator.naturalOrder()); // 以自然顺序比较可比较对象, 默认是字母排序大小的先后顺序 
        backNumbers.forEach((String s) -> System.out.println(s));

        numbers.stream() // Returns a sequential Stream with this collection as its source 拿到原始数据的stream
                .map(String::toUpperCase)       // Function<? super T, ? extends R> mapper 使用method reference
                .filter(s -> s.startsWith("G")) // Predicate<? super T> predicate 过滤的条件，返回boolean判读的结果
                .sorted()                       // 按照自然比较进行排序
                .forEach(System.out::println);  // Consumer<? super T> action 消费者：forEach() is Terminal Operation
        List<String> backNumber = numbers.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("G")).sorted()
                .collect(Collectors.toList()); // Terminal Operation: 获取到Stream操作完成的结果

        /**
         * java.util.function.Supplier<R> supplier, 构造器的引用
         * java.util.function.BiConsumer<R, ? super T> accumulator, 累加器 for add single item to the list
         * java.util.function.BiConsumer<R, R> combiner 结合器 To improve the efficiency of the operation 由java runtime决定
         */
        List<String> backNumberTest = numbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
        // 下面代码等效于使用Collectors，收集之前操作处理的结果
        // .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    // 1. Function传递一个参数, 返回一个Stream<R>
    //    也可以是Stream<R>类型的任何subtypes, 但必须明确存在extends关系
    // 2. 通过flatMap可以将一个类型的对象 map -> 映射到另外一个Stream
    //    该流包括将流中的每个元素替换为: 通过将提供的映射函数应用于每个元素而生成的映射流的内容而得到的结果
    private static void testFlatMap() {
        Employee chen = new Employee("chen chen");
        Employee test = new Employee("OtherTech");
        Department hr = new Department("Human resources");
        Department accounting = new Department("Accounting");
        hr.addEmployee(chen);
        accounting.addEmployee(test);
        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        // 遍历所有部门下面的所有员工
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        // 对所有的员工进行分组 Collectors.groupingBy()
        Map<Integer, List<Employee>> employeesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge)); // 引用特定类型的"任意对象"的实例方法

        // 对所有的员工进行分组，只提取员工的姓名
        Map<Integer, List<String>> namesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                // 注意这里分组的key的类型，就是返回的map的key的值
                // 可以提供第二层的处理: 映射，统计，或者额外的分组...
                .collect(Collectors.groupingBy(Employee::getAge,
                        Collectors.mapping(Employee::getName, Collectors.toList()))); // 分组后提取的信息

        // 计算所有员工的平均年龄
        double average = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .mapToInt(Employee::getAge)// 将所有的Employee对象只映射到年龄上面 ==> returns a new stream of type IntStream
                .average()      // 对IntStream操作 Terminal Operation, returns OptionalDouble
                .getAsDouble(); // 拿到可能的返回结果
    }

    private void testCollectCollectors() {
        // 直接使用Collectors中提供的静态方法，见Stream<T>转变成List<T>
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toList());

        // partitioningBy() 划分之后，返回两组list数据
        Map<Boolean, List<Integer>> map = Stream.of(2, 34, 54, 23, 33, 20, 59, 11, 19, 37)
                .collect(Collectors.partitioningBy(i -> i > 10));
    }
}

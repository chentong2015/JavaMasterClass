package JavaLambdaExpressions.Streams;

import JavaLambdaExpressions.Model.Department;
import JavaLambdaExpressions.Model.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams Aggregate Operations 集合流的聚合操作                ====> C#区别: LINQ语言集成查询
 * 0. A pipeline is a sequence of aggregate operations.
 * 1. Aggregate operations process elements from a stream, not directly from a collection
 * 2. Streams: A sequence of elements supporting and parallel aggregate operations 支持和并行聚合操作
 * 3. Collectors: 实现各种有用的约简操作的Collector的实现，例如将元素累积到集合中，根据各种标准对元素进行汇总等 !!!!
 */
public class Steams {

    /**
     * 两种Operations的背后逻辑                                  =====> C#等效：LINQ延迟执行/立即执行和流处理
     * 1. Intermediate operation: 中间操作返回一个新的流, 延迟执行
     * 执行诸如filter()之类的中间操作实际上并不执行任何过滤，而是创建一个新的流，该新流在遍历时将包含与给定谓词匹配的初始流的元素。在执行管道的终端操作之前，不会开始遍历管道源。
     * 2. Terminal Operation: 终端操作可能会遍历该流以产生结果或副作用，立即开始执行
     * 执行终端操作后，流管道被视为已消耗，无法再使用；如果需要再次遍历同一数据源，则必须返回到数据源以获取新的流
     */
    private static void testStreamOperations() {
        Stream<String> ioNumberStream = Stream.of("I24", "O90", "A12");
        Stream<String> inNumberStream = Stream.of("N40", "I26", "O23");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        long total = concatStream.distinct()
                .peek(System.out::println) // Intermediate operation：从结果流中消耗元素时对每个元素另外执行提供的操作
                .count();
    }

    // 使用Arrays.stream(array)构建stream()
    private long testArrayStreams(int... array) {
        return array == null ? 0 : Arrays.stream(array).filter(i -> i == 9).count();
    }

    /**
     * 1. 使用stream()时，不对源始数据造成影响
     * 2. 每一个操作都执行上一个操作所完成的结果，构成操作的链条; 操作相互独立，不依赖于前一个操作的变量
     * 3. 必须是stateless无状态的，the result of an operation can't depend on any state outside of the operation
     */
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
                .map(String::toUpperCase)  // Function<? super T, ? extends R> mapper 直接引用已经存在的方法, 代替lambda表达式 !!!
                .filter(s -> s.startsWith("G"))  // Predicate<? super T> predicate 过滤的条件，返回boolean判读的结果
                .sorted() // 按照自然比较进行排序
                .forEach(System.out::println); // Consumer<? super T> action 消费者：forEach() is Terminal Operation

        List<String> backNumber = numbers.stream().map(String::toUpperCase).filter(s -> s.startsWith("G")).sorted()
                .collect(Collectors.toList()); // Terminal Operation: 获取到Stream操作完成的结果

        /**
         * java.util.function.Supplier<R> supplier, 构造器的引用
         * java.util.function.BiConsumer<R, ? super T> accumulator, 累加器 for add single item to the list
         * java.util.function.BiConsumer<R, R> combiner 结合器 To improve the efficiency of the operation 由java runtime决定
         */
        List<String> backNumberTest = numbers.stream().map(String::toUpperCase).filter(s -> s.startsWith("G")).sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
     * 1. Function传递一个参数, 返回一个Stream<R> ==> 也可以是Stream<R>类型的任何subtypes, 但必须明确存在extends关系 !!!
     * 2. 通过flatMap可以将一个类型的对象 map -> 映射到另外一个Stream
     * 该流包括将流中的每个元素替换为: 通过将提供的映射函数应用于每个元素而生成的映射流的内容而得到的结果
     */
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
                .flatMap(department -> department.getEmployees().stream()) // 返回新的操作源
                .forEach(System.out::println);

        // 对所有的员工进行分组 Collectors.groupingBy()
        Map<Integer, List<Employee>> employeesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge)); // 引用特定类型的"任意对象"的实例方法

        // 对所有的员工进行分组，只提取员工的姓名
        Map<Integer, List<String>> namesByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge,  // 分组的依据
                        Collectors.mapping(Employee::getName, Collectors.toList()))); // 分组后提取的信息

        // 计算所有员工的平均年龄
        double average = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .mapToInt(Employee::getAge)// 将所有的Employee对象只映射到年龄上面 ==> returns a new stream of type IntStream
                .average()// 对IntStream操作 Terminal Operation, returns OptionalDouble
                .getAsDouble(); // 拿到可能的返回结果
    }
}

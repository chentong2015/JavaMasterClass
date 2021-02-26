package JavaLambdaExpressions;

import JavaLambdaExpressions.Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

// Java.util.Function Package 包含约30个Functional interfaces, 提供丰富的lambda表达式的使用场景
// Consumer 消费者，有输入没有输出             > void accept(T t);      --> Can chain
// BiConsumer                              > void accept(T t, U u);

// Supplier 提供者，没有输入有输出             > T get();

// Function 功能，既有输入又有输出             > R apply(T t);           --> Can chain
// BiFunction                              > R apply(T t, U u);
// UnaryOperator<T> extends Function<T, T> 输入输出参数的类型一致性

// Predicate 预测，测试条件并放回bool值        > boolean JavaUnitTestExceptions.test(T t);       --> Can chain
// BiPredicate                              > boolean JavaUnitTestExceptions.test(T t, U u);
public class FunctionalInterfaces {

    private List<Employee> employees = new ArrayList<>();

    // 1. 抽象迭代每个对象需要执行的操作 void forEach(Consumer<? super T> action) {}
    private void testConsumer() {
        employees.forEach((employee -> {
            if (employee.getName() == "chen") {
                System.out.println("Found");
            }
        }));
    }

    // 1. 支持Primitive type类型的Supplier: int, double, boolean, long
    // 2. Supplier可以提供指定类型的对象，在App测试过程中提供随机的测试数据 !!
    private void testSupplier() {
        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(100);
        int randomNum = randomSupplier.get();
    }

    // 0. 支持Primitive type类型的Function: int -> long, int -> double ...
    // 1. Function可使用在CallBack回调机制上，根据不同state状态执行不同判断的操作 !!
    // 2. 每一种CallBack回调机制提供一种特殊的算法，在不同的Step可运行指定的传入算法
    private void testFunction() {
        Function<Employee, String> getFullName = (Employee employee) -> employee.getName();
        String fullName = getFullName.apply(employees.get(0));

        // 可以组合Function, 连续操作 => 注意在组合的时候，需要注意参数的匹配和传递
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> getFirstName = name -> name.substring(0, name.indexOf(' '));
        Function<Employee, String> chainedFunction = upperCase.andThen(getFirstName);
        String firstName = chainedFunction.apply(employees.get(0));

        // 2.  BiFunction 支持传递两个参数, 返回一个结果值
        BiFunction<String, Employee, String> concatName = (name, employee) -> name.concat("+" + employee.getName());
    }

    // 0. UnaryOperator<T> 返回值的类型和参数的类型保持一致
    private void testUnaryOperator() {
        IntUnaryOperator increaseBy5 = i -> i + 5;
        int result = increaseBy5.applyAsInt(10);
    }

    private void testPredicate() {
        // 0. 使用基本类型的Predicate
        IntPredicate greaterThan15 = count -> count > 10;
        IntPredicate lessThan100 = count -> count < 100;
        boolean result = greaterThan15.test(10);
        // 1. 可同时组合两种判断条件
        boolean result1 = greaterThan15.and(lessThan100).test(25);
        boolean result2 = greaterThan15.or(lessThan100).test(20);

        // 2. 将Predicate作为参数：实际参数Lambda表达式需要匹配Predicate接口需要实现的方法的方法原型
        printEmployeesByName(employees, (employee -> employee.getName() == "chen"));
    }

    private static void printEmployeesByName(List<Employee> employees, Predicate<Employee> nameCondition) {
        for (Employee employee : employees) {
            if (nameCondition.test(employee)) {
                System.out.println("Found");
            }
        }
    }
}

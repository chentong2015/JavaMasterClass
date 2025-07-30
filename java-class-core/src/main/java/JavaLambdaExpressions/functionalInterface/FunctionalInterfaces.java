package JavaLambdaExpressions.functionalInterface;

import JavaLambdaExpressions.base.Employee;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FunctionalInterfaces {

    // 抽象迭代每个对象需要执行的操作
    // void forEach(Consumer<? super T> action) {}
    private void testConsumer(List<Employee> employees) {
        employees.forEach((employee -> {
            if (employee.getName() == "chen") {
                System.out.println("Found");
            }
        }));
    }

    // Supplier提供Primitive type类型数据: int, double, boolean, long
    // Supplier提供指定T类型的对象，用于在测试过程中提供随机数据
    private void testSupplier() {
        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(100);
        int randomNum = randomSupplier.get();
    }

    // TODO: Function用于CallBack回调机制, 调用.apply()获取返回值
    // 支持Primitive type类型的Function: int -> long, int -> double ...
    // 每种CallBack回调机制提供特殊算法，在不同Step可运行指定的传入算法
    private void testFunction(List<Employee> employees) {
        Function<Employee, String> getFullName = (Employee employee) -> employee.getName();
        String fullName = getFullName.apply(employees.get(0));

        // 可以组合Function, 连续操作 => 注意在组合的时候，需要注意参数的匹配和传递
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> getFirstName = name -> name.substring(0, name.indexOf(' '));

        Function<Employee, String> chainedFunction = upperCase.andThen(getFirstName);
        String firstName = chainedFunction.apply(employees.get(0));

        // BiFunction 支持传递两个参数, 返回一个结果值
        BiFunction<String, Employee, String> concatName = (name, employee)
                -> name.concat("+" + employee.getName());
    }

    // UnaryOperator<T> 返回值的类型和参数的类型保持一致
    private void testUnaryOperator() {
        IntUnaryOperator increaseBy5 = i -> i + 5;
        int result = increaseBy5.applyAsInt(10);
    }

    private void testPredicate() {
        IntPredicate greaterThan15 = count -> count > 10;
        IntPredicate lessThan100 = count -> count < 100;
        boolean result = greaterThan15.test(10); // 进行预测

        // 同时组合两种判断条件
        boolean result1 = greaterThan15.and(lessThan100).test(25);
        boolean result2 = greaterThan15.or(lessThan100).test(20);
    }

    // 将Predicate作为参数：实际参数Lambda表达式需要匹配Predicate接口需要实现的方法原型
    private static void printEmployeesByName(List<Employee> employees, Predicate<Employee> nameCondition) {
        for (Employee employee : employees) {
            if (nameCondition.test(employee)) {
                System.out.println("Found");
            }
        }
    }
}

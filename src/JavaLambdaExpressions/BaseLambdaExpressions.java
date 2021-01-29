package JavaLambdaExpressions;

import JavaLambdaExpressions.Model.Employee;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 1. After Java 8 version
 * 2. 优化只有一个方法的接口的方法调用 @FunctionalInterface         ====> C#区别：Lambda表达式是一种匿名方法
 * 3. 优化只有一个方法的(匿名)类型的方法调用
 */
public class BaseLambdaExpressions {

    private static void testLambdaExpressions() {
        // 1. 使用类型实例 new Thread(new CodeToRun()).start();

        // 2. 使用匿名类型
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing runnable");
            }
        }).start();

        // 3. 使用Lambda表达式
        // 编译器背后的逻辑：Thread()接受一个实现了Runnable接口的类型实例
        // 由于接口中只含有一个public abstract void run();抽象方法，且方法不具备输入参数，也不输出参数
        // 于是编译器将Lambda表达式match(maps to)到该方法，完成对方法的"实现"
        new Thread(() -> {
            System.out.println("Printing ...");
            System.out.format("This is line %d \n", 3);
        }).start();
    }

    public static int testCompare(Employee employee1, Employee employee2) {
        return employee1.getName().compareTo(employee2.getName());
    }

    private static double testDouble(double x) {
        return x + 10;
    }

    private static void testString(String value) {
        System.out.println(value);
    }

    private void testFunctionalInterface() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("chen"));
        employees.add(new Employee("tong"));

        // 1. 使用原始的排序方式
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        // Action<String> test 没有这个类型或者接口， 不是C#委托的概念 !!!! ===> 对方法的引用 !! 可将方法传递给参数

        Function<Double, Double> square = BaseLambdaExpressions::testDouble;
        square.apply(10.0);

        // Comparator<Employee, String> comparator = Comparator.comparing(Employee::getName);

        Set<String> set = new HashSet<>();
        Predicate<String> pred = set::contains;

        // 2. 使用基本Lambda表达式
        Collections.sort(employees, (Employee e1, Employee e2) -> {
            return e1.getName().compareTo(e2.getName());
        });

        // 3. 优化Lambda表达式 ::
        // Accepts a function that extracts a Comparable sort key from a type T, and returns a Comparator<T> that compares by that sort key
        // Employee employee = new Employee("test");
        // Collections.sort(employees, Comparator.comparing(employee::getName));


        Consumer<Integer> b1 = System::exit;
    }

}


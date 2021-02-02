package JavaLambdaExpressions;

import JavaLambdaExpressions.Model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 1. > Java 8
 * 2. 优化只有一个方法的接口的方法调用             ====> C#区别：Lambda表达式是一种匿名方法
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
        // 由于接口中只含有一个public abstract void run(); 抽象方法，且方法不具备输入参数，也不输出参数
        // 于是编译器将Lambda表达式match(maps to)到该方法，完成对方法的"实现"
        new Thread(() -> {
            System.out.println("Printing ...");
            System.out.format("This is line %d \n", 3);
        }).start();
    }

    private void testFunctionalInterface() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("chen"));

        // 1. 使用基本排序方式
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        // 2. 使用Lambda表达式
        Collections.sort(employees, (Employee e1, Employee e2) -> {
            return e1.getName().compareTo(e2.getName());
        });
        Collections.sort(employees, (e1, e2) -> {
            return e1.getName().compareTo(e2.getName());
        });

        // 3. 优化Method Reference ::
        Collections.sort(employees, Comparator.comparing(Employee::getName));
    }
}


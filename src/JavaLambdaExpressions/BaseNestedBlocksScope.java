package JavaLambdaExpressions;

import JavaLambdaExpressions.Model.IStringUpperConcat;

import java.util.ArrayList;
import java.util.List;

// Lambda expressions的作用域和变量捕获        ====> C#区别：匿名方法中捕获变量(闭包), 与环境(变量集)进行更大程度的互动
// 1. "匿名方法能够使用声明该匿名方法的方法内部定义的局部变量"  ==> 可以避免创建额外的类来存储信息
// 2. 外部变量: 在它的作用域中声明了一个匿名方法， 并且匿名方法可以对它进行捕获 ==> 外部变量的更改在匿名方法内部是可见的
// 3. "当一个变量被捕获时，捕获的是变量的<实例>"   ==> 如果是在循环内部，则会创建多个实例化对象(被捕获), 彼此之前不共享
// 4. 捕获变量生命周期: 只要引用还在，则一直存在
public class BaseNestedBlocksScope {

    private void testNestedBlock() {
        IStringUpperConcat ucInside = ((str1, str2) -> {
            System.out.println(getClass().getSimpleName()); // 返回当前类型的名称
            return str1.toUpperCase() + str2.toUpperCase();
        });

        // 等效于 Nested code block
        {
            String str1 = "";
            String str2 = "";
            System.out.println(getClass().getSimpleName());
            // ...
        }
    }

    /**
     * 在Lambda expressions中所使用的作用域中的饿局部变量，必须声明成final或者确定不变
     * 1. 由于声明的Lambda表达式可能不会立即执行(Thread), 方法被调用后exit，局部变量将被回收, 对表达式中的访问造成影响 !!
     * 2. 对于在表达式内部声明的变量，可自定义修改
     */
    private void testLambdaScope() {
        final int localVariable = 0;
        IStringUpperConcat ucOuter = ((str1, str2) -> {
            String insideVar = "inside variable";
            insideVar = "reset variable";
            System.out.println("Count = " + localVariable);
            return str1.toUpperCase() + str2.toUpperCase();
        });
    }

    /**
     * 为什么testNumbers不需要标明final ?
     * 在循环中，testNumbers list中的对象在变化，但是list中所存储的对象的引用没有变化 ==> testNumbers是effectively final
     */
    private void testLambdaScopeComplex() {
        List<String> numbers = new ArrayList<>();
        // Add data to numbers ...
        List<String> testNumbers = new ArrayList<>();
        numbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                testNumbers.add(number);
            }
        });
    }

    /**
     * 特别注意：在使用循环时，lambda表达式中所使用的变量是否是不变的 !!          ====> C#区别：局部变量的实例化
     * 1. 当使用Foreach时，每次迭代出来的确定不变的，可直接在Lambda expressions访问
     * 2. 如果将变量声明在循环之外，在迭代过程中会变化，则不能在Lambda expressions访问
     */
    private void testForScope() {
        List<String> array = new ArrayList<>();
        for (String item : array) {
            System.out.println(item);
            new Thread(() -> System.out.println(item));
        }

        for (int i = 0; i < array.size(); i++) {
            String item = "item" + i;
            new Thread(() -> System.out.println(item));
        }

        final String item = "item";
        for (int i = 0; i < array.size(); i++) {
            // item = "item" + i;
            new Thread(() -> System.out.println(item));
        }
    }
}

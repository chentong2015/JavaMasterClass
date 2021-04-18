package JavaLambdaExpressions;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

/**
 * :: (syntactic sugar语法糖)对方法的引用, 可将方法作为参数传递             =====> C#区别: 类似Delegate委托机制(Action<>, Func<>)
 * 1. Method references are expressions which have the same treatment as lambda expressions (...),
 * .  but instead of providing a method body, they refer an existing method by name.
 * 2. Lambda expression invokes an existing method, you can use a method reference
 * 如果Lambda表达式invoke的是一个存在的方法，那么可以使用方法引用 !!!
 *
 * @FunctionalInterface 表示只有一个抽象方法的接口(在实现的时候, 只需要实现一个方法)
 * Runnable, Callable, Comparator, ActionListener...
 * Function, Predicate, Consumer, Supplier...
 */
public class BaseMethodReference {

    // IntBinaryOperator是被标记成@FunctionalInterface的接口, 表示它能够使用Lambda表达式进行优化
    public static OptionalInt reduce(IntBinaryOperator op) {
        // return evaluate(ReduceOps.makeInt(op));
        return OptionalInt.empty();
    }

    /**
     * 以下3种调用方式会产生不同的bytecode字节码，但是在语义上是平等
     * 这将由编译器而不是运行时的JVM解释
     */
    private static void testMethodReference() {
        // 1. 创建匿名类型，调用reduce方法
        reduce(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return Math.max(left, right);
            }
        });

        // 2. 使用Lambda表达式
        reduce((int left, int right) -> Math.max(left, right));

        // 3. 使用方法引用：所引用的方法完全满足重写的方法，和Lambda表达式的声明(方法签名一致) !!
        // Defined in Math：public static int max(int a, int b)
        reduce(Math::max);
    }

    private int testInstanceMethod(int value) {
        return value + 1;
    }

    private static int testStaticMethod(String value) {
        return 0;
    }

    private static void testReferences() {
        // 1. 对实例方法的引用: 引用指定对象的方法(Invoke the method that is part of the object instance)
        BaseMethodReference instance = new BaseMethodReference();
        Function<Integer, Integer> refInstanceMethod = instance::testInstanceMethod;
        refInstanceMethod.apply(10);

        // 2. 对静态方法的引用
        Function<String, Integer> refStaticMethod = BaseMethodReference::testStaticMethod;
        refStaticMethod.apply("Test");

        // 3. 引用特定类型的"任意对象"的实例方法: ContainingType::methodName
        String[] stringArray = {"Barbara", "James", "Mary", "John"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        // 4. 对泛型的实例构造器的引用: 具有类型推断
        // Supplier<MyClass<Integer>> obj = MyClass::new;
    }
}

package JavaLambdaExpression.functionalInterface;

import java.util.function.Function;

// TODO: @FunctionalInterface注解的含义(本质和@Override类似)
// 1. 表明这个接口只针对Lambda设计
// 2. 该接口不会进行编译，除非它只有一共抽象方法
@FunctionalInterface
public interface CustomFunctionalInterface<T> {

    // 实际需要实现的方法, 被lambda表达式或者方法引用赋值
    int compare(T t1, T t2);

    // 在现有比较条件下，如果相等，则继续使用新的比较器进行比较
    default CustomFunctionalInterface<T> thenComparing(CustomFunctionalInterface<T> newComparator) {
        return (p1, p2) -> compare(p1, p2) == 0 ? newComparator.compare(p1, p2) : compare(p1, p2);
    }

    // TODO: 通过thenComparing()方法实现了一个Builder效果，可以联系调用
    default CustomFunctionalInterface<T> thenComparing(Function<T, Comparable> function) {
        return thenComparing(comparing(function));
    }

    // 将Function interface作为参数传递，并使用其实现的方法逻辑
    // 1. 提供泛型类型做处理
    // 2. 使用能够比较的类型Comparable, 调用默认方法.compareTo(), 同时支持不同类型的比较
    static <U> CustomFunctionalInterface<U> comparing(Function<U, ? extends Comparable> function) {
        return (p1, p2) -> function.apply(p1).compareTo(function.apply(p2));
    }
}

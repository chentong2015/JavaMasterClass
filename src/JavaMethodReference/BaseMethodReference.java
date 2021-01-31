package JavaMethodReference;

// Java 8 之后支持对方法的引用, 可将方法作为参数传递        =====> C#区别: Delegate 委托机制(Action<>, Func<>)

// https://stackoverflow.com/questions/20001427/double-colon-operator-in-java-8#:~:text=%3A%3A%20is%20a%20new%20operator,static%20methods%20of%20a%20class.&text=The%20only%20prerequisite%20for%20referring,compatible%20with%20the%20method%20reference.

/**
 * public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
 * Objects.requireNonNull(keyExtractor);
 * return (Comparator<T> & Serializable) (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
 * }
 * <p>
 * keyExtractor.apply(c1)  ===>
 * T.instanceMethodX() => U的类型值 .compareTo的方法 来比较两个类型的值
 * U extends Comparable<? super U> 这里的约束，说明U泛型一定实现了Comparable接口，所以能够调用compareTo方法来比较类型
 * String Comparable<
 * return (Comparator<T> & Serializable) (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
 * & 的运算表示 和传入的方法具有同样的可序列化性
 * <p>
 * <p>
 * public static <T, U> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
 * Objects.requireNonNull(keyExtractor);
 * Objects.requireNonNull(keyComparator);
 * return (Comparator<T> & Serializable)
 * (c1, c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2));
 * }
 */
public class BaseMethodReference {
}

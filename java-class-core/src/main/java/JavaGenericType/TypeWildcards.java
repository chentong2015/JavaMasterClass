package JavaGenericType;

import JavaGenericType.model.GenericModel;
import JavaGenericType.model.IGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * ? wildcards 通配符类型, 表示未知类型, match anything
 * 1. OK: Type of a parameter, field, or local variable, sometimes as a return type
 * 2. Not OK: type argument for a generic method invocation, a generic class instance creation, or a supertype.
 */
public class TypeWildcards<T extends GenericModel & IGeneric, E extends Comparable<? super E>>
        implements Comparable<TypeWildcards<T, E>> {

    /**
     * Unbounded Wildcards 无限通配符 ?
     * 使用条件：当需要使用到通用类型Object时, 同时逻辑不依赖于泛型参数
     * 1. 如果使用List<Object>, 由于List<Integer> List<String> ... 都不是List<Object>的SubType, 所以只能输出Object类型的list
     * 2. 任何具体类型的列表List<A>都是List<?>的SubType, 所以可以使用printList输出任何类型的list
     */
    public static void testUnboundedWildcards(List<?> list) { // 这里的泛型类型不受约束，都可以使用
        for (Object item : list) {
            System.out.println(item);
        }
    }

    /**
     * Upper Bounded Wildcards 上界通配符 ? extends ClassType > 不能和super同时使用
     * 表示匹配ClassType类型(自身)或者任何它的下级类型
     */
    private void testUpperBoundedWildcards() {
        // List<Number>要比下面所声明的List具有更大的约束 !!
        List<? extends Number> numbers;
    }

    /**
     * Lower Bounded Wildcards 下界通配符 ? super ClassType > 不能和extends同时使用
     * 表示匹配ClassType类型(自身)或者任何它的上级类型
     */
    private void testLowerBoundedWildcards(List<? super Integer> list) {
        // 根据替换原则, 任何Integer的上级类型的list都能够存放Integer类型的值
        for (int i = 1; i <= 10; i++) list.add(i);
    }

    /**
     * class A {}
     * class B extends A implements Comparable<A> { } B类型实现了它母类型的比较方法
     * class C extends A implements Comparable<C> { } C类型实现了它自身类型的比较方法
     */
    private static <T> void test(List<? extends Comparable<? super T>> list) {
        // List<B> listB = new ArrayList<>();
        // JavaUnitTestExceptions.test(listB); 类型推断T为B, 同时该类型实现了约束的指定类型的接口 !!!
    }

    @Override
    public int compareTo(TypeWildcards<T, E> team) {
        // 实现自身泛型类型的比较方法, 返回-1, 0, 1
        return 0;
    }

    /**
     * 结合通配符?的继承关系：所有的<E>泛型类型都是<?>的Subtype
     */
    private void testWildcardsSituations() {
        List<? super Integer> list = new ArrayList<>();
        // 向上 List<? super Number>
        // 向上 List<Number>

        List<? extends Number> numbers = new ArrayList<>();
        // 向下 List<? extends Integer>
        // 向下 List<Integer>
    }
}
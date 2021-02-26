package JavaGenerics;

import JavaGenerics.Base.GenericModel;
import JavaGenerics.Base.IGeneric;

import java.util.List;

/**
 * ? wildcards 通配符类型, 表示未知类型   ====> 最好遵循Java的强类型约束 !!!
 * 1. OK: Type of a parameter, field, or local variable, sometimes as a return type
 * 2. Not OK: type argument for a generic method invocation, a generic class instance creation, or a supertype.
 */
public class BaseWildcards<T extends GenericModel & IGeneric, E extends Comparable<? super E>> implements Comparable<BaseWildcards<T, E>> {

    /**
     * Unbounded Wildcards 无限通配符 ?
     * 使用条件：当需要使用到通用的类型Object时, 同时逻辑不依赖于泛型参数 !!
     * 1. 如果使用List<Object>, 由于List<Integer> List<String> ... 都不是List<Object>的SubType, 所以只能输出Object类型的list
     * 2. 任何具体类型的列表(List<A>)都是List<?>的SubType, 所以可以使用printList输出任何类型的list
     */
    public static void testUnboundedWildcards(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    /**
     * Upper Bounded Wildcards 上界通配符 ? extends ClassType  ====> 不能和super同时使用 !!   ======> 相当于"in"Variable   ===> C#逆变量
     * 表示匹配ClassType类型或者任何它的下级类型
     */
    private void testUpperBoundedWildcards() {
        // List<Number>要比下面所声明的List具有更大的约束 !!
        List<? extends Number> numbers;
    }

    /**
     * Lower Bounded Wildcards 下界通配符 ? super ClassType    ====> 不能和extends同时使用 !!  ======> 相当于"out"Variable ===> C#协变量
     * 表示匹配ClassType类型或者任何它的上级类型
     */
    private void testLowerBoundedWildcards(List<? super Integer> list) {
        // 任何Integer的上级类型的list都能够存放Integer类型的值   ====> 根据替换原则 !!
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    /**
     * class A {}
     * class B extends A implements Comparable<A> { } B类型实现了它母类型的比较方法
     * class C extends A implements Comparable<C> { } C类型实现了它自身类型的比较方法
     */
    private static <T> void test(List<? extends Comparable<? super T>> list) {
        // List<B> listB = new ArrayList<>();
        // JavaUnitTestExceptions.test(listB); 类型推断T为B, 同时该类型实现了约束的指定类型的接口 !!!

        // List<C> listC = new ArrayList<>();
        // JavaUnitTestExceptions.test(listC);
    }

    @Override
    public int compareTo(BaseWildcards<T, E> team) {
        return 0; // 类型对象的比较逻辑，返回-1, 0, 1
    }
}
package JavaGenerics;

import JavaGenerics.Base.Box;
import JavaGenerics.Base.Pair;

import java.util.ArrayList;
import java.util.List;

// 泛型的本质式参数化类型或者参数化多态
// 1. 保证类型的安全, 在编译的时候发现错误
// 2. 避免类型或者是方法的膨胀, 解决代码重复问题

// TODO. 类型参数名称规范
// T -> Type    任意的类型
// E -> Element 集合中元素
// K -> Key     键
// V -> Value   值
// R -> Result  方法返回类型
// T U V -> 2nd, 3rd, 4th
// T1, T2, T3   序列的类型
public class BaseGenericTypes<E> {

    // 在类型内部可以直接使用类型的泛型参数E
    private List<E> list = new ArrayList<>();

    // 泛型构造器：在创建类型对象时(调用构造器), 会根据实际的参数进行类型推断
    // BaseGenericTypes<Integer> myObject = new BaseGenericTypes<>("");
    // E类型推断成Integer, T类型推断成String
    <T> BaseGenericTypes(T t) {
    }

    /**
     * 泛型参数<T>写在泛型方法的返回类型之前
     */
    private static <K, V> boolean testCompare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    // TODO: 无法直接创建实例对象，泛型T不确定是什么类型，没有类型约束(具有构造器)
    private <T> void testGenericTypeInstance() {
        // T object = new T();
    }

    /**
     * Bounded Type Parameters 有界类型参数: 对类型参数进行指定的约束
     * extends表示泛型参数"extends"继承一个母类或者"implements"一个接口
     */
    private static <U extends Number> void testBoundedTypeParameters(U u) {
        System.out.println("U: " + u.getClass().getName());
    }

    /**
     * 约束类型参数是实现通用算法的关键
     * TODO: T泛型参数在类型擦除之后，成Object类型，该类型的对象不包含指定的比较方法，无法直接使用if(e > elem)大小比较
     */
    public static <T extends Comparable<T>> int countGreaterThanElem(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) count++; // 使用实现接口的方法来比较 !!
        }
        return count;
    }

    /**
     * 多重类型约束: 必须先Class(Java单继承语言，最多只能有一个), 后接Interface
     * Class A { }, interface B { }, interface C { }
     */
    private static void testMultipleBounds() {
        // class D <T extends A & B & C> { }
    }

    /**
     * TODO: 对于泛型类型: 不存在直接的(替换原则)关系
     * 1. MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related
     * 2. The common parent of MyClass<A> and MyClass<B> is Object.
     */
    public void boxTest(Box<Number> n) {
        // 这里不能传递Box<Integer> or Box<Double>, 因为两者和Box<Number>没有关系
        // 尽管Integer和Double都是Number子类
    }

    /**
     * 泛型类型之间必须存在extends or implements声明的subType关联 !!
     */
    private void testInheritanceSubTypes() {
        // 下面构成关系，可以替换
        // interface PayloadList<E,P> extends List<E> {
        //    void setPayload(int index, P val);
        // }

        // PayloadList<String,String> is subtype of List<String>
        // PayloadList<String,Exception> is subtype of List<String>
    }
}

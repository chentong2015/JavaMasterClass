package JavaGenerics;

import JavaGenerics.Model.Box;
import JavaGenerics.Model.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 保证类型的安全，在编译的时候发现错误
 * 2. 避免类型或者是方法的膨胀, 解决代码重复问题
 * 3. 名称规范 E -> Element, T -> Type, K -> Key, V -> Value, S U V -> 2nd, 3rd, 4th
 * 4. Type Inference 泛型类型实例化和泛型方法调用的时候，编译器会做出类型推断 !!
 */
public class BaseGenericTypes<E> {

    // 在类型内部可以直接使用类型的泛型参数 E
    private List<E> list = new ArrayList<>();

    // 可以申明泛型的构造器：在创建类型对象时(调用构造器), 会根据实际的参数进行类型推断
    <T> BaseGenericTypes(T t) {
        // BaseGenericTypes<Integer> myObject = new BaseGenericTypes<>(""); ===> X类型推断成Integer, T类型推断成String
    }

    /**
     * Row Type原始类型：在实例化泛型的对象时，不将类型参数特化出来，则该类型成为通用类型的原始类型: 一个非泛型的类型或者接口，不构成原始类型 !!
     * 1. Box is the raw type of the generic type Box<T>
     * 2. 这里的Box原始类型看做是类型参数为Object的类型，可将特化后的类型赋值给原始类型，反之不行
     * 3. 原始类型不能调用需要泛型参数的方法
     * 4. Box<String>特化泛型类型的时候，不能使用Primitive Types !!!
     */
    private static void testRawType() {
        Box<String> stringBox = new Box<>();
        Box rawBox = stringBox;
    }

    /**
     * 1. 泛型参数<T>写在泛型方法的返回类型之前                          ====> C#区别：泛型方法 public void Find<T>(T value) {}
     */
    private static <K, V> boolean testCompare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    /**
     * Bounded Type Parameters 有界类型参数: 对类型参数进行指定的约束     ===> C#区别：class GenericClass<T> where T : struct {}
     * extends表示泛型参数"extends"继承一个母类或者"implements"一个接口
     */
    private static <U extends Number> void testBoundedTypeParameters(U u) {
        System.out.println("U: " + u.getClass().getName());
    }

    /**
     * 约束类型参数是实现通用算法的关键
     * 直接使用 if (e > elem) 大小比较操作符只能使用在primitive types, 对于Object对象是无法直接比较 !!
     */
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) // 使用实现接口的方法来比较 !!
                ++count;
        }
        return count;
    }

    /**
     * 多重类型约束: 必须先Class(并且只有一个), 后接Interface
     * Class A {  }, interface B {  }, interface C {  }
     */
    private static void testMultipleBounds() {
        // class D <T extends A & B & C> { }
    }
}

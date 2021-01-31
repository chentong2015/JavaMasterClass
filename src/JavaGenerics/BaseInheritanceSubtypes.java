package JavaGenerics;

import JavaGenerics.Model.Box;

import java.util.ArrayList;
import java.util.List;

// 加入泛型之后，在理解继承关系上面，有所区别
public class BaseInheritanceSubtypes {

    /**
     * 对于具体的类型: 存在关系
     * 1. In object-oriented terminology, this is called an "is a" relationship  ===> 替换原则：子类必须能够替代基类型
     * 对于泛型的类型: 不存在关系
     * 2. MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related
     * 3. The common parent of MyClass<A> and MyClass<B> is Object. 两种类型共同的母类是Object
     */
    public void boxTest(Box<Number> n) {
        // 这里不能传递Box<Integer> or Box<Double>, 因为两者和Box<Number>没有关系
        // 尽管Integer和Double都是extends Number
    }

    /**
     * 4. 泛型类型之间必须存在extends or implements声明的subType关联 !!
     * interface PayloadList<E,P> extends List<E> {
     * .... void setPayload(int index, P val);
     * }
     */
    private static void testInheritanceSubTypes() {
        // 下面构成关系，可以替换
        // PayloadList<String,String> is subtype of List<String>
        // PayloadList<String,Exception> is subtype of List<String>
    }

    /**
     * 结合通配符?的继承关系：所有的<E>泛型类型都是<?>的Subtype
     */
    private static void testWildcardsSituations(List<? extends Number> list) {
        List<? super Integer> newList = new ArrayList<>();
        // 向下 List<? super Number>
        // 向下 List<Number>

        List<? extends Number> numbers = new ArrayList<>();
        // 向下 List<? extends Integer>
        // 向下 List<Integer>
    }
}

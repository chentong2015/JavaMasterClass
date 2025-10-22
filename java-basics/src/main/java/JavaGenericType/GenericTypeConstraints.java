package JavaGenericType;

// TODO. 添加泛型参数的约束: 确保泛型的实际类型满足特定的条件, 以便实现通用算法
public class GenericTypeConstraints {

    // Bounded Type Parameters: 有界类型参数
    // extends: 继承一个母类或者"implements"一个接口
    private static <U extends Number> void testBoundedTypeParameters(U u) {
        System.out.println("U: " + u.getClass().getName());
    }

    // T泛型参数在类型擦除之后为Object类型，该类型对象不包含指定的比较方法，无法直接使用if(e > elem)
    public static <T extends Comparable<T>> int countGreaterThanElem(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                count++; // 使用实现接口的方法来比较
            }
        }
        return count;
    }

    // TODO. 多重类型约束
    // 必须先定义继承Class(最多一个), 然后定义实现接口Interface
    // Class A { }, interface B { }, interface C { }
    private static void testMultipleBounds() {
        // class D <T extends A & B & C> { }
    }

    // TODO. 泛型之间不存在明显的继承关系, 不存在直接(替换原则)关系
    // 1. MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related
    // 2. The common parent of MyClass<A> and MyClass<B> is Object.
    // public void boxTest(Box<Number> n) {
    //
    //    虽然Integer和Double都是Number子类
    //    不能传递Box<Integer>或Box<Double>作为方法实际参数
    //    Box<Integer>或Box<Double>两者与Box<Number>没有关系
    // }

    // TODO. 泛型间必须存在extends || implements声明, 才能构成继承关联
    // 以下构成关系，可以替换
    // interface PayloadList<E,P> extends List<E> {
    //    void setPayload(int index, P val);
    // }
    // 
    // PayloadList<String,String> is subtype of List<String>
    // PayloadList<String,Exception> is subtype of List<String>
}

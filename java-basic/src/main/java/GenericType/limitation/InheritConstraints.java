package GenericType.limitation;

public class InheritConstraints {

    // TODO. 多重泛型类型约束: 单个继承类型, 多个实现接口
    class A { }
    interface B { }
    interface C { }

    class TC extends A implements B, C { }
    class D <T extends A & B & C> { }

    public void testMultipleBounds() {
        D<TC> dObject = new D<>();
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
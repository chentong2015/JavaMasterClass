package GenericType.limitation;

import java.util.List;

// TODO. 泛型之间不存在明显的继承关系, 不存在直接(替换原则)关系
//  泛型间必须存在extends || implements声明, 才能构成继承关联
// 1. MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related
// 2. The common parent of MyClass<A> and MyClass<B> is Object.
public class InheritConstraints {

    // 虽然Integer是Number子类,
    // 但List<Integer>类的实例不能传递给List<Number>类型参数, 两种类型没有父子关联
    private static void test(List<Number> list) {
        System.out.println(list);
    }

    // 虽然Integer是Object子类,
    // 但List<Integer>类的实例不能传递给List<Object>类型参数, 两种类型没有父子关联
    private static void test1(List<Object> list) {
        System.out.println(list);
    }

    // TODO. 方案1: 使用泛型类型
    private static <T> void test2(List<T> list) {
        System.out.println(list);
    }

    private static <T extends Integer> void test3(List<T> list) {
        System.out.println(list);
    }

    // TODO. 方案2: 使用?通配符类型
    private static void test4(List<?> list) {
        System.out.println(list);
    }

    private static void test5(List<? extends Integer> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        // test(list);
        // test1(list);

        test2(list);
        test3(list);
        test4(list);
        test5(list);
    }
}
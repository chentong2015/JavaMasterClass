package GenericType.limitation;

// TODO. 添加泛型参数的约束:
// 确保泛型的实际类型满足特定的条件, 实现通用(类型匹配)算法
public class BaseConstraints {

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
}

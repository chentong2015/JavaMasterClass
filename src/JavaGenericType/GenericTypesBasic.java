package JavaGenericType;

import JavaGenericType.model.Pair;

import java.util.ArrayList;
import java.util.List;

// 泛型的本质式参数化类型或者参数化多态
// 1. 保证类型的安全, 在编译的时候发现错误
// 2. 避免类型或者是方法的膨胀, 解决代码重复问题
public class GenericTypesBasic<E> {

    // T -> Type    任意的类型
    // E -> Element 集合中元素
    // K -> Key     键
    // V -> Value   值
    // R -> Result  方法返回类型
    // T U V -> 2nd, 3rd, 4th
    // T1, T2, T3   序列的类型

    // 在类型内部可以直接使用类型的泛型参数E
    private List<E> list = new ArrayList<>();

    // 泛型构造器：在创建类型对象时(调用构造器), 会根据实际的参数进行类型推断
    // GenericTypesBasic<Integer> myObject = new GenericTypesBasic<>("");
    // E类型推断成Integer, T类型推断成String
    <T> GenericTypesBasic(T t) {
    }

    // 泛型方法: 泛型参数<T>写在泛型方法的返回类型之前
    private static <K, V> boolean testCompare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    // TODO. 泛型方法的重载: <T>泛型的返回类型不属于方法的签名，因此会造成重载的问题
    <T> List<T> executeSqlQuery(String query, Class<T> returnType) {
        // 泛型约束: 无法直接通过泛型判断instance类型
        if (returnType.isAssignableFrom(String.class)) {
            System.out.println("check: return type is string");
        }
        return new ArrayList<>();
    }

    // <T> List<T> executeSqlQuery(String query, Class<T> returnType) {
    //     System.out.println("query2");
    //     return null;
    // }
}

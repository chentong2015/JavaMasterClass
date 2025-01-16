package JavaGenericType;

import JavaGenericType.model.Pair;

import java.util.ArrayList;
import java.util.List;

// TODO. 定义一个具有泛型参数(在使用时被特化)的类型
public class GenericTypeBase<E> {

    // 直接使用泛型参数类型来定义属性
    private E value;
    private List<E> list = new ArrayList<>();

    // TODO: 泛型方法的定义: 泛型参数<T>必须写在返回类型之前
    // - 使用泛型参数类型作为参数类型
    // - 使用泛型参数类型作为返回值类型
    private static <K, V> boolean testCompare(K value, Pair<K, V> p1, Pair<K, V> p2) {
        return value != null && p1.getKey().equals(p2.getKey());
    }

    // TODO. 泛型方法的重载: 泛型类型的返回类型不属于方法签名，造成重载问题
    <T> List<T> executeSqlQuery(String query, Class<T> returnType) {
        // 泛型约束: 无法直接通过泛型判断instance类型
        if (returnType.isAssignableFrom(String.class)) {
            System.out.println("check: return type is string");
        }
        return new ArrayList<>();
    }

    <T> List<T> executeSqlQueryPlus(String query, Class<T> returnType) {
        System.out.println("query2");
        return null;
    }
}

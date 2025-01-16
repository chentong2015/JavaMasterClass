package JavaGenericType;

// TODO. 具有泛型参数的类型在被实例化时，泛型参数需要被特化
public class GenericTypeInstance<T> {

    // Box<T>特化泛型类型时不能使用Primitive Types, 不支持类型擦除后插入强制类型转换

    // public static <T> ObjectListComparator<T> create() {
    //        return new ObjectListComparator<>();
    //    }

    // // 泛型构造器：在创建类型对象时(调用构造器), 会根据实际的参数进行类型推断
    //    // GenericTypesBasic<Integer> myObject = new GenericTypesBasic<>("");
    //    // E类型推断成Integer, T类型推断成String
    //    <T> GenericTypeBase(T t) {
    //    }
}

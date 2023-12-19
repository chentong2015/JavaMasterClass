package JavaGenericType;

import JavaGenericType.model.Box;

// TODO: Java泛型的实现方式 "类型擦除式泛型 Type Erasure Generics"
// 在字节码层面的擦除，元数据metadata中还保留泛型信息，通过反射可以取回参数化类型
// 1. Java语言泛型只在程序源码中存在，在编译后的字节码中泛型都会被替换成原来的裸类型，并在相应的地方插入强制类型转换
// 2. 类型擦除可确保不会为参数化类型创建新的类，因此泛型不会产生运行时开销
// 3. 在保证类型安全的同时，生成bridge methods以便在泛型中保留多态
public class TypeErasure<E> {

    // TODO: Row Type原始类型(裸类型), 某类型所有泛型化实例的共同父类型
    // Box<T>特化泛型类型时不能使用Primitive Types, 不支持类型擦除后插入强制类型转换
    private static void testRawType() {
        Box<String> stringBox = new Box<>();
        // stringBox.set(String str); 调用特化后的类型方法

        // Box is the raw type of the generic type Box<T>
        // 1. 这里Box原始类型看做是类型参数为Object的类型，可将特化后的类型赋值给原始类型，反之不行
        // 2. 原始类型不能调用需要泛型参数的方法
        Box rawBox = stringBox;
        // rawBox.set(Object obj);
    }

    // TODO. 在重载方法中使用泛型, 注意类型擦除(both methods have same erasure)
    // 1. 以下重载方法的参数经过类型擦除成同一个Row Type: List，造成方法的重载失败(返回类型不参与重载的选择)
    // 2. 但是由于返回类型不同，前端编译后在Class文件格式中却可以共存，但是编译仍然会报错(必须JDK6编译)
    // 3. Signature存储一个方法在字节码层面的特征签名
    // private String testOverloading(List<String> list) {
    //     System.out.println("List<String>");
    //     return "";
    // }
    //
    // private int testOverloading(List<Integer> list) {
    //     System.out.println("List<Integer>");
    //     return 0;
    // }
}

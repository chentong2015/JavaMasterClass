package JavaGenericType.TypeErasures;

import java.util.List;

// TODO: Raw Type原始类型(裸类型), 某类型所有泛型化实例的共同父类型
public class TypeErasure {

    // TODO: Type Erasure类型擦除: Java泛型的实现方式
    // 在字节码层面的擦除，元数据metadata中还保留泛型信息，通过反射可以取回参数化类型
    // 1. Java语言泛型只在程序源码中存在，在编译后的字节码中泛型都会被替换成Raw Type(原始类型)
    //    并在相应的地方插入强制类型转换
    // 2. 类型擦除可确保不会为参数化类型创建新的类，因此泛型不会产生运行时开销
    // 3. 在保证类型安全的同时，生成bridge methods以便在泛型中保留多态

    // TODO. Type Erasure类型擦除对方法重载的影响
    // 1. 以下重载方法的参数经过类型擦除成相同Row Type: List，造成重载失败(返回类型不参与重载的选择)
    // 2. 但是由于返回类型不同，前端编译后在Class文件格式中却可以共存，但是编译仍然会报错(必须JDK6编译)
    // 3. Signature存储一个方法在字节码层面的特征签名
    private String testOverloading(List<String> list) {
        System.out.println("List<String>");
        return "";
    }

    private int testOverloadingPlus(List<Integer> list) {
        System.out.println("List<Integer>");
        return 0;
    }
}

package JavaBasicOOP.BaseInterface.base;

import java.util.List;

public interface ISaveable extends ITelephone {

    // 1. 接口中不能使用静态语句块 static { }
    // 编译器会为接口生成"<clinit>()"方法, 完成变量初始化的赋值
    // 执行接口的<clinit>()方法时，不需要先执行父接口的<clinit>()方法

    // 2. 使用泛型接口, 最大限度的解耦, 增加可扩展性
    List<String> write();

    void read(List<String> savedValues);

    // TODO. Java 8中添加了缺省方法，允许给现有的接口添加方法而不对它的实现类型造成影响
    //   并不能确保缺省方法在之前存在的实现中能够运行，并且实现的类型并不知道新的默认方法
    // 3. 使用default关键字声明一个方法, 提供方法的默认实现
    //    被implements的时候，不用再重写   > Java 8
    default void testDefaultMethod() {
        System.out.println("Default method");
    }

    // 4. 使用static声明一个方法, 提供方法的默认实现
    //    被implements的时候，不用再重写   > Java 9
    static void testStaticMethod() {
        System.out.println("Static method");
    }
}
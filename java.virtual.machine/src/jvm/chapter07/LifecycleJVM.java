package jvm.chapter07;

/**
 * JVM的生命周期: Java程序执行的7个阶段
 * 1. Java Virtual Machine Startup
 * 2. Loading of Classes and Interfaces
 * 3. Linking of Classes and Interfaces
 * 4. Initialization of Classes and Interfaces
 * 5. Creation of New Class Instances
 * 6. Finalization of Class Instances
 * 7. Unloading of Classes and Interfaces
 * 8. Program Exit, Invoke exit()/halt() of Runtime
 */
// <<Java Virtual Machine虚拟机定义文档>>
// 1. JVM Startup:
//    通过使用bootstrap class loader & user-defined class loader来创建初始化的类或者接口
//    JVM会link链接在上面的类或者接口上
// 2. Loading: 通过Class Loader类来加载，找到指定的二进制表示形式的文件
//    Bootstrap class Loader 有JVM提供
//    User-defined class loaders 用于创建源自用户定义的源的类 invoke loadClass(N) on loader
// 3. Linking
//    Verify: 确定load的是格式完整且正确 structurally correct, proper symbol table
//    Prepare: allocation分配静态存储空间以及内部使用的任何数据结构 create static fields, and init fields with default values
//    Resolution (optional): 检查符号引用是否正确 (by loading other classes and interface)
// 4. Initialization
//    先会从superclass开始初始化 Explicit initializers for static fields
//    执行变量的初始化以及调用静态的初始化器
// Invoke .main
public class LifecycleJVM {

    // TODO: 类型加载的生命周期
    // 1. Loading
    //    通过特殊的名称找到2进制表示形式的class或者interface (.class file format)，获取定义此类的二进制字节流

    // 2. Linking
    //    将上面找到的2进制形式"组合"到Java虚拟机的运行时状态(run-time state)以便可以执行
    //    2.1 Verification 验证符合规范，对JVM无害: 文件格式，元数据，字节码，符合验证
    //    2.2 Preparation
    //    2.3 Resolution
    private static int value = 123; // value在准备阶段的值为0, 把123赋值给value的动作要等到"类的初始化阶段"才会被执行

    // 3. Initialization
    //    TODO: 只有6种情况会立即执行"类的初始化"
    //    执行静态初始化器，初始化静态字段/接口中常量constants

    // 4. Using

    // 5. Unloading

    // TODO: ==============> 从一个类型被创建，到被回收，JVM内存中经历怎样的过程和变化 ??
}

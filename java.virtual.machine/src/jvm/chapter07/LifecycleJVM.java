package jvm.chapter07;

/**
 * JVM的生命周期: Java程序执行的7个阶段
 * 1. Java Virtual Machine Startup        从程序的入口开始启动 invoke the method
 * 2. Loading of Classes and Interfaces   通过特殊的名称找到2进制表示形式的class或者interface (.class file format)
 * 3. Linking of Classes and Interfaces   将上面找到的2进制形式"组合"到Java虚拟机的运行时状态(run-time state)以便可以执行
 * 4. Init of Classes and Interfaces      执行静态初始化器，初始化静态字段 / 接口中常量constants
 * 5. Creation of New Class Instances     创建实例化对象
 * 6. Finalization of Class Instances     结束实例对象
 * 7. Unloading of Classes and Interfaces 卸载class或者interface
 * 8. Program Exit                        程序退出 invoke exit()/halt() of Runtime, invoke exit() of System
 */
public class LifecycleJVM {

    // 1. JVM Startup:
    // -> 通过使用bootstrap class loader & user-defined class loader来创建初始化的类或者接口
    // -> JVM会link链接在上面的类或者接口上

    // 2. Creation and Loading: 通过Class Loader类来加载，找到指定的二进制表示形式的文件
    // -> Bootstrap class Loader 有JVM提供
    // -> User-defined class loaders 用于创建源自用户定义的源的类 invoke loadClass(N) on loader

    // 3. Linking
    // -> Verify: 确定load的是格式完整且正确 structurally correct, proper symbol table
    // -> Prepare: allocation分配静态存储空间以及内部使用的任何数据结构 create static fields, and init fields with default values
    // -> Resolution (optional): 检查符号引用是否正确 (by loading other classes and interface)

    // 4. Initialization
    // -> 先会从superclass开始初始化 Explicit initializers for static fields
    // -> 执行变量的初始化以及调用静态的初始化器

    // Invoke .main

    // Finalization 该Object中声明的方法已经废弃掉
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

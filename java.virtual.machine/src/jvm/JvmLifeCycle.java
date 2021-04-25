package jvm;

/**
 * Execution 程序是如何执行的? JVM的生命周期
 * 1. Java Virtual Machine Startup        从程序的入口开始启动
 * 2. Loading of Classes and Interfaces   通过特殊的名称找到2进制形式的class或者interface
 * 3. Linking of Classes and Interfaces   将上面找到的2进制形式组合到Java虚拟机的运行时状态(run-time state)以便可以执行
 * 4. Initialization of Classes and Interfaces 执行静态初始化器，初始化静态字段 / 或者初始化接口中的常量constants
 * 5. Creation of New Class Instances     创建实例化对象
 * 6. Finalization of Class Instances     结束实例对象
 * 7. Unloading of Classes and Interfaces 卸载class或者interface
 * 8. Program Exit                        程序退出
 */
public class JvmLifeCycle {

    // Finalization 该Object中声明的方法已经废弃掉
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

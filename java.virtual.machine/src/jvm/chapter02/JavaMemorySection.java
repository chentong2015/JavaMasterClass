package jvm.chapter02;

public class JavaMemorySection {

    //      运行时的数据区域
    // 方法区     虚拟机栈     本地方法栈
    // 堆            程序计数器
    // 执行引擎   本地库接口   本地方法库


    // 1. 方法区: 线程共享的内存区域，存储已被虚拟机加载的类型信息，常量...
    //        包含运行时常量池;

    // 2. 虚拟机栈:  线程私有内存，线程的内存模型，创建栈帧(Stack Frame)
    //          数据类型在局部变量表中的存储空间以局部变量槽(Slot)来表示
    //          线程请求的栈深度过大，超过虚拟机允许的深度，则出现StackOverflow

    // 3. 本地方法栈: 使用本地(Native)方法服务

    // 4. Java堆: 内存中最大的一块，所有线程共享的一块区域，在虚拟机启动时创建
    //         几乎所有的对象实例都存储在这个内存中
    //         垃圾回收器管理的内存区域

    // 5. 程序计数器: 线程私有的内存，存储当前线程所执行的字节码的行号指示器，可用于线程切换后恢复
    //           无OutOfMemoryError
}

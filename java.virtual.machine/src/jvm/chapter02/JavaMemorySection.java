package jvm.chapter02;

public class JavaMemorySection {

    // TODO: OS单个进程内存分配限制 = 最大堆容量 + 最大方法区容量 + 栈(虚拟机栈, 本地方法栈)
    //      运行时的数据区域
    // 方法区     虚拟机栈     本地方法栈
    // 堆            程序计数器
    // 执行引擎   本地库接口   本地方法库

    // 1. 方法区:    线程共享的内存区域，存储已被虚拟机加载的类型信息，常量...
    //              包含运行时常量池;
    // 2. 虚拟机栈:   线程私有内存，线程的内存模型，创建栈帧(Stack Frame)
    //              数据类型在局部变量表中的存储空间以局部变量槽(Slot)来表示
    //              线程请求的栈深度过大，超过虚拟机允许的深度，则出现StackOverflow
    // 3. 本地方法栈: 使用本地(Native)方法服务
    // 4. Java堆:   内存中最大的一块，所有线程共享的一块区域，在虚拟机启动时创建
    //              几乎所有的对象实例都存储在这个内存中
    //              垃圾回收器管理的内存区域
    // 5. 程序计数器: 线程私有的内存，存储当前线程所执行的字节码的行号指示器，可用于线程切换后恢复


    // HotSpot虚拟机对象
    // 1. 对象的创建：类加载/为新对象分配内存
    // 2. 对象的内存布局
    //    对象头：对象自身运行时数据 + 类型指针(类型元数据的指针)
    //    实例数据:对象存储的有效信息
    //    对齐填充:确保任何对象的大小都是8字节的整数倍
    // 3. 对象的访问：使用句柄间接访问 + 指针直接访问(reference直接存储对象地址)

    // OutOfMemoryError异常: Java heap space
    // 确定程序的那个区域造成的内存溢出 ? 如何避免 ?
    // 使用内存映像分析工具(Eclipse Memory Analyzer): 内存泄漏(GC Root引用链)? 内存溢出(JVM设置堆参数上限)?
}

package jvm_basics.chapter03_Garbage_Collection;

// GC: 字节码执行引擎在后台开启的一个线程，用来做垃圾对象回收的
//     垃圾回收器通常是作为一个单独的低优先级的线程运行，清除和回收内存堆中已经死亡的或者长时间没有使用的对象进行
public class BaseGarbageCollection {

    // 堆:                |<- Survivor区 ->|             ----> Survivor区S0和S1分别用来做备份，存放"非垃圾"，其余Eden+S0直接清空
    //        Eden  -->     S0   -->  S1   -->  老年代    ----> 一般分代年龄超过15则视为老年代(不同的垃圾收集器数值可能不同)
    //        8/10         1/10     1/10        2/3
    //        |<--------- 年轻代 -------->|               ----> Minor GC回收的是整个年轻代

    // GC过程: 当Eden内存区占满之后所触发的垃圾回收机制
    // 0. 为什么年轻代的比例是8:1:1 ?
    //    对象首先放到Eden区中，大部分对象都是创建都会销毁，真正移动到Survivor区的对象很少，因此比例不高 !!
    // 1. 对象的分代年龄
    //    一个对象经历过一次GC之后，则分代年龄会加1，该信息存储在堆存储空间中对象的附加信息中，占4bits
    // 2. 年轻代的算法：复制算法
    // 2. 老年代GC算法: 大多是标记整理，CGS使用标记清除算法

    // 3. 一般什么样的对象会被放入老年代 ??
    //    3.1 大对象直接进入老年代   : 在Survivor区不容易存放
    //    3.2 长期存活的对象        : 单例，静态变量引用的对象，数据连接池，对象缓存池，Spring Bean
    //    3.3 对象动态年龄判断      : 对象的大小 > Survivor区的50%
    //    3.4 老年代空间分配担保机制 : 执行minor gc之前会判断老年代的剩余可用空间，如果可用空间大于所有对象之和...
    // 4. 如果老年代放满了，则会触发"Full GC"回收整个堆的内存空间，
    //    当"Full GC"回收不了，剩下的都是非垃圾对象，则产出异常: OutOfMemoryError, java heap space
    // 5. 客户端调用: System.gc()或Runtime.getRuntime().gc(), 但JVM可以屏蔽掉显式垃圾回收调用

    // TODO: 判断垃圾对象的算法, 如何找到 ?
    // 1. 引用计数算法：统计一个对象被引用的数目
    //    给对象添加一个引用计数器，任何时候当引用计数器为0的时候表示该对象可以被回收
    //    <该引用计数算法没有办法解决"循环引用"的问题，因此主流的虚拟机并没有使用该算法>
    // 2. 可达性分析算法
    //    将"GC Roots"作为起点：线程栈的本地变量/局部变量，本地方法栈的变量，静态变量
    //    从根结点找引用的变量，如果对象被引用，则标记为"非垃圾"，将对象从堆的Eden内存空间转移到Survivor区，反之则为"垃圾"对象
    // 3. 三色标记算法
    //    在并发标记的过程中，由于标记期间应用程序还在继续跑，对象间的引用可能发生变化，造成多标或者漏标
    //    三色标记算法会把GC Roots可达性分析遍历过程中遇到的对象，按照"是否访问过"来标记3中颜色
    //    > 黑色: 表示对象已经被GC收集器访问过，且这个对象的所有引用也被访问过，黑色标记的对象表示安全存活的，如果有其他对象指向，则无需再遍历
    //    > 灰色: 表示对象已经被GC收集器访问过，但这个对象至少有一个引用没被访问过
    //    > 白色: 表示对象没有被GC收集器访问过

    // GC日志详细分析工具
    // 1. GCEasy日志分析工具
    // 2. GCViewer日志分析工具
}

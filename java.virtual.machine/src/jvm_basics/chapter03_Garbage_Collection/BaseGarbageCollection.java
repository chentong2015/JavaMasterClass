package jvm_basics.chapter03_Garbage_Collection;

public class BaseGarbageCollection {

    // GC: 字节码执行引擎在后台开启的一个线程，用来做垃圾对象回收的
    //     垃圾回收器通常是作为一个单独的低优先级的线程运行，清除和回收内存堆中已经死亡的或者长时间没有使用的对象进行

    // TODO: GC过程: 当Eden内存区占满之后所触发的垃圾回收机制
    // 1. GC Roots根结点: 线程栈的本地变量/局部变量，本地方法栈的变量，静态变量
    // 2. 可达性分析算法  ：从根结点找引用的变量，如果对象被引用，则标记为"非垃圾"，将对象从堆的Eden内存空间转移到Survivor区，反之则为"垃圾"对象
    // 3. 对象的分代年龄  : 一个对象经历过一次GC之后，则分代年龄会加1m，该信息存储在堆存储空间中对象的附加信息中，占4bits
    // 4. 客户端调用     : System.gc()或Runtime.getRuntime().gc(), 但JVM可以屏蔽掉显示的垃圾回收调用

    // 堆:                |<- Survivor区 ->|             ----> Survivor区S0和S1分别用来做备份，存放"非垃圾"，其余Eden+S0直接清空
    //        Eden  -->     S0   -->  S1   -->  老年代    ----> 一般分代年龄超过15则视为老年代(不同的垃圾收集器数值可能不同)
    //        8/10         1/10     1/10        2/3
    //        |<--------- 年轻代 -------->|               ----> Minor GC回收的是整个年轻代
    // 5. TODO: 一般什么样的对象会被放入老年代:
    //    5.1 大对象直接进入老年代   : 在Survivor区不容易存放
    //    5.2 长期存活的对象        : 单例，静态变量引用的对象，数据连接池，对象缓存池，Spring Bean
    //    5.3 对象动态年龄判断      : 对象的大小 > Survivor区的50%
    //    5.4 老年代空间分配担保机制 : 执行minor gc之前会判断老年代的剩余可用空间，如果可用空间大于所有对象之和...
    // 6. 如果老年代放满了，则会触发"Full GC"回收整个堆的内存空间
    // 7. 当"Full GC"回收不了，则OutOfMemory内存溢出异常


    // TODO: -XX:NexRatio=4是什么意思 ?
    //       堆为什么要分新生代和老年代? 他们比例为什么是1: 2?
}

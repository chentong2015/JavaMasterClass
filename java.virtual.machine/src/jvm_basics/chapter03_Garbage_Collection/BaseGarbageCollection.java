package jvm_basics.chapter03_Garbage_Collection;

// Java提供的GC功能可以自动监测对象是否超过作用域从而达到自动回收内存的目的
// 垃圾收集器会自动进行管理
// System.gc() 或Runtime.getRuntime().gc() ，但JVM可以屏蔽掉显示的垃圾回收调用
// 垃圾回收器通常是作为一个单独的低优先级的线程运行，不可预知的情况下对内存堆中已经死亡的或者长时间没有使用的对象进行清除和回收
public class BaseGarbageCollection {

    // TODO: 垃圾回收机制
    // 分代复制垃圾回收
    // 标记垃圾回收
    // 增量垃圾回收
}

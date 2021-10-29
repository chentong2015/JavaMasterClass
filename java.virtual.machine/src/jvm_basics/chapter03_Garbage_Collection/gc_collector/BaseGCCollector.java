package jvm_basics.chapter03_Garbage_Collection.gc_collector;

// TODO: 为什么要设计不同的垃圾收集器(C++底层代码的实现程序) ?
// 不同的垃圾收集器适用于不同的场景，设计的目标不同，但是都会有STW

// Java为什么要设计STW机制 ?
// 在GC的过程中，会遍历堆中所有对象，判断是否是"垃圾对象"，这个过程会STW，完了之后立马再恢复线程的执行
// 如果在这个过程中，允许线程运行，那么这个对象的状态会不停的变化，比如线程结束的时候会从非垃圾变成垃圾
// 这时不可能再回头循环判断一遍，导致GC垃圾收集失效，甚至无法结束
public class BaseGCCollector {

    // TODO: 1. 对整个Eden堆内存区的整个回收 ===> 基于分代模型(年轻代，老年代)
    // Serial / Serial Old     : 早期垃圾收集器，单线程GC
    // 复制算法 / 标记整理算法

    // Parallel / Parallel Old : Java 8的默认垃圾收集器，并行线程GC
    // 复制算法 / 标记整理算法

    // ParNew / CMS            : 并发GC回收的进阶，解决STW时间过长，约100ms
    // 复制算法 / 标记清除算法
    // ConcurrentMarkSweep(初始标记 > 并发标记 > 重新标记(解决错标) > 并发清理)，造成漏标，浮动垃圾等问题 !!
    // CMS可以使用Serial Old来清理，终止用户线程，标记整理

    // TODO: 2. 实现对堆内存的部分回收      ===> 分区模型(Java 8版本之后)
    // G1                      : Java 9默认的垃圾收集器，STW时间约10ms
    // 标记整理算法
    // -XX:+UseG1GC: 使用G1垃圾收集器
    // -XX:MaxGCPauseMillis: 目标的暂停时间(默认200毫秒) ==> 通过设置基于的停顿时间，只回收指定时间长短，实现部分回收

    // ZGC (Zero GC)           : Java 11默认的垃圾收集器，STW时间约1ms
    // 颜色指针 / 读屏障 / 多重映射

    // G的增强性                 ：redhat开发
    // Epsilon                 : 做调试使用，不做部署
}
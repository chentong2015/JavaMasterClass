package jvm_basics.chapter03_Garbage_Collection;

// 垃圾收集器: C++底层代码的实现程序，通过设置不同参数来调优
// 为什么要设计不同的垃圾收集器 ?
// 1. 不同的垃圾收集器适用于不同的场景，设计的目标不同，但是都会有STW
public class BaseGCCollector {

    // TODO: 对整个Eden堆内存区的整个回收
    // CMS:
    // 标记清除算法


    // TODO: 实现对堆内存的部分回收
    // G1 :
    //     -XX:+UseG1GC: 使用G1垃圾收集器
    //     -XX:MaxGCPauseMillis: 目标的暂停时间(默认200毫秒) ==> 通过设置基于的停顿时间，只回收指定时间长短，实现部分回收
    // 三色标记算法
    // 标记整理算法

    // ZGC:
    // 颜色指针
    // 读屏障
    // 多重映射
}

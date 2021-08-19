package jvm_basics.chapter03_Garbage_Collection;

// 垃圾回收算法
// 1. 分代复制垃圾回收
// 2. 标记垃圾回收
// 3. 增量垃圾回收
public class BaseGCAlgo {

    // 1. 三色标记算法
    //   在并发标记的过程中，由于标记期间应用程序还在继续跑，对象间的引用可能发生变化，造成多标或者漏标
    //   三色标记算法会把GC Roots可达性分析遍历过程中遇到的对象，按照"是否访问过"来标记3中颜色
    //   1.1 黑色: 表示对象已经被GC收集器访问过，且这个对象的所有引用也被访问过，黑色标记的对象表示安全存活的，如果有其他对象指向，则无需再遍历
    //   1.2 灰色: 表示对象已经被GC收集器访问过，但这个对象至少有一个引用没被访问过
    //   1.3 白色: 表示对象没有被GC收集器访问过

}

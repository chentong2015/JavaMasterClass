package jvm_basics.chapter03_Garbage_Collection.aglo;

// TODO: 三种垃圾回收算法: 如何高效的收集垃圾 ?
// 1. Mark-Sweep 标记清除算法
//    位置不联系，容易在内存空间产生内存碎片 ==> 无需线程同步
//    > 黑色：可被回收  ---> 回收后会变成绿色
//    > 灰色：存活对象
//    > 绿色：未被使用的空间
//
// 2. Mark-Copying 标记复制算法 > 分代收集理论(<jdk1.8)
//    没有碎片，但是浪费空间(会将内存空间一分为二，用来复制存活的对象)
//
// 3. Mark-Compact 标记整理算法
//    没有碎片，效率偏低(会将不连续的内存空间进行整理) ==> 这个必须做线程同步
//    3.1 标记
//    3.2 清理
//    3.3 内存整理
//    3.4 数据整理
//
// 4. 基于Region的GC算 G1版本

// 垃圾回收算法底层实现: https://www.bilibili.com/video/BV1Rf4y1y7xE?p=10
public class BaseGCAlgo {

}

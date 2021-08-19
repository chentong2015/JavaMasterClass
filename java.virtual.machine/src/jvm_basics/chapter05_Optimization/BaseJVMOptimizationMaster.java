package jvm_basics.chapter05_Optimization;

// 单机几十万并发的系统JVM如何优化 ?
// 背景分析：
// Rocket MQ, Kafka 中间件系统, 单机最大可处理几十万的消息
// 1. 一秒钟几百兆的对象被放入Eden，短时间触Minor GC，同时Survivor区可能放不下，来不及GC直接被放到老年代中
//    老年代的空间被迅速的占满，然后触发Full GC，导致卡顿，客户端发送的请求超时，无法处理 !!
// 2. 在这个期间，可能还有对象没来得及放到磁盘，还在内存中 !!
//
// 调优方案：
// 1. 使用大内存(>64G)，但minor gc仍然需要优化: Eden(30G)内存太大，做GC依然需要化时间，同时还会STW
// 2. 选择合适的垃圾收集器，设置合适的参数
//    一次只回收Eden区的一部分(2G-3G)，收回出来然后立即使用(使用和回收交替)，中间停顿时间短
//    单次的Minor GC是减少的，避免客户端的超时重发，但总的回收一遍之后的时间是增加的
public class BaseJVMOptimizationMaster {

}

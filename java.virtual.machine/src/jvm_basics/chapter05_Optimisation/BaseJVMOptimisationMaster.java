package jvm_basics.chapter05_Optimisation;

// 单机几十万并发的系统JVM如何优化 ?
// 背景分析：Rocket MQ, Kafka 中间件系统, 单机最大可处理几十万的消息
//         一秒钟几百兆的对象被放入Eden，短时间触Minor GC，还有Survivor区直接放不下，来不及GC直接被放到老年代中
//         老年代的空间被迅速的占满，然后触发Full GC，导致卡顿，客户端发送的请求超时，无法处理 !!
// 调优方案：
//        1. 使用大内存(几十个G)，Eden(30G)内存太大，做GC依然需要化时间，同时还会STW
//        2. 选择合适的垃圾收集器，设置合适的参数
public class BaseJVMOptimisationMaster {

}

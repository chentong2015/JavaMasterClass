package jvm_basics.chapter05_Optimization;

// TODO: JVM虚拟机调优是为了减少GC, 特别是Full GC
//       避免STW(Stop The World), 会停掉用户的发起的线程会暂停, 程序的卡顿, 影响性能 !!
// 1. Java为什么要设计STW机制 ?
//    在GC的过程中，会遍历堆中所有对象，判断是否是"垃圾对象"，这个过程会STW，完了之后立马再恢复线程的执行
//    如果在这个过程中，允许线程运行，那么这个对象的状态会不停的变化，比如线程结束的时候会从非垃圾变成垃圾
//    这时不可能再回头循环判断一遍，导致GC垃圾收集失效，甚至无法结束 !!
// 2. 基本实战调优: 合理设置JVM参数的配置，同时进行压力测试，逐步的优化
public class BaseJVMOptimization {

    // 背景分析：                       亿级流量，每天用户点击上亿次的网站
    //                                日活用户500万，平均用户点击20，30次
    //                                日均50万订单，几个高峰期占80%
    //                    每秒订单1000数                              每秒订单几十单
    //         订单系统(4核8G) / 订单系统(4核8G) / 订单系统(4核8G)
    //              每个订单对象假定1KB(算字段的总和)
    //             每秒300KB订单对象的生成，分配到堆中
    //            下单还设计其他的对象，库存，优惠，积分...*20倍
    //                10倍放大的其他操作

    // 调优方案：
    // 1. JVM参数的设置，根据机器的内存空间大小来设置JVM中数据区的大小 ??
    //    设置堆参数-Xmx(堆的最大值) -Xms(堆的最小值)
    //    > java -Xms3G -Xmx3G -Xss1M -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=512M -jar microservice-server.jar
    //                                                       堆(3G)         方法区/元空间(512M)
    //                                                       Eden(800M)
    //              每秒产生60MB的对象，存放到堆空间中   ----->    S0(100M)       线程栈1 -Xss 1M
    //                                                       S1(100M)       线程栈2 -Xss 1M
    //                                                       Old(2G)
    //    大约14秒左右Eden被占满，然后触发Minor GC，60M的对象大约1秒后都变成垃圾对象
    //    在最后两秒的对象由于方法还没有执行结束，则不会被回收，会被移动到Survivor区，"或者直接移动到老年代" !!
    //    老年代Old(2G)大约会在5，6分钟被占满，之后触发Full GC
    //
    // 2. 调优堆空间的内存分布 !!
    //    > java -Xms3G -Xmx3G -Xmn2G -Xss1M -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=512M -jar microservice-server.jar
    //                                                       堆(3G)         方法区/元空间(512M)
    //                                                       Eden(1.6G)
    //              每秒产生60MB的对象，存放到堆空间中   ----->    S0(200M)       线程栈1 -Xss 1M
    //                                                       S1(200M)       线程栈2 -Xss 1M
    //                                                       Old(1G)
    //      增加年轻代的内存空间，减少老年代所占的大小，使得对象在回收前不被送入Old区
    //      通过对业务的预估，直接在"年轻代"就将对象给干掉 !!
}
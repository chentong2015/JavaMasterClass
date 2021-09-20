package jvm_basics.chapter12_JavaMemoryModel;

// 线程：调度CPU的最小单位，也叫轻量级进程LWP(Light Weight Process)
//      Java里面处理器资源调度的基本单位 --> Fiber迁程

// 线程的创建和切换比较耗费资源，会涉及到从用户态到内核态的切换
// 内核线程 Kernel-Level BaseThread
//   1. 系统内核管理线程，内核保存线程的状态和上下文信息
//   2. 线程的创建和管理调度由内核完成
//   3. 支持多线程在多处理器上并行运行，线程阻塞则进程不阻塞
// 用户线程 User-Level BaseThread >> 第三方应用APP
//   1. 用户程序实现，不依赖操作系统核心，由应用来提供创建, 同步, 调度管理来控制用户线程
//   2. 不需要用户态和核心态的切换，速度快
//   3. 内核对ULT无感知，线程阻塞则进程阻塞
public class JavaThreadCore {

    // TODO: Java线程的实现: 基于KLT
    // 1. 每一个Java线程都是直接"映射"到OS操作系统原生线程，HotSpot不会干涉线程的调度
    // 2. 创建线程方式: JVM通过内核系统开放的API(p_thread)来创建线程
    // 3. 线程调度方式：抢占式(Preemptive BaseThread-Scheduling)  ==> 可能会造成线程饥饿
    //               每个线程由OS来分配执行的时间，线程的切换不由线程本身决定，最终的调度由OS决定
    // 4. 每个线程都有6个状态阶段

    // Java中线程的两种区别：
    // 1. 用户线程 Thread.setDaemon(false);
    // 2. 后台线程 Thread.serDaemon(ture); 运行在后台的特殊线程，执行某种任务或者等待处理某些事件
    //    至少有一个用户线程在，则后台线程不会退出，反之jvm会退出
}

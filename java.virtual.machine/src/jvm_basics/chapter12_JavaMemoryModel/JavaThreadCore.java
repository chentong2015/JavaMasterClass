package jvm_basics.chapter12_JavaMemoryModel;

// 线程：调度CPU的最小单位，也叫轻量级进程LWP(Light Weight Process)
//      Java里面处理器资源调度的基本单位 --> Fiber迁程

// 线程的创建和切换比较耗费资源，会涉及到从用户态到内核态的切换
// 内核线程 Kernel-Level BaseThread
// 1. 系统内核管理线程，内核保存线程的状态和上下文信息
// 2. 线程的创建和管理调度由内核完成
// 3. 支持多线程在多处理器上并行运行，线程阻塞则进程不阻塞

// 用户线程 User-Level BaseThread >> 第三方应用APP
// 1. 用户程序实现，不依赖操作系统核心，由应用来提供创建, 同步, 调度管理来控制用户线程
// 2. 不需要用户态和核心态的切换，速度快
// 3. 内核对ULT无感知，线程阻塞则进程阻塞
public class JavaThreadCore {

    // TODO: Java线程的实现: 基于KLT
    // 1. Java线程都是直接"映射"到OS操作系统原生线程，HotSpot不会干涉线程的调度
    // 2. 创建线程方式: JVM通过内核系统开放的API(p_thread)来创建线程
    // 3. 线程调度方式：抢占式(Preemptive BaseThread-Scheduling)  ==> 可能会造成线程饥饿
    // 每个线程由OS来分配执行的时间，线程的切换不由线程本身决定，最终的调度由OS决定
    private void testJavaThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test thread");
            }
        }
        ).start();
        // .start() 线程启动方法会调用到本地方法
        // private native void start0();
        // openjdk  调用到c语言实现的方法
        // os       调用到os中的线程的启动
    }

    // TODO: 线程是比较"昂贵"的资源
    // 1. 线程的创建和销毁成本很高，在Linux这样的操作系统中，创建和销毁都是重量级的系统函数
    // 2. 线程本身占用较大内存，像Java的线程栈，一般至少分配512K～1M的空间，系统中线程数过多将耗尽JVM内存
    // 3. 线程的切换成本是很高的
    //    操作系统发生线程切换的时候，需要保留线程的上下文，然后执行系统调用
    //    如果线程数过高，可能执行线程切换的时间甚至会大于线程执行的时间，导致系统不可用
    // 4. 容易造成锯齿状的系统负载
    //    系统负载是用活动线程数或CPU核心数，一旦线程数量高但外部网络环境不是很稳定
    //    就很容易造成大量请求的结果同时返回，激活大量阻塞线程从而使系统负载压力过大

    // Java两种线程区别：
    // 1. 用户线程 Thread.setDaemon(false);
    // 2. 后台线程 Thread.serDaemon(ture); 运行在后台的特殊线程，执行某种任务或者等待处理某些事件
    //    至少有一个用户线程在，则后台线程不会退出，反之jvm会退出
}

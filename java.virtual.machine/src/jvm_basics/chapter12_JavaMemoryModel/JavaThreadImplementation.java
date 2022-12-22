package jvm_basics.chapter12_JavaMemoryModel;

// Java两种线程区别：
// 1. User Thread - Thread.setDaemon(false); 普通的用户线程
//    只要有一个用户线程在，JVM就不会退出
// 2. Daemon Thread Thread.serDaemon(ture); 守护线程(后台线程)
//    运行在后台的特殊线程，执行某种任务或者等待处理某些事件
//    只要有一个用户线程在，后台线程就不会退出
//    守护线程不会阻止JVM的推出(not prevent the JVM from exiting when the program finishes)
public class JavaThreadImplementation {

    // TODO: Java线程的实现: 基于KLT(Kernel Level Thread)
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
}

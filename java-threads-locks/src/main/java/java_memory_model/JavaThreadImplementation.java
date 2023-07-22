package java_memory_model;


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

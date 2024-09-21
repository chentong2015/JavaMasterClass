package java_threads.thread;

// TODO. Java线程的启动和调度(基于JMM模型)
//  具体线程的调度完全交给JVM和OS来决定，输出的顺序不可保证
// new Thread().start();
//   .start() 线程启动方法会调用到本地方法
//   private native void start0();
//     openjdk 调用c语言实现的方法
//        os   调用os中的线程的启动
public class JavaThreadStarting {

    public static void main(String[] args) {
        Thread runThread = new Thread(new JavaThreadCreation.DemoRunnable());
        runThread.setPriority(10); // 线程的优先级只是给OS参考，并非确定的执行顺序
        runThread.start();

        JavaThreadCreation.DemoThread demoThread = new JavaThreadCreation.DemoThread();
        demoThread.setName("Name");

        // .run() 方法级别的调用: 等效于调主线程的run()方法，始终只在一个线程
        demoThread.run();

        // .start() 线程级别的调用: 会创建新的线程，并自动调用线程的run()方法
        demoThread.start();

        // 同一个线程不能.start()启动多次，否则抛出IllegalThreadStateException
        // demoThread.start();
    }
}

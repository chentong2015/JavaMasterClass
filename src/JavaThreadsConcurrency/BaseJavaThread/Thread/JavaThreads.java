package JavaThreadsConcurrency.BaseJavaThread.Thread;

// 创建线程实例的几种方式: 本质只有一种
// 1. 使用"匿名类型"来创建新的线程
// 2. 通过继承Thread类型(类型本身也是实现了Runnable接口): 实现其中的方法来自定义创建线程
// 3. 通过实现Runnable接口：只需要实现一个方法 (推荐: 与更多的API交互)
// 4. 使用Executive Service线程池
public class JavaThreads {

    // 两种匿名类型的实现方式
    public void testAnonymousClassThread() {
        final Object shareObject = new Object();
        new Thread() {
            @Override
            public void run() {
                System.out.println("From anonymous class"); // 改变控制台输出的默认颜色
            }
        }.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable BaseThread");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    // 具体线程的调度完全交给JVM和OS来决定，输出的顺序不可保证 !!
    public void testDemoThread() {
        DemoThread demoThread = new DemoThread();
        demoThread.setName("Demo BaseThread Name"); // 通过新线程的名称来判断
        demoThread.run();   // 显式调用run()方法, 等效于调主线程的run()方法  ==> 始终只在一个线程：线程级别的调用
        demoThread.start(); // 创建新的线程，自动调用线程的run()方法         ==> 多线程：方法级别的调用
        // demoThread.start(); 根据线程的6种状态，同一个线程不能.start()启动多次，会抛出IllegalThreadStateException
    }

    // 创建Runnable的实例对象
    public void testRunnable() {
        Thread runThread = new Thread(new DemoRunnable());
        runThread.start();
    }

    // 使用Thread.join()确定等待一个线程执行彻底结束 ==> 可以在主线程调用，也可以在一个多线程中调用另外一个线程的.join()方法
    // 使用Thread.join(millis) 只在指定的时间内等待线程结束
    // 应用场景：控制线程的结束，约束执行的顺序，等待数据fetch之后，再执行相应的操作 !!
    private void testJoinThreads() throws InterruptedException {
        Thread threadA = new DemoThread();
        Thread threadB = new DemoThread();
        threadA.start();
        threadB.start();
        threadA.join();  // Waits for this thread to die
        threadA.join();
    }
}

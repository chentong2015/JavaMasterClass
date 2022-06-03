package JavaThreadsConcurrency.BaseJavaThread.Thread;

// TODO. Java中如何创建新的线程? (本质只有一种，实现Runnable接口)
// 实现和使用的形式有五种:
// 1. 自定义实现Runnable接口
// 2. 通过继承Thread类型(类型本身实现Runnable接口)
// 3. 使用"匿名类型"来创建新的线程
// 4. 使用ThreadPoolExecutor线程池来创建线程
// 5. 自定义实现ThreadFactory线程工厂
public class JavaThreads {

    // 两种匿名类型的实现方式
    public void testAnonymousClassThread() {
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

    // TODO: 具体线程的调度完全交给JVM和OS来决定，输出的顺序不可保证
    public void testDemoThread() {
        DemoThread demoThread = new DemoThread();
        // 通过新线程的名称来判断
        demoThread.setName("Demo BaseThread Name");

        // 1. 线程级别的调用: 始终只在一个线程
        // 显式调用run()方法, 等效于调主线程的run()方法
        demoThread.run();

        // 2. 方法级别的调用: 多线程
        // 创建新的线程，自动调用线程的run()方法
        demoThread.start();

        // 同一个线程不能.start()启动多次
        // 根据线程的6种状态，会抛出IllegalThreadStateException
        // demoThread.start();

        // Stop a thread 正确结束线程的方式，使用中断
        demoThread.interrupt();
    }

    // 创建Runnable的实例对象
    public void testRunnable() {
        Thread runThread = new Thread(new DemoRunnable());
        runThread.start();
    }

    // 1. 使用Thread.join()确定等待一个线程执行彻底结束
    //    可以在主线程调用，也可以在一个多线程中调用另外一个线程的.join()方法
    // 2. 使用Thread.join(millis) 只在指定的时间内等待线程结束
    // 应用场景：控制线程的结束，约束执行的顺序，等待数据fetch之后，再执行相应的操作
    private void testJoinThreads() throws InterruptedException {
        Thread threadA = new DemoThread();
        Thread threadB = new DemoThread();
        threadA.start();
        threadB.start();
        threadA.join();  // Waits for this thread to die
        threadA.join();
    }
}

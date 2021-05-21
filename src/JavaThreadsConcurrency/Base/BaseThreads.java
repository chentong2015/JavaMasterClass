package JavaThreadsConcurrency.Base;

import static JavaThreadsConcurrency.Base.model.ThreadColor.ANSI_GREEN;

/**
 * Thread: The only way to create a thread is to create an object of this class 创建线程类型的实例
 * 1. 使用"匿名类型"来创建新的线程
 * 2. 通过继承Thread, 实现其中的方法来自定义创建线程
 * 3. 通过实现Runnable接口来创建新的线程：只需要实现一个方法 (推荐: 与更多的API交互)
 * 4. 通过Executive Service(线程池)来实现
 */
public class BaseThreads {

    // 两种匿名类型的实现方式
    public void testAnonymousClassThread() {
        final Object shareObject = new Object();
        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "From anonymous class"); // 改变控制台输出的默认颜色
            }
        }.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Thread");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    // 具体线程的调度完全交给JVM和OS来决定，输出的顺序不可保证 !!
    public void testDemoThread() {
        DemoThread demoThread = new DemoThread();
        demoThread.setName("Demo Thread Name"); // 通过新线程的名称来判断
        demoThread.run(); // 如果显式的调用run()方法, 等效于调Main Thread主线程的run()方法, 该调用不会报错
        demoThread.start(); // Enable the JVM to run the run() method of Thread 线程run()方法的调用是交给JVM去处理
        demoThread.start(); // Throw Exceptions 同一个Thread不可Start启动多次
    }

    // 创建Runnable的实例对象
    public void testRunnable() {
        Thread runThread = new Thread(new DemoRunnable());
        runThread.start();
    }

    // 将B线程join到A线程，可以确保在A线程执行完成之后再紧接着执行B线程     ====> C#区别：Task.ContinueWith(() => {})
    // 1. 应用场景：在等待是数据fetch之后，再执行相应的操作
    // 2. 如果A线程在指定的时间内没有结束，可以设置time out"强制"唤醒B线程
    public void testJoinThread() {
        DemoThread threadB = new DemoThread();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A running ...");
                try {
                    System.out.println("Thread A complete ...");
                    threadB.join(); // 紧接着执行B线程的操作
                    threadB.join(3000); // 3S后自动唤起
                    System.out.println("Thread B complete ...");
                } catch (InterruptedException e) {
                    System.out.println("Cannot wait, B be interrupted, terminate prematurely");
                }
            }
        });
        threadA.start();
    }
}

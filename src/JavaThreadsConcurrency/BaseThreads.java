package JavaThreadsConcurrency;

import JavaThreadsConcurrency.Base.DemoRunnable;
import JavaThreadsConcurrency.Base.DemoThread;

import static JavaThreadsConcurrency.Base.ThreadColor.ANSI_GREEN;

/**
 * 1. 大部分的JVM instance实例都启动一个process进程
 * 2. Java Application在运行的时候，拥有独自的Heap(堆)内存空间, 且相互独立
 * 3. Thread线程是process进程的一个执行单元，至少有一个默认的主线程，可创建额外的线程
 * 4. Thread线程的创建会共享Process的Heap内存空间和Files, 同时拥有自己独立的Thread Stack(线程栈)空间
 * 5. 多线程用于执行耗时的操作或者Task, 避免阻塞main thread, 可以放到后台执行
 * 6. Concurrency并发："同时"执行多个操作，一个操作或者Task不需要等到结束再执行别的操作或者Task
 */
// 1. 使用"匿名类型"来创建新的线程
// 2. 通过继承Thread, 实现其中的方法来自定义创建线程
// 3. 通过实现Runnable接口来创建新的线程：只需要实现一个方法 (推荐: 与更多的API交互)
public class BaseThreads {

    // 两种匿名类型的实现方式
    public void testAnonymousClassThread() {
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
        demoThread.run(); // 如果显式的调用run()方法, 等效于调Main Thread主线程的run()方法
        demoThread.start(); // Enable the JVM to run the run() method of Thread 线程run()方法的调用是交给JVM去处理
        demoThread.start(); // Throw Exceptions 同一个Thread不可Start启动多次
    }

    public void testRunnable() {
        Thread runThread = new Thread(new DemoRunnable());
        runThread.start();
    }

    // 如何中断一个线程: 调用要中断线程对象的interruptedMethod方法
    // 由Main Thread主线程中断demoThread，终止其sleep
    public void testInterruptThread() {
        DemoThread demoThread = new DemoThread();
        demoThread.start();
        demoThread.interrupt();
    }

    // 将B线程join到A线程，可以确保在A线程执行完成之后再紧接着执行B线程     ====> C#区别：Task.ContinueWith(() => {})
    // 1. 应用场景：在等待是数据fetch之后，再执行相应的操作
    // 2. 如果A线程在指定的时间内没有结束，可以设置time out唤醒B线程
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
                    System.out.println("Cannot wait, B be interrupted"); // B在join的过程中被中断 terminate prematurely
                }
            }
        });
        threadA.start();
    }

    // 可以给线程设置执行的优先级.setPriority()，但实际执行由JVM控制，或OS不会按照设置的指定优先级来执行 !!!
    // MIN_PRIORITY = 1; MAX_PRIORITY = 10
    public void testThreadPriority() {
        DemoThread demoThread = new DemoThread();
        demoThread.setPriority(5);
    }
}

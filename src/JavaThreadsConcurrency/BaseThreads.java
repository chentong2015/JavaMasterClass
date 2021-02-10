package JavaThreadsConcurrency;

// TODO: optimistic locking and pessimistic locking 乐观锁，悲观锁
// https://docs.oracle.com/javaee/7/tutorial/persistence-locking001.htm
// https://www.objectdb.com/java/jpa/persistence/lock#Optimistic_Locking

import JavaThreadsConcurrency.Model.DemoThread;

import static JavaThreadsConcurrency.Model.ThreadColor.ANSI_GREEN;

/**
 * 1. 大部分的JVM instance实例都启动一个process进程
 * 2. Java Application在运行的时候，拥有独自的Heap(堆)内存空间, 且相互独立
 * 3. Thread线程是process进程的一个执行单元，至少有一个默认的主线程，可创建额外的线程
 * 4. Thread线程的创建会共享Process的内存空间和Files, 同时拥有自己独立的Thread Stack(线程栈)空间
 * 5. 多线程用于执行耗时的操作或者Task, 避免阻塞main thread, 可以放到后台执行
 * 6. Concurrency并发："同时"执行多个操作，一个操作或者Task不需要等到结束再执行别的操作或者Task
 */
public class BaseThreads {

    // Thread线程运行的先后无法保证, 默认由系统来进行调度, 但可以设置线程的优先级
    // 同一个Thread不可Start启动多次
    public void testDemoThread() {
        DemoThread demoThread = new DemoThread();
        // Enable the JVM to run the run() method of Thread
        demoThread.start();
        demoThread.start(); // Throw Exceptions
    }

    // 给线程的输出添加颜色，前面的颜色设置对后面的输出有影响
    public void testAnonymousClassThread() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "From anonymous class");
            }
        }.start();
    }

    // 通过实现Runnable()来创建新的线程
    public void testRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Thread");
            }
        };
        // runnable.run();
    }

}

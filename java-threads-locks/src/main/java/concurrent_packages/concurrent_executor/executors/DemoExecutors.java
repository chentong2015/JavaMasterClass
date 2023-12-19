package concurrent_packages.concurrent_executor.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Executors: 线程池工具类，封装线程池的初始化
// Java自带的线程池工具类, 可能并不适合互联网高并发场景 !!
public class DemoExecutors {

    // newCachedThreadPool();    有多少个任务就会创建多少线程，创建和调度线程耗CPU100%，但不造成OOM
    // newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
    // newSingleThreadExecutor() 积累到阻塞队列，造成OOM
    public void testExecutors() {
        ExecutorService service1 = Executors.newCachedThreadPool();         // corePoolSize 核心线程数 0 - Integer.MAX_VALUE
        ExecutorService service2 = Executors.newFixedThreadPool(3); // 只会在线程池中创建指定数量的线程 3 - 3
        ExecutorService service3 = Executors.newSingleThreadExecutor();     // 线程池中始终只有一个线程，不断处理 1 - 1
        ExecutorService service4 = Executors.newScheduledThreadPool(10); // 支持定时与周期性任务的线程池
        for (int index = 0; index < 100; index++) {
            // executorService1.execute(new MyTask());
        }
    }

    public void testThreadFactory() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "number: " + threadNumber);
            }
        });
        executorService.shutdown();

        // TODO. Blocks until all tasks have completed execution after a shutdown request,
        //   or the timeout occurs, or the current thread is interrupted
        try {
            executorService.awaitTermination(1000, TimeUnit.MICROSECONDS);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    // TODO. setDaemon(true) 在线程池中创建出来的是守护线程，不会阻止JVM的退出
    // 在用户线程结束的时候，JVM退出，后台的守护线程也自动结束
    public void testThreadScheduledExecutor() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setName("Thread name");
            t.setDaemon(true);
            return t;
        });
        // 使用method reference来调用方法，周期性的执行指定的逻辑
        scheduler.scheduleAtFixedRate(this::taskMethod, 0, 1000, TimeUnit.MINUTES);
    }

    private void taskMethod() {
        // Run this method by the Scheduled thread in the Thread Pool
        System.out.println("run task...");
    }
}

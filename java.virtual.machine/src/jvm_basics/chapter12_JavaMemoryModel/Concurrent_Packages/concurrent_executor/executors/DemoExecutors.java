package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_executor.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// TODO: 大企业一般不推荐使用Java自带的线程池工具类, 不适合互联网高并发场景 !!
// newCachedThreadPool();    有多少个任务就会创建多少线程，创建和调度线程耗CPU100%，但不造成OOM
// newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
// newSingleThreadExecutor() 积累到阻塞队列，造成OOM
public class DemoExecutors {

    // Executors: 线程池工具类，封装线程池的初始化
    public void testExecutors() {
        ExecutorService service1 = Executors.newCachedThreadPool();         // corePoolSize 核心线程数 0 - Integer.MAX_VALUE
        ExecutorService service2 = Executors.newFixedThreadPool(3); // 只会在线程池中创建指定数量的线程 3 - 3
        ExecutorService service3 = Executors.newSingleThreadExecutor();     // 线程池中始终只有一个线程，不断处理 1 - 1
        ExecutorService service4 = Executors.newScheduledThreadPool(10); // 支持定时与周期性任务的线程池
        for (int index = 0; index < 100; index++) {
            // executorService1.execute(new MyTask());
        }
    }

    // TODO. 使用ThreadFactory来创建线程池中的特定线程
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

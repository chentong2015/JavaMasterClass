package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_executor.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO: 大企业一般不推荐使用Java自带的线程池工具类, 不适合互联网高并发场景 !!
// newCachedThreadPool();    有多少个任务就会创建多少线程，创建和调度线程耗CPU100%，但不造成OOM
// newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
// newSingleThreadExecutor() 积累到阻塞队列，造成OOM

// 企业级使用线程池策略：针对任务的类型来调配
// 1. CPU密集型(读取视频)，不建议设置过多的线程数
// 2. IO密集型(文件读取)，对CPU要求不高，对磁盘和内存要求高，则多设置线程数量
public class ThreadPool {

    // ThreadPoolExecutor 线程池对象
    // public ThreadPoolExecutor(
    //       int corePoolSize,    核心线程数量
    //       int maximumPoolSize, 最大线程数目: corePoolSize+非核心线程
    //       long keepAliveTime,  时间值: 非核心线程的生命周期, 活跃时间
    //       TimeUnit unit,       时间单位
    //       BlockingQueue<Runnable> workQueue,  阻塞队列, 用于存放等待被执行的任务
    //       ThreadFactory threadFactory,        由ThreadFactory创建线程
    //       RejectedExecutionHandler handler) { 阻塞队列存放线程的拒绝策略
    //          1. CallerRunsPolicy
    //          2. AbortPolicy 抛异常，默认策略
    //          3. DiscardPolicy 放弃任务，一旦被拒绝则放弃掉
    //          4. DiscardOldestPolicy 放弃最老的任务
    //          5. 自定义策略，实现RejectedExecutionHandler接口
    //       ...
    // }

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

    // 线程复用机制：如果线程完成task之后，再去做别的等待执行的task
    //  public static ExecutorService newCachedThreadPool() {
    //      return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
    //      new SynchronousQueue<Runnable>());
    //  }

    // 链表阻塞队列: 有界队列, 容量为Integer.MAX_VALUE, 足够容纳线程数目
    // public static ExecutorService newFixedThreadPool(int nThreads) {
    //      return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
    //      new LinkedBlockingQueue<Runnable>());
    //  }
    //

    // 只有一个核心线程负责执行task，其他的task全部放到阻塞线程队列中
    // public static ExecutorService newSingleThreadExecutor() {
    //     return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,
    //     0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
    // }
}

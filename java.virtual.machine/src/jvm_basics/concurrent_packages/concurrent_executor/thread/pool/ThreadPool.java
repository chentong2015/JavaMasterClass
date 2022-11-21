package jvm_basics.concurrent_packages.concurrent_executor.thread.pool;

import java.util.concurrent.*;

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

    // 线程复用机制：如果线程完成task之后，再去做别的等待执行的task
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    // 链表阻塞队列: 有界队列, 容量为Integer.MAX_VALUE, 足够容纳线程数目
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    // 只有一个核心线程负责执行task，其他的task全部放到阻塞线程队列中
    public static ExecutorService newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        // return new FinalizableDelegatedExecutorService(executor);
    }
}

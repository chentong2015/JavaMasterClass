package jvm_basics.chapter13_Lock_ThreadSafety.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// TODO: 大企业一般不推荐使用Java自带的线程池工具类, 不适合互联网高并发场景 !!
// newCachedThreadPool();    有多少个任务就会创建多少线程, 创建和调度线程耗CPU100%, 但不造成OOM
// newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
// newSingleThreadExecutor() 积累到阻塞队列，造成OOM

// 企业级使用线程池策略：针对任务的类型来调配
// 1. CPU密集型(读取视频)，不建议设置过多的线程数
// 2. IO密集型，对CPU要求不高，对磁盘和内存要求高，则多设置线程数量
public class BaseThreadPool {

    // ThreadPoolExecutor 线程池对象
    // public ThreadPoolExecutor(
    //       int corePoolSize,    核心线程数量
    //       int maximumPoolSize, 最大线程数目: corePoolSize+非核心线程
    //       long keepAliveTime,  时间值: 非核心线程的生命周期, 活跃时间
    //       TimeUnit unit,       时间单位
    //       BlockingQueue<Runnable> workQueue,  阻塞队列, 用于存放等待被执行的任务
    //       ThreadFactory threadFactory,        由ThreadFactory创建线程
    //       RejectedExecutionHandler handler) { 阻塞队列存放线程的拒绝策略
    //           // 1. DiscardOldestPolicy
    //           // 2. CallerRunsPolicy
    //           // 3. AbortPolicy
    //           // 4. DiscardPolicy
    //       ...
    // }

    // Executors: 线程池工具类
    public void testExecutors() {
        ExecutorService service1 = Executors.newCachedThreadPool();         // corePoolSize 核心线程数 0 - Integer.MAX_VALUE
        ExecutorService service2 = Executors.newFixedThreadPool(3); // 只会在线程池中创建指定数量的线程 3 - 3
        ExecutorService service3 = Executors.newSingleThreadExecutor();     // 线程池中始终只有一个线程，不断处理 1 - 1
        for (int index = 0; index < 100; index++) {
            // executorService1.execute(new MyTask());
        }
    }

    // 线程复用机制：如果线程完成task之后，再去做别的等待执行的task
    //  public static ExecutorService newCachedThreadPool() {
    //      return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
    //      new SynchronousQueue<Runnable>());
    //  }

    // 链表阻塞队列: 容量为Integer.MAX_VALUE, 足够容纳线程数目
    // public static ExecutorService newFixedThreadPool(int nThreads) {
    //      return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
    //      new LinkedBlockingQueue<Runnable>()); 多余的task会放置到一个队列中，等待被执行
    //  }
    //

    // 只有一个核心线程负责执行task，其他的task全部放到阻塞线程队列中
    // public static ExecutorService newSingleThreadExecutor() {
    //     return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,
    //     0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
    // }
    
    // TODO: ExecutorService接口提供的主要方法
    // 1. Future<T> submit(Runnable task, T result); 能够获取在线程接受后返回的结果
    // 2. Future<T> submit(Callable<T> task);
    // 3. void shutdown();              等待queue队列中的所有线程都运行完成, 然后停止, 不会再接受任何新的task
    // 4. List<Runnable> shutdownNow();
    private static void testGetThreadBackValue() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> "Back value");
        try {
            // 在获取返回值时会阻塞当前的(main)线程, 不可在UI线程中使用 !!
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }
}

package concurrent_packages.concurrent_executor.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// TODO: 线程池本身是如何保证线程安全的 ?
// 用一个int值记录线程池的生命状态, 并且保证是原子操作AtomicInteger
// private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
// private static final int COUNT_BITS = Integer.SIZE - 3;      留出3个bit位
// private static final int COUNT_MASK = (1 << COUNT_BITS) - 1; 低位的29位全部设置成1，作为Mask
//
// runState is stored in the high-order bits 运行时的状态使用最高位的3位来记录
// private static final int RUNNING    = -1 << COUNT_BITS; 右移动29位
//    负数二进制：正数的反码 + 1
//    RUNNING = 1110 0000 0000 0000 0000 0000 0000 0000
// private static final int SHUTDOWN   =  0 << COUNT_BITS;
// private static final int STOP       =  1 << COUNT_BITS;
//    STOP = 0001 0000 0000 0000 0000 0000 0000 0000
// private static final int TIDYING    =  2 << COUNT_BITS;
// private static final int TERMINATED =  3 << COUNT_BITS;
public class ThreadPoolSourceCode {

    // TODO: 根据业务场景自定义ThreadPoolExecutor线程池, 配置合理参数
    // 1. 在执行前面的线程的过程中，先来的线程任务不一定会先执行完 ==> 最终的调度由OS决定 !!
    // 2. 如果执行100个线程任务，则第31个线程任务会出现Rejected(无法被执行，也无法放到阻塞队列)
    public void testMyThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 20, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));
        for (int index = 0; index < 100; index++) {
            // threadPoolExecutor.execute(new Task());
        }
    }

    // TODO: 提交优先级: coreThreads > 阻塞队列 > notCoreThreads
    // public void execute(Runnable command) {
    //     if (command == null)
    //         throw new NullPointerException();
    //     int c = ctl.get();                     主线程的控制状态, int类型的
    //     if (workerCountOf(c) < corePoolSize) { 如果效于核心主线程数目，则添加coreWorker
    //         if (addWorker(command, true))      true表示使用corePoolSize
    //            return;
    //         c = ctl.get();
    //     }
    //     if (isRunning(c) && workQueue.offer(command)) { 添加到阻塞队列中, offer()使用在容量受限的队列 !!
    //         int recheck = ctl.get();
    //         if (! isRunning(recheck) && remove(command))从阻塞队列中移除command
    //             reject(command);
    //         else if (workerCountOf(recheck) == 0)       此时线程池中主核心worker为0
    //             addWorker(null, false);
    //     } else if (!addWorker(command, false)) false表示不使用corePoolSize，在非主核心线程上添加addWorker()
    //         reject(command);                   如果添加不成功，则说明workQueue和maximumPoolSize都满了，造成拒绝 !!
    // }


    // 在构造Workers时，将用户自定义的task设置成firstTask的值，后面调用它的run()方法
    //  Worker(Runnable firstTask) {
    //      setState(-1); // inhibit interrupts until runWorker
    //      this.firstTask = firstTask;
    //      this.thread = getThreadFactory().newThread(this); 使用ThreadFactory创建的Thread，"自封装"work到线程中，调用.start()启动 !!
    //  }

    // 创建Worker线程: Worker extends AbstractQueuedSynchronizer implements Runnable 线程池中线程的载体 !!
    // 启动work线程  : 调用Worker实现的方法: void run() { runWorker(this); }
    // private boolean addWorker(Runnable firstTask, boolean core) {
    //   try {
    //      w = new Worker(firstTask);
    //      final BaseThread t = w.thread;
    //      if (t != null) {
    //        ....
    //        if (workerAdded) {
    //            t.start();  启动Worker对象中"自封装"的线程，也就是自身
    //            workerStarted = true;
    //        }

    // TODO: 执行优先级: coreThreads 优先执行 > notCoreThreads > 阻塞队列中线程任务优先级最低(处于等待)
    // final void runWorker(Worker w) {
    //    BaseThread wt = BaseThread.currentThread();
    //    Runnable task = w.firstTask;
    //    w.firstTask = null;
    //    w.unlock(); // allow interrupts
    //    boolean completedAbruptly = true;
    //    try {
    //        TODO: 执行优先级的条件是先判断task为null(在core核心线程中与非核心的线程中的task)
    //              然后才会通过getTask()方法从workQueue中取出线程任务 !!
    //        while (task != null || (task = getTask()) != null) {
    //            w.lock();
    //            // If pool is stopping, ensure thread is interrupted; if not, ensure thread is not interrupted.
    //            // This requires a recheck in second case to deal with shutdownNow race while clearing interrupt
    //            ....
    //            try {
    //                beforeExecute(wt, task);
    //                try {
    //                    TODO: 最终调用自定义的封装在worker的firstTask=task的run方法 ==> 相当于线程级别的调用 !!
    //                          不是.start()线程启动的方式创建多线程
    //                    task.run();
    //                    afterExecute(task, null);
    //                } catch (Throwable ex) {
    //                    afterExecute(task, ex);
    //                    throw ex;
    //                }
    //            } ...
    //     } finally {
    //        processWorkerExit(w, completedAbruptly);
    //     }

    // 最终在processWorkerExit()方法中调用addWorker()方法，回到前面的状态 ===> 线程复用机制
    // private void processWorkerExit(Worker w, boolean completedAbruptly) {
    //     ...
    //     int c = ctl.get();
    //     if (runStateLessThan(c, STOP)) {
    //         if (!completedAbruptly) {
    //             int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
    //             if (min == 0 && ! workQueue.isEmpty())
    //                 min = 1;
    //             if (workerCountOf(c) >= min)
    //                 return; // replacement not needed
    //         }
    //         addWorker(null, false);
    //     }
    // }
}

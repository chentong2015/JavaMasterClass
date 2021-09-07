package jvm_basics.chapter13_Lock_ThreadSafety.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SourceCodeThreadPool {

    // TODO: 根据业务场景自定义ThreadPoolExecutor线程池, 配置合理参数 !!
    // 根据自定义配置，如果执行100个线程任务，则第31个线程任务会出现Rejected：无法被执行，也无法放到阻塞队列
    public void testMyThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 20, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int index = 0; index < 100; index++) {
            // threadPoolExecutor.execute(new Task());
        }
    }

    // TODO: 提交优先级(源码)
    // 1. coreThreads 优先级更高
    // 2. coreThreads 处理不了，则会提交给阻塞队列
    // 3. 阻塞队列存不下，则提交给notCoreThreads
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

    // private boolean addWorker(Runnable firstTask, boolean core) {
    //   try {
    //      w = new Worker(firstTask); 创建Worker线程: Worker extends AbstractQueuedSynchronizer implements Runnable
    //      final Thread t = w.thread;
    //      if (t != null) {
    //        ....
    //        if (workerAdded) {
    //            t.start();           启动work线程，调用Worker实现的方法: void run() { runWorker(this); }
    //            workerStarted = true;
    //        }
    //      ...

    // TODO: 执行优先级(源码)
    // 1. coreThreads 优先执行
    // 2. notCoreThreads 其次优先级
    // 3. 阻塞队列中线程任务优先级最低(处于等待)
    //   final void runWorker(Worker w) {
    //      Thread wt = Thread.currentThread();
    //      Runnable task = w.firstTask;
    //      w.firstTask = null;
    //      w.unlock(); // allow interrupts
    //      boolean completedAbruptly = true;
    //      try {
    //          TODO: 执行优先级的条件是先判断task为null(在core核心线程中与非核心的线程中的task)
    //                然后才会通过getTask()方法从workQueue中取出线程任务 !!
    //          while (task != null || (task = getTask()) != null) {
    //              w.lock();
    //              ....
}

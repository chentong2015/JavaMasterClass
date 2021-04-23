package JavaThreadsConcurrency;

import JavaThreadsConcurrency.Base.model.ThreadColor;
import JavaThreadsConcurrency.Concurrency.ProducerConsumer.LockConsumer;
import JavaThreadsConcurrency.Concurrency.ProducerConsumer.LockProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. Interface Executor:
 * _____ void execute(Runnable command); 用来执行新的线程, 无需创建和启动Thread, 无需对线程进行管理, 只关注在要执行的task上面
 * .
 * 2. Interface ExecutorService extends Executor:
 * _____ <T> Future<T> submit(Runnable task, T result); 支持线程在执行结束后返回结果
 * _____ <T> Future<T> submit(Callable<T> task);
 * _____ void shutdown(); 等待queue队列中的所有线程都运行完成，然后停止，不会再接受任何新的task
 * _____ List<Runnable> shutdownNow(); 立即停止，(不保证)同时清出queue队列中的所有线程
 * .
 * 3. Executors:
 * 使用Factory工厂模式创建实现Executive Service接口的线程池对象, 比如ThreadPoolExecutor
 */
public class BaseThreadsPools {

    /**
     * 1. Thread Pool: 线程池管理线程的集, 优化JVM优化线程调度和管理lifecycle
     * 2. 特别是在具有大量线程的应用中, 可以限制active的线程数量at any one time, 超多最大数目的线程将会等待
     */
    private static void testExecutiveServices() {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);  // Set max number of threads in the thread pool
        LockProducer producer = new LockProducer(buffer, ThreadColor.ANSI_BLACK, bufferLock);
        LockConsumer consumer1 = new LockConsumer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        LockConsumer consumer2 = new LockConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.shutdown();
    }

    /**
     * 1. submit(Callable<T> task) 提供在新的线程中执行的task
     * 2. future.get(): 在获取返回值时会阻塞当前的(main)线程, 不可在UI线程中使用 !!
     */
    private static void testGetThreadBackValue() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // Callable<T> 会添加一个新的Thread到线程池中，如果没有超过线程池的最大线程数量 !!
        Future<String> future = executorService.submit(() -> "Back value");
        try {
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }
}

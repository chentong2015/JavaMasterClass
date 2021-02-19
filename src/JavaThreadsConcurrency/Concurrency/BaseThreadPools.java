package JavaThreadsConcurrency.Concurrency;

import JavaThreadsConcurrency.Base.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Executive service interface extends Executor
 * 1. Interface Executor:
 * _____ void execute(Runnable command); 用来执行新的线程; 等效于thread.start();
 * 2. Interface ExecutorService:
 * ----- <T> Future<T> submit(Runnable task, T result); 支持线程在执行结束后返回结果
 * ----- <T> Future<T> submit(Callable<T> task);
 * ----- void shutdown(); 必须关闭Service, 否则会持续在后台执行
 * ----- List<Runnable> shutdownNow(); 立即停止
 * 3.创建Executive service interface实现类型的实例对象ThreadPoolExecutor
 */
public class BaseThreadPools {

    /**
     * 1. Thread Pool: 线程池管理线程的集, 有JVM优化线程之间调度和life cycles的管理
     * 2. ThreadPoolExecutor extends AbstractExecutorService implements ExecutorService
     */
    private static void testExecutiveServices() {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);  // Set max number of threads in the thread pool
        ReentrantProducer producer = new ReentrantProducer(buffer, ThreadColor.ANSI_BLACK, bufferLock);
        ReentrantConsumer consumer1 = new ReentrantConsumer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        ReentrantConsumer consumer2 = new ReentrantConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.shutdown();
    }

    /**
     *
     */
    private static void testGetThreadBackValue() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Callable class");
                return "Back value";
            }
        });

        // future.get(): 在获取返回值时会阻塞当前的线程: 不可在UI线程中使用 !!
        try {
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }
}

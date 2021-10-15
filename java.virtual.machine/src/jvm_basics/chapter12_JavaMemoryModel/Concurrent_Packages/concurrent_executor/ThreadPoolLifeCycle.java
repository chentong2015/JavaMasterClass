package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// TODO: 线程池中线程的五种状态
// RUNNING:  Accept new tasks and process queued tasks
// SHUTDOWN: Don't accept new tasks, but process queued tasks
// STOP:     Don't accept new tasks, don't process queued tasks,
//           and interrupt in-progress tasks
// TIDYING:  All tasks have terminated, workerCount is zero,
//           the thread transitioning to state TIDYING
//           will run the terminated() hook method
// TERMINATED: terminated() has completed
public class ThreadPoolLifeCycle {

    // ExecutorService接口提供的主要方法
    // 1. Future<T> submit(Runnable task, T result); 能够获取在线程接受后返回的结果
    //    实际会创建class FutureTask<V> implements RunnableFuture<V>
    // 2. Future<T> submit(Callable<T> task);
    // 3. void shutdown();                   不会再接受任何新的task, 等待queue队列中的所有线程都运行完成, 然后停止
    // 4. List<Runnable> shutdownNow();      正在执行的线程被中断，安全退出，不接收新的任务，也不会执行阻塞队列中的任务
    private static void testGetThreadBackValue() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> "Back value");
        try {
            // TODO: 获取返回值时会阻塞当前(main)线程, 不可在UI线程中使用
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }
}

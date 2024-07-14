package multi_threading.thread_pool;

import java.util.concurrent.*;

// Executors: 线程池工具类，封装线程池的初始化
// newCachedThreadPool();    有多少任务就会创建多少线程，创建和调度线程耗CPU100%，不造成OOM
// newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
// newSingleThreadExecutor() 只有一个线程，多任务积累到阻塞队列，造成OOM
public class JavaExecutorService {

    // TODO. 如果不设置shutdown()，线程池中的线程不会结束，程序不会结束
    //  - Shutdown orderly for previously submitted tasks, no new tasks will be accepted.
    //  - Invocation has no additional effect if already shut down.
    //  - Does not wait for previously submitted tasks to complete. 不会等待全部的task结束
    public static void main(String[] args) throws InterruptedException {
        String[] list = {"item01", "item02", "item03", "item04"};
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                for (String item : list) {
                    System.out.println(item);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        });

        // TODO. shutdown()不会阻塞主线程
        // 确保线程池中所有的线程结束, 不在接受新的task执行任务, 并且不会再次复用
        // 如果不调用.shutdown(), 虚拟机可能不会退出
        executorService.shutdown();

        // Attempts to stop all actively executing tasks
        // 正在执行的线程被中断，安全退出，不接收新的任务也不会执行阻塞队列中的任务
        executorService.shutdownNow();

        // Returns true if all tasks have completed following shut down.
        // Note that isTerminated is never true unless either shutdown or shutdownNow was called first.
        while (!executorService.isTerminated()) {
            // 判断线程池中所有task全部执行结束
        }
        System.out.println("All tasks done");

        // TODO. Blocks until all tasks have completed execution after a shutdown request,
        //  or the timeout occurs, or the current thread is interrupted
        boolean isCompleted = executorService.awaitTermination(5, TimeUnit.SECONDS);
    }

    // TODO. 获取线程池中线程执行后的返回值
    // 1. Future<T> submit(Runnable task, T result);
    // 2. Future<T> submit(Callable<T> task);
    private static void testGetThreadBackValue() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 使用线程池中的线程来异步执行, 等待结束后获取返回数据为止
        Future<String> future = executorService.submit(() -> "Back value");
        try {
            // TODO: 获取返回值时会阻塞当前(main)线程, 不可在UI线程中使用
            // Waits if necessary for the computation to complete, and then retrieves its result.
            System.out.println(future.get());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        executorService.shutdown();
    }

    public void testSendRequestAsync() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> "back value");
        try {
            // 只会做一次判断，之后获取数据的时候，仍然会阻塞当前的线程
            if (future.isDone()) {
                // Wait until a response is received
                String responseJson = future.get();
                // Send http request
            }
        } catch (Exception exception) {
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}

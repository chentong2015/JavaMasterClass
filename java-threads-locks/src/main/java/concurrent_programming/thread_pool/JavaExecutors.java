package concurrent_programming.thread_pool;

import java.util.concurrent.*;

// Executors: 线程池工具类，封装线程池的初始化
public class JavaExecutors {

    // newCachedThreadPool();    有多少个任务就会创建多少线程，创建和调度线程耗CPU100%，但不造成OOM
    // newFixedThreadPool(3);    任务增多，积累到阻塞队列中，内存无限增多，造成OOM
    // newSingleThreadExecutor() 积累到阻塞队列，造成OOM
    public void testExecutors() {
        // corePoolSize 核心线程数 0 - Integer.MAX_VALUE
        ExecutorService service1 = Executors.newCachedThreadPool();
        // 只会在线程池中创建指定数量的线程 3 - 3
        ExecutorService service2 = Executors.newFixedThreadPool(3);
        // 线程池中始终只有一个线程，不断处理 1 - 1
        ExecutorService service3 = Executors.newSingleThreadExecutor();

        // 支持定时与周期性任务的线程池
        ExecutorService service4 = Executors.newScheduledThreadPool(10);
        for (int index = 0; index < 100; index++) {
            // executorService1.execute(new MyTask());
        }
    }

    // TODO. 如果不设置shutdown()，线程池中的线程不会结束，程序不会结束
    //  - Shutdown orderly for previously submitted tasks, no new tasks will be accepted.
    //  - Invocation has no additional effect if already shut down.
    //  - Does not wait for previously submitted tasks to complete. 不会等待全部的task结束
    public static void main(String[] args) {
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

        // 确保线程池中所有的线程结束, 不在接受新的task执行任务, 并且不会再次复用
        // 如果不调用.shutdown(), 虚拟机可能不会退出
        executorService.shutdown();
        System.out.println("finish ...");

        // TODO. Blocks until all tasks have completed execution after a shutdown request,
        //  or the timeout occurs, or the current thread is interrupted
        try {
            boolean isCompleted = executorService.awaitTermination(5, TimeUnit.SECONDS);
            if (isCompleted) {
                System.out.println("Completed all successfully");
            } else {
                // 在指定的时间的内线程任务没有完成
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}

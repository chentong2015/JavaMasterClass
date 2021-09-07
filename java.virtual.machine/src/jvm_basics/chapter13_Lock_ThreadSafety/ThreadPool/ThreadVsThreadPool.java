package jvm_basics.chapter13_Lock_ThreadSafety.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 使用线程池的理由
// 1. 在某种场景下，使用线程池拥有更好的性能，尽可能的重用线程
// 2. 使用线程池无需关注线程创建和start启动，而是关注在具体执行的任务上面: 优化JVM优化线程调度和管理lifecycle
public class ThreadVsThreadPool {

    // 性能低的原因：不停的创建新的线程, 线程的切换造成性能低下
    private void testUsingThreads() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    // 使用线程池执行的时间会优化1000倍
    // 1. 线程池的处理，并不会创建1000个新的线程，因此性能高
    private void testUsingThreadPool() {
        Long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int index = 0; index < 1000; index++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executorService.shutdown(); // 确保线程池中所有的线程结束
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

package concurrent_thread_pool.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// 使用线程池的理由
// 1. 在某种场景下，使用线程池拥有更好的性能，尽可能的重用线程(线程缓存)
// 2. 提高响应的速度，无需等待线程创建完成再执行
// 3. 提供线程的统一分配和调控，只需要重点关注线程执行的task任务上
public class BaseThreadPool {

    // 性能低：不停的创建新线程, 线程的创建和切换会造成性能低下
    private void testUsingThreads() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < 1000; index++) {
            Thread thread = new Thread(() -> list.add(random.nextInt()));
            thread.start(); // 创建多线程，去调用线程的run()方法: 方法级别的调用 !!
            thread.join();  // 等着线程执行结束
        }
        for (int index = 0; index < 1000; index++) {
            // threads[index].join(); 等待线程数组中指定的线程结束
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    // TODO: 整个方法只会创建2个线程: 一个主线程 + 一个线程池中的线程
    // 使用线程池避免大量线程的创建和销毁，提升应用的性能(同场景优化1000倍)
    private void testUsingThreadPool() {
        Long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int index = 0; index < 1000; index++) {
            executorService.execute(() -> list.add(random.nextInt()));
        }
        executorService.shutdown();
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    // TODO. 使用线程工厂来创建线程池中要执行的线程
    private void testUsingThreadFactory() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                String name = "name:" + threadNumber;
                return new Thread(runnable, name);
            }
        });
        executorService.shutdown();
    }
}

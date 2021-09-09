package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 使用线程池的理由
// 1. 在某种场景下，使用线程池拥有更好的性能，尽可能的重用线程(线程缓存)
// 2. 提高相应的速度，无需等待线程创建完成再执行
// 3. 提供线程的统一分配和调控，只需要重点关注线程执行的task任务上

// 使用场景：
// 1. 单个任务时间比较短
// 2. 需要处理的任务数量很大  ==> ForkJoinPool 专门处理计算密集型任务
public class ThreadPoolAndThread {

    // 性能低：不停的创建新的线程, 线程的切换造成性能低下
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
            thread.start(); // 创建多线程，去调用线程的run()方法: 方法级别的调用 !!
            thread.join();  // 等着线程执行结束
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
            // TODO: 这里创建的对象，其中的run()方法会被"线程级别"的调用，不会产生多线程 !!
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
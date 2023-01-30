package jvm_basics.concurrent_packages.concurrent_executor.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 使用线程池的理由
// 1. 在某种场景下，使用线程池拥有更好的性能，尽可能的重用线程(线程缓存)
// 2. 提高响应的速度，无需等待线程创建完成再执行
// 3. 提供线程的统一分配和调控，只需要重点关注线程执行的task任务上
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
            // TODO: 这里创建的对象，其中的run()方法会被"线程级别"的调用，不会产生多线程 !!
            executorService.execute(() -> list.add(random.nextInt()));
        }
        executorService.shutdown();
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    // TODO. 主线程不会受到阻塞，如果不设置，线程池中的线程不会结束，程序不会结束
    //      Initiates an orderly shutdown in which previously submitted tasks are executed,
    //      but no new tasks will be accepted. 不在接受新的task执行任务
    //      Invocation has no additional effect if already shut down.
    public static void main(String[] args) {
        String[] list = {"item01", "item02", "item03", "item04"};
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                for (String item : list) {
                    System.out.println(item);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        });

        // 确保线程池中所有的线程结束, 并且不会再次复用
        // 如果不调用.shutdown(), 虚拟机可能不会退出
        executor.shutdown();
        System.out.println("finish ...");
    }
}

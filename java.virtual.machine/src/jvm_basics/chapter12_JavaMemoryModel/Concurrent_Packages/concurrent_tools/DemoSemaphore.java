package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_tools;

import java.util.concurrent.Semaphore;

public class DemoSemaphore {

    private static Semaphore semaphore1 = new Semaphore(1);
    private static Semaphore semaphore2 = new Semaphore(1);

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            System.out.println("线程1执行结束，释放，导致semaphore1许可证+1");
            semaphore1.release();
        });

        final Thread thread2 = new Thread(() -> {
            try {
                semaphore1.acquire();
                System.out.println("线程2拿到semaphore1上许可证，执行结束后，释放，semaphore2许可证+1");
                semaphore2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                semaphore2.acquire(); // 线程3必须拿到semaphore2上的许可证
                thread2.join();       // 同时等待线程2彻底结束(如果线程2释放semaphore2许可证之后，还有耗时的操作需要执行)
                semaphore2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

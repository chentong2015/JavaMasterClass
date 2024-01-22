package concurrenty_packages.concurrent_tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DemoCyclicBarrier {

    private static CyclicBarrier barrier1 = new CyclicBarrier(2);
    private static CyclicBarrier barrier2 = new CyclicBarrier(2);

    public static void main(String[] args) {

        final Thread thread1 = new Thread(() -> {
            try {
                System.out.println("线程1优先执行");
                barrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        final Thread thread2 = new Thread(() -> {
            try {
                barrier1.await();
                System.out.println("前面线程1已经执行结束，然后线程2再次进入barrier状态");
                barrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        final Thread thread3 = new Thread(() -> {
            try {
                barrier2.await();
                System.out.println("线程2和3同被释放，执行结束");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

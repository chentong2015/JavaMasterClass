package concurrent_api.tools;

import java.util.concurrent.CountDownLatch;

// 使用倒计时锁存器来让线程按照顺序执行: A -> B -> C
public class DemoCountDownLatch {

    // CountDownLatch倒计时锁上所需要等待的线程数都是1
    private static CountDownLatch c1 = new CountDownLatch(1);
    private static CountDownLatch c2 = new CountDownLatch(1);

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            System.out.println("Finish thread 1");
            // 对c1倒计时-1
            c1.countDown();
        });

        final Thread thread2 = new Thread(() -> {
            try {
                // 等待c1倒计时，计时为0则往下运行
                c1.await();
                System.out.println("Finish thread 2");
                // 对c2倒计时-1
                c2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                // 等待c2倒计时，计时为0则往下运行
                c2.await();
                System.out.println("Finish thread 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

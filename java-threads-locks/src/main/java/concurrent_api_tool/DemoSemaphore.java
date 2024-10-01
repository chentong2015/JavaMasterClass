package concurrent_api_tool;

import java.util.concurrent.Semaphore;

public class DemoSemaphore {

    private static Semaphore semaphore1 = new Semaphore(1);
    private static Semaphore semaphore2 = new Semaphore(1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 finish - release semaphore1");
            semaphore1.release();
        });

        Thread thread2 = new Thread(() -> {
            try {
                semaphore1.acquire();
                System.out.println("Thread 2 finish - release semaphore2");
                semaphore2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                semaphore2.acquire();
                System.out.println("Wait Thread 2 to Close");
                thread2.join();

                System.out.println("Thread 3 finish");
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

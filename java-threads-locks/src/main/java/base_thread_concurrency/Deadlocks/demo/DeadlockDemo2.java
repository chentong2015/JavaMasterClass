package base_thread_concurrency.Deadlocks.demo;

// 2. 两个线程相互拥有各自的Lock，同时在等待对方释放lock
public class DeadlockDemo2 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void testDeadLocks() {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("BaseThread 1: get lock 1");
                sleepThread();
                synchronized (lock2) {
                    System.out.println("BaseThread 1: get lock 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("BaseThread 2: get lock 2");
                sleepThread();
                synchronized (lock1) {
                    System.out.println("BaseThread 2: get lock 1");
                }
            }
        }).start();
    }

    private void sleepThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

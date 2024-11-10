package concurrent_program.issues.deadlocks;

// 两个线程相互拥有各自的Lock，同时等待对方释放lock
public class DeadlockDemo2 {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private void testDeadLocks() {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("BaseThread 1: get lock 1");
                sleepThread();
                // lock1.wait(); 解除死锁
                synchronized (lock2) {
                    System.out.println("BaseThread 1: get lock 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("BaseThread 2: get lock 2");
                sleepThread();
                // lock2.wait(); 解除死锁
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

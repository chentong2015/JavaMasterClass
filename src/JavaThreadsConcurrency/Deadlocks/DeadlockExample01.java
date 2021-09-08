package JavaThreadsConcurrency.Deadlocks;

/**
 * DeadLocks 基本解决方案：
 * 1. 只使用一个lock锁, 在线程之间共享, 但是有局限性
 * 2. 所有的线程获取锁的顺序保持一致, lock1 -> lock2 -> lock3
 * 3. 考虑使用lock objects(锁定关联对象上的monitor), 而非使用synchronized blocks
 * 4. 考虑是否过度的synchronizing代码 ?
 * 5. 考虑能够避免逻辑上的circular ?
 * 6. 考虑能否使用ReentrantLock object来实现 ?
 */
public class DeadlockExample01 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    // DeadLocks 死锁的场景 01：两个线程相互拥有各自的Lock，同时在等待对方释放lock
    private void testDeadLocks() {
        getThread1().start();
        getThread2().start();
    }

    private Thread getThread1() {
        return new Thread(() -> {
            synchronized (lock1) {
                System.out.println("BaseThread 1: get lock 1");
                sleepThread();
                synchronized (lock2) {
                    System.out.println("BaseThread 1: get lock 2");
                }
            }
        });
    }

    private void sleepThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Thread getThread2() {
        return new Thread(() -> {
            synchronized (lock2) {
                System.out.println("BaseThread 2: get lock 2");
                sleepThread();
                synchronized (lock1) {
                    System.out.println("BaseThread 2: get lock 1");
                }
            }
        });
    }
}

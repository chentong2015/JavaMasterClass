package jvm.chapter13;

/**
 * Thread Starvation 线程饥饿: 一个线程很少有机会能够被运行到，没有什么机会获得锁 !!
 * 1. 由线程优先级所造成, 优先级高的会优先于其他低的, 在等待的线程
 * 2. synchronized同步机制并不是first come first served, 可能导致有些线程等待时间过长，无法得到执行
 */
public class JavaReentrantLock {

    /**
     * TODO: Java使用的线程调度方式是抢占式调度，设置的优先级不是稳定的调节手段，最终调度由操作系统去处理
     * MIN_PRIORITY = 1 ~ 10;
     * Priority优先级只是给OS一个执行的建议，不是准确的执行顺序
     */
    private void testThreadStarvation() {
        Thread thread1 = getThread("Thread 1");
        Thread thread2 = getThread("Thread 2");
        thread1.setPriority(8);
        thread2.setPriority(6);
    }

    // 解决方案1：
    // 1. 所有的线程使用同一个锁，只有最终获得lock的线程能得到执行，线程之间没有数据的Interference
    // 2. TODO: synchronized是非公平的，等待同样一个锁时，按照申请的时间依次获得
    // 3. synchronized可能造成线程饥饿，对性能的影响和公平锁基本相当 !!
    // 4. TODO: synchronized会由Java虚拟机来保证在出现异常时，锁能够被自动释放
    private Object lock = new Object();

    private Thread getThread(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    System.out.println(threadName + i);
                }
            }
        }, threadName);
    }

    // 解决方案2:
    // 1. 正在等待reentrantLock锁的线程可以选择放弃等待
    // 2. TODO: true: 作为fair lock公平锁使用，在释放锁时，任何一个等待锁的线程都有机会获得锁
    // 3. 公平锁会耗费额外的layer of processing去管理和确保公平的获得锁，对性能影响较大，减低吞吐量 !!
    // 4. 必须确保在finally语句快中释放掉锁
    private java.util.concurrent.locks.ReentrantLock reentrantLock = new java.util.concurrent.locks.ReentrantLock(true);

    private Thread getThreadWithReentrantLock(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                reentrantLock.lock();
                try {
                    System.out.println(threadName + i);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, threadName);
    }
}

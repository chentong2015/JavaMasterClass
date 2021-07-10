package JavaThreadsConcurrency.ThreadStarvation;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread Starvation 线程饥饿: 一个线程很少有机会能够被运行到，没有什么机会获得锁 !!
 * 1. 由线程优先级所造成, 优先级高的会优先于其他低的, 在等待的线程
 * 2. synchronized同步机制并不是first come first served, 可能导致有些线程等待时间过长，无法得到执行
 * .
 * Thread Starvation 解决方案：Fair locks and live locks
 * 1. true: 作为fair lock公平锁使用, 根据排队等待获得线程锁的数量来做出公平的决策：是选择等待，还是放弃而去执行别的
 * 2. 能保证获取锁的顺序first come first served，不能保证每个线程所等待的时间 (有的线程task可能耗时过长)
 * 3. 对于具有大量的线程，会耗费额外的layer of processing去管理和确保公平的获得锁，对性能有所影响 !!!
 */
public class BaseThreadStarvation {

    /**
     * TODO: Java使用的线程调度方式是抢占式调度，设置的优先级不是稳定的调节手段，最终调度由操作系统去处理
     * MIN_PRIORITY = 1; ~ MAX_PRIORITY = 10
     * Priority优先级只是给OS一个执行的建议，不是准确的执行顺序
     */
    private void testThreadStarvation() {
        Thread thread1 = getThread("Thread 1");
        Thread thread2 = getThread("Thread 2");
        Thread thread3 = getThread("Thread 3");
        thread1.setPriority(8);
        thread2.setPriority(6);
        thread3.setPriority(4);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 1. 所有的线程使用同一个锁，只有最终获得lock的线程能得到执行，输出
     * 2. 所有线程之间没有数据的Interference干扰，没有共享的变量
     */
    // 使用synchronized机制的3个理由：
    // 线程执行非常快，基本不会出现线程饥饿问题，线程饥饿所带来的影响低于使用fair lock所带来的性能影响 !!
    private Object lock = new Object();

    private Thread getThread(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " output " + i);
                }
            }
        }, threadName);
    }

    // 确保公平锁的释放，使其他线程也能得到执行，不会陷入线程饥饿
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    private Thread getThreadWithReentrantLock(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                reentrantLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " output " + i);
                } finally {

                    reentrantLock.unlock();
                }
            }
        }, threadName);
    }
}

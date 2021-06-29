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

    private Object lock = new Object();
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    /**
     * MIN_PRIORITY = 1; MAX_PRIORITY = 10
     * Priority优先级只是给OS一个执行的建议，不是准确的执行顺序, 实际执行由JVM控制
     * 每次执行，最终的结果调度都不一致(一个线程可能执行一段的输出，然后中断)，与设置的优先级没有必然的联系
     */
    private void testThreadStarvation() {
        Thread thread1 = getThread("Thread 1");
        Thread thread2 = getThread("Thread 2");
        Thread thread3 = getThread("Thread 3");
        Thread thread4 = getThread("Thread 4");
        Thread thread5 = getThread("Thread 5");

        thread1.setPriority(10);
        thread2.setPriority(8);
        thread3.setPriority(6);
        thread4.setPriority(4);
        thread5.setPriority(2);

        thread1.start(); // 大概率会先开始执行
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    /**
     * 1. 所有的线程使用同一个锁，只有最终获得lock的线程能得到执行，输出
     * 2. 所有线程之间没有数据的Interference干扰，没有共享的变量
     */
    // 使用synchronized机制的3个理由：
    // 线程执行非常快，基本不会出现线程饥饿问题，线程饥饿所带来的影响低于使用fair lock所带来的性能影响 !!
    private Thread getThread(String threadName) {
        return new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " output " + i);
                }
            }
        }, threadName);
    }

    /**
     * 1. 使用try-finally机制确保一定得到释放
     * 2. 确保每个线程都能得到一次输出, 不会长时间等待
     */
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

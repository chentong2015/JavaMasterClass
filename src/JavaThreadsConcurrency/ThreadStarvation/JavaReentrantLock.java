package JavaThreadsConcurrency.ThreadStarvation;

import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock: 可重入锁，公平锁
// 1. 可以作为公平锁使用，解决线程饥饿问题：释放锁时，任何一个等待锁的线程都有机会获得锁
// 2. 正在等待reentrantLock锁的线程可以选择放弃等待
// 3. 公平锁会耗费额外的layer of processing去管理和确保公平的获得锁，对性能影响较大，减低吞吐量
// 4. 必须确保在finally语句快中释放掉锁
public class JavaReentrantLock {

    private ReentrantLock reentrantLock = new ReentrantLock(true);

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

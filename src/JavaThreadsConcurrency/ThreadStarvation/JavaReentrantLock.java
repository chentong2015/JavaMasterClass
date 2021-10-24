package JavaThreadsConcurrency.ThreadStarvation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock implements Lock
// 1. 正在等待reentrantLock锁的线程可以选择放弃等待
// 2. 公平锁: 作为公平锁使用，解决线程饥饿问题，释放锁时，任何一个等待锁的线程都有机会获得锁
// 3. 可重入锁: 获得到同步锁之后，可以再继续执行需要该同步锁的代码块
public class JavaReentrantLock {

    // 公平锁会耗费额外的layer of processing去管理和确保公平的获得锁，对性能影响较大，减低吞吐量
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    // 1. 使用tryLock()尝试获取lock，设置timeout时间避免不必要尝试
    // 2. 使用try-finally语句块，确保一定会释放，且只释放一次
    // 3. 使用getQueueLength()判断在队列中等待的线程数目
    private void testReentrantLock() throws InterruptedException {
        int numThreadsWaiting = reentrantLock.getQueueLength();
        if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("Get Lock");
        }
        reentrantLock.lock();
        try {
            System.out.println("do something");
        } finally {
            reentrantLock.unlock();
        }
    }

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

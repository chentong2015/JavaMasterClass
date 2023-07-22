package java_thread_lock.JavaLock.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock implements Lock ==> 性能优于jdk1.6之前的synchronized，和jdk1.7之后的版本性能差不多
// 1. 正在等待reentrantLock锁的线程可以选择放弃等待
// 2. 公平锁: 作为公平锁使用，解决线程饥饿问题，释放锁时，任何一个等待锁的线程都有机会获得锁
// 3. 可重入锁: 获得到同步锁之后，可以再继续执行需要该同步锁的代码块
public class JavaReentrantLock {

    // 公平锁会耗费额外的layer of processing去管理和确保公平的获得锁，对性能影响较大，减低吞吐量
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    private void testReentrantLock() throws InterruptedException {
        // 获取在AQS队列中等待的线程数目
        int numThreadsWaiting = reentrantLock.getQueueLength();
        // 尝试获取lock，设置timeout时间避免不必要尝试
        if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("Get Lock");
        }
        // reentrantLock.lock(); 该方法能够正常返回，则表示加锁成功
        reentrantLock.lock();
        try {
            System.out.println("do something");
        } finally {
            // 使用try-finally语句块，确保一定会释放，且只释放一次
            reentrantLock.unlock();
        }
    }

    // TODO: 如何让AQS队列中等待的线程被打断，响应打断
    // thread1.start();
    // thread1.interrupt(); 在用户侧打断指定的线程
    private void testThreadInterrupted() {
        try {
            // 判断在加锁之前是否被打断了，捕获到异常
            // 源码的实现和reentrantLock.lock();的是否公用一个兼容的方法
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("指定的线程收到了被打断的请求");
            e.printStackTrace();
        }
    }
}

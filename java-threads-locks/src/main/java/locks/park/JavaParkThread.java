package locks.park;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

// Park能够让线程立马睡眠，等待被叫醒(无延迟)
// 等效于当前线程处于Disable的状态，禁用当前线程以进行线程调度
//   LockSupport.park(); U.park(false, 0L); 由Unsafe类提供cpp代码
//   LockSupport.unpark(thread); 指定要唤醒的线程
public class JavaParkThread {

    private void testPark() {
        System.out.println("Start sleeping...");
        LockSupport.park();
        System.out.println("End Sleeping");
    }

    // 使用Park + 自旋自定义实现一把锁
    private volatile int lockStatus = 0;
    private Queue<Thread> threadQueue = new ArrayDeque<>();

    public void lock() {
        // while (!compareAndSet(0,1)) {
        park();
        // }
    }

    private void park() {
        // threadQueue.add(currentThread); 将当前被等待的线程添加到队列中
        // releaseCpu(); 释放CPU
    }

    public void unlock() {
        lockStatus = 0;
        Thread thread = threadQueue.poll(); // 从队列中出一个线程
        unpark(thread);
    }

    private void unpark(Thread thread) {
        // 唤醒指定的线程
    }
}

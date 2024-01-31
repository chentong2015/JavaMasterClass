package locks.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock Condition 条件变量:
// 1. 使用Condition来关联一个锁上的多个条件，实现在多个Condition中共享一个锁
// 2. 使用Condition可以替代对Object monitor methods方法的调用
//    Condition中await()方法类似于Object类中的wait()方法
//    Condition中await(long time,TimeUnit unit)方法类似于Object类中的wait(long time)方法
//    Condition中signal()方法类似于Object类中的notify()方法
//    Condition中signalAll()方法类似于Object类中的notifyAll()方法

// ReentrantLock Condition 条件变量的标准设计:
// If a take is attempted on an empty buffer, then the thread will block until an item becomes available
// if a put is attempted on a full buffer, then the thread will block until a space becomes available
// We would like to keep waiting "put threads" and "take threads" in separate "wait-sets"
// use the optimization of only notifying a single thread at a time when items or spaces become available in the buffer
// This can be achieved using two Condition instances
public class ReentrantLockCondition<E> {

    // TODO: Java源码典型阻塞(有界)队列: ArrayBlockingQueue, LinkedBlockingDeque

    int count;
    int putIndex;
    int takeIndex;
    final Object[] items = new Object[100];
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    // 1. 添加时，首先需要拿到ReentrantLock
    // 2. 如果数组已满，则.await()处于等待，并释放掉拿到的锁，然后while自旋(阻塞)
    // 3. 当数组被通知notFull.signal()非满时，则在这个条件上等待的一个线程被唤醒，然后执行添加
    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            // Causes the current thread to wait until it is signalled or interrupted
            // The lock associated with this Condition is atomically released
            // The current thread becomes disabled for thread scheduling purposes and lies dormant until one of four things happens
            while (count == items.length)
                notFull.await();
            count++;
            items[putIndex++] = x;
            if (putIndex == items.length) putIndex = 0;
            // If any threads are waiting on this condition then one is selected for waking up
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 1. 取值时，首先需要拿到ReentrantLock
    // 2. 如果数组为空，则.await()处于等待，并释放掉拿到的锁，然后while自旋(阻塞)
    // 3. 当数组被通知notEmpty.signal()非空时，则在这个条件上等待的一个线程被唤醒，然后执行添加
    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            count--;
            E x = (E) items[takeIndex++]; // 可能造成强制转换问题
            if (takeIndex == items.length) takeIndex = 0;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

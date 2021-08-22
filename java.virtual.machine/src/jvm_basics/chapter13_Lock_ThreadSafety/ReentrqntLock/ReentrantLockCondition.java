package jvm_basics.chapter13_Lock_ThreadSafety.ReentrqntLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock Condition: 锁绑定多个条件
// 通过多次调用.newCondition()，实现一个锁和多个条件相关联
// Condition replaces the use of the Object monitor methods 可以实现对象监视器的调用(实现一个wait, notify, notifyAll的条件效果)

// If a take is attempted on an empty buffer, then the thread will block until an item becomes available
// if a put is attempted on a full buffer, then the thread will block until a space becomes available
// We would like to keep waiting "put threads" and "take threads" in separate "wait-sets" 将两个操作的等待放置在不同的等待集中
// we can use the optimization of only notifying a single thread at a time when items or spaces become available in the buffer
// This can be achieved using two Condition instances  可以根据不同的条件通知指定等待的线程
public class ReentrantLockCondition<E> {

    int count;
    int putIndex;
    int takeIndex;
    final Object[] items = new Object[100];

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            // Causes the current thread to wait until it is signalled or interrupted
            // The lock associated with this Condition is atomically released and the current thread becomes disabled
            // for thread scheduling purposes and lies dormant until one of four things happens
            while (count == items.length)
                notFull.await(); // 关联到这个Condition上面的lock锁会被自动释放掉

            count++;
            items[putIndex++] = x;
            if (putIndex == items.length) putIndex = 0;

            // signal()唤醒一个等待在这个条件上的thread线程
            // If any threads are waiting on this condition then one is selected for waking up
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)    // 非空则说明能取，且takeIndex指示的是正确的位置
                notEmpty.await();
            count--;
            E x = (E) items[takeIndex++];  // 将Object元素类型强制转换成E泛型，可能会出现cast转换问题 !!
            if (takeIndex == items.length) takeIndex = 0;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

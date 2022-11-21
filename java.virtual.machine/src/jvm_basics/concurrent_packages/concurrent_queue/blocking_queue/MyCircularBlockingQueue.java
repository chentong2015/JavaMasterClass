package jvm_basics.concurrent_packages.concurrent_queue.blocking_queue;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 自定义循环有界阻塞队列
public class MyCircularBlockingQueue<E> implements BlockingQueue<E> {

    // 事件存储数据的(由数组实现的)双端队列
    private final ArrayDeque<E> queue;
    // 保证队列队列线程安全的锁
    private final ReentrantLock lock;
    private final Condition notEmpty;

    private final int maxSize;
    // 记录队列中废弃掉的item
    private long countDroppedItems;

    public MyCircularBlockingQueue(int queueSize) {
        this.queue = new ArrayDeque<>(queueSize);
        this.maxSize = queueSize;
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.countDroppedItems = 0L;
    }

    // It will remove the oldest queued element (the element at the front of the
    // queue) in order to make room for any new elements if the queue is full.
    @Override
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 没有使用notFull这个condition，是因为如果出现队列满的情况下，会自动删除一个，然后入队
            if (this.queue.size() == this.maxSize) {
                // 相当于removeFirst()移除队列中的第一个，历史最早的结点
                final E discard = this.queue.remove();
                this.countDroppedItems++;
            }
            this.queue.add(e);
            this.notEmpty.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (this.queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            }
            return this.queue.poll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (this.queue.isEmpty()) {
                this.notEmpty.await();
            }
            return this.queue.poll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return this.queue.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return this.queue.size();
        } finally {
            lock.unlock();
        }
    }

    // 返回被废弃的结点数目
    public long getDroppedCount() {
        return this.countDroppedItems;
    }

    // 判断在这个锁关联的condition条件上面是否有线程在等待(阻塞)
    public boolean isConsumerThreadBlocked() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return lock.getWaitQueueLength(this.notEmpty) > 0;
        } finally {
            lock.unlock();
        }
    }


    @Override
    public int drainTo(Collection<? super E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(E e) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remainingCapacity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
}

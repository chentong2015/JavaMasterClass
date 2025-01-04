package concurrent_api.queue.circular_blocking_queue;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 自定义循环有界阻塞队列
public class MyCircularBlockingQueue<E> implements BlockingQueue<E> {

    // 事件存储数据的(由数组实现的)双端队列
    private final ArrayDeque<E> queue;

    // 保证队列队列线程安全的锁
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = this.lock.newCondition();

    private final int maxSize;

    public MyCircularBlockingQueue(int queueSize) {
        this.queue = new ArrayDeque<>(queueSize);
        this.maxSize = queueSize;
    }

    // It will remove the oldest queued element (the front of the queue)
    // in order to make room for any new elements if the queue is full.
    @Override
    public boolean offer(E e) {
        this.lock.lock();
        try {
            // 没有使用notFull这个condition，
            // 是因为如果出现队列满的情况下，会自动删除一个，然后入队
            if (this.queue.size() == this.maxSize) {
                // 相当于removeFirst()移除队列中的第一个，最早插入队列的元素
                final E discard = this.queue.remove();
            }
            this.queue.add(e);

            // 开发Lock上满足的Condition条件，以便别的线程能够读取
            this.notEmpty.signal();
        } finally {
            this.lock.unlock();
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
                // 等待指定的时间，直到满足Condition条件
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
            // 在获取数据时必须满足Lock上指定的条件
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

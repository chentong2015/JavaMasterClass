package concurrent_api.queue;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

// TODO. 自定义阻塞队列控制并发执行的线程Task
public class MyPriorityBlockingQueue {

    // An unbounded blocking queue that uses the same ordering rules as class PriorityQueue
    // While queue is logically unbounded, attempted additions may fail due to resource exhaustion (OutOfMemoryError).
    // This class does not permit null elements
    private final BlockingQueue<String> blockingQueue;
    private final Semaphore semaphore;
    private final Object lock = new Object();

    // 在线程之间同步更新最新数据
    private volatile int count = 0;

    // TODO. 对于队列中自定义的对象，优先队列必须提供队列元素的Comparator<T>比较器
    public MyPriorityBlockingQueue() {
        this.blockingQueue = new PriorityBlockingQueue<>(10);
        this.semaphore = new Semaphore(10);
    }

    public void expect() throws InterruptedException {
        // Acquires a permit from this semaphore,
        // blocking until one is available, or the thread is interrupted.
        semaphore.acquire();

        // Don't acquire the lock in a synchronized block - might deadlock
        synchronized (lock) {
            count++;
        }
    }

    public boolean isEmpty() {
        return this.blockingQueue.isEmpty();
    }

    public boolean isExpecting() {
        return this.count > 0;
    }

    // TODO. 往阻塞队列中添加新的元素，前提能够获取信号量
    // 添加完成后释放信号量给其他线程，并通过lock来通知所有线程
    public void put(String value) {
        if (!isExpecting()) {
            throw new IllegalArgumentException("Call expect() before put().");
        }
        this.blockingQueue.add(value);
        this.semaphore.release();
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    // TODO. 从阻塞队列中获取元素 ?
    // 如果count数量大于阻塞队列的大小，则循环等待
    public String take() throws InterruptedException {
        if (!isExpecting()) {
            throw new NoSuchElementException("Call expect() before take().");
        }
        String value;
        synchronized (lock) {
            // Causes the current thread to wait until it is awakened,
            // typically by being notified or interrupted.
            while (count > this.blockingQueue.size()) {
                lock.wait();
            }

            value = this.blockingQueue.take();
            this.count--;
        }
        return value;
    }
}

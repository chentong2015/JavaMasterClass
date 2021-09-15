package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_queue.blocking_queue;

import jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_queue.blocking_queue.model.MyDelayTask;

import java.util.concurrent.DelayQueue;

// DelayQueue延迟队列：特化版的PriorityBlockingQueue，线程安全的优先阻塞队列  ==> ScheduledThreadPoolExecutor
// TODO: 定时任务调度器的实现: 把"计算时间差"并让消费者"等待该时间差"的功能集成进了队列
public class JavaDelayQueue {

    public void testDelayQueue() throws InterruptedException {
        DelayQueue<MyDelayTask> queue = new DelayQueue<>();
        queue.add(new MyDelayTask());
        queue.put(new MyDelayTask());
        queue.offer(new MyDelayTask());
        MyDelayTask task = queue.take();
    }

    // private final transient ReentrantLock lock = new ReentrantLock();
    // private final Condition available = lock.newCondition();
    // private final PriorityQueue<E> q = new PriorityQueue<E>(); 内部使用优先队列, 插入和删除的时间复杂度都是log(n)
    // private Thread leader; 使用Leader/Follower模式, 每个消费者线程只需要等待所需要的时间差，因此响应速度更快
    // TODO: 线程leader的作用 ? 相当于将指定的消费者选择出来成为leader，以此来起到"阻塞"其他消费者的效果

    // public boolean offer(E e) {
    //     final ReentrantLock lock = this.lock;
    //     lock.lock();
    //     try {
    //         q.offer(e);
    //         if (q.peek() == e) {    如果第一个元素就是刚入队列的元素，则说明刚才的队列是空的
    //             leader = null;      将leader重置为null，这些消费者之间互相竞争，自然有一个会被选为leader
    //             available.signal(); 消费者中被选成leader的线程才能成功从阻塞队列中出队
    //         }
    //         return true;
    //     } finally {
    //         lock.unlock();
    //     }
    // }

    //  public E take() throws InterruptedException {
    //    final ReentrantLock lock = this.lock;
    //    lock.lockInterruptibly();
    //    try {
    //        for (;;) {
    //            E first = q.peek(); 取队列中延迟最小的第一个任务来出队
    //            if (first == null)
    //                available.await();
    //            else {
    //                long delay = first.getDelay(NANOSECONDS);
    //                if (delay <= 0L)
    //                    return q.poll(); 如果剩余的延迟时间小于0，则获取成功，直接出队
    //                first = null; TODO: 避免peek()到的对象，由于已经被别的线程出队，在本线程无限等待的过程中无法释放，造成OOM
    //                if (leader != null)
    //                    如果leader不为空说明已经有线程在取了，让当前消费者无限等待
    //                    available.await();
    //                else {
    //                    如果为空说明没有其他消费者去取任务，设置leader为当前消费者，并让改消费者等待指定的时间
    //                    Thread thisThread = Thread.currentThread();
    //                    leader = thisThread;
    //                    try {
    //                        线程等待指定时间后被唤醒，获取对象成功，出队
    //                        TODO: 只需要等待需要等待的时间差
    //                        available.awaitNanos(delay);
    //                    } finally {
    //                        如果leader是当前线程，则将leader置空，让别的消费者能够去取任务
    //                        if (leader == thisThread)
    //                            leader = null;
    //                    }
    //                }
    //            }
    //        }
    //    } finally {
    //        如果leader为空(没有消费者取任务)，同时延迟队列中又有任务，则唤醒在阻塞的消费者.signal()
    //        if (leader == null && q.peek() != null)
    //            available.signal();
    //        lock.unlock();
    //    }
    //  }
}

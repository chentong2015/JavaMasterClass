package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_queue.blocking_queue;

import jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_queue.blocking_queue.model.MyDelayTask;

import java.util.concurrent.DelayQueue;

// 延迟队列：定时任务调度器的实现
public class JavaDelayQueue {

    public void testDelayQueue() throws InterruptedException {
        DelayQueue<MyDelayTask> queue = new DelayQueue<>();
        queue.add(new MyDelayTask());
        queue.put(new MyDelayTask());
        queue.offer(new MyDelayTask());
        MyDelayTask task = queue.take();
    }

    // 基本类型成员，使用ReentrantLock来实现线程安全
    // private final transient ReentrantLock lock = new ReentrantLock();
    // private final Condition available = lock.newCondition();
    // private final PriorityQueue<E> q = new PriorityQueue<E>();
    // private Thread leader;

    //
    // public boolean offer(E e) {
    //     final ReentrantLock lock = this.lock;
    //     lock.lock();
    //     try {
    //         q.offer(e);
    //         if (q.peek() == e) {
    //             leader = null;
    //             available.signal();
    //         }
    //         return true;
    //     } finally {
    //         lock.unlock();
    //     }
    // }

}

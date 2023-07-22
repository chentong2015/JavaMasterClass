package java_concurrent_packages.concurrent_queue.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 阻塞队列 TODO: 基于加锁来保证线程安全
//   1. 应用在多线程场景，只有一个线程能够执行入队或出队，其余被阻塞
//   2. 阻塞队列如果存满, 只能进行出队列，所有入队列都会阻塞
//   3. 阻塞队列如果为空, 只能进行入队列，所有出队列都会阻塞
// 使用场景 TODO: 生产者和消费者的场景，生产者是向队列里添加元素的线程，消费者是从队列里取元素的线程

// 1. 有界阻塞队列
//    ArrayBlockingQueue    基于数组: 生产者和消费者模式，系统设计
//    LinkedBlockingQueue   单向链表结构
//    LinkedBlockingDeque   双端列表结构
// 2. 无界阻塞队列
//    SynchronousQueue      没有缓冲
//    LinkedTransferQueue   链表
//    PriorityBlockingQueue 优先级排序: 插入和删除的时间复杂度都是log n
//    DelayQueue            延迟阻塞队列
public class JavaBlockingQueue {

    // TODO: ArrayBlockingQueue底层实现
    // 1. 底层基于数组来存储，FIFO，循环使用数组位置进行添加
    // 2. ReentrantLock，入队出队用的是同一把锁，有公平和非公平两种方式
    //    并基于这把锁构建了两个condition，一个是notFull，一个是notEmpty
    // 3. put的时候，如果队列已满，阻塞在notFull条件队列上，如果添加成功，通知阻塞在notEmpty队列的线程
    // 4. take的时候，如果队列是空，阻塞在notEmpty条件队列上，如果拿取元素成功，通知阻塞在notFull对列的线程
    private void testArrayBlockingQueue() {
        final Object[] items; // 存放元素的数组
        int putIndex; // 存元素的下标索引
        int takeIndex; // 取元素的下标索引
        int count; // 队列中元素的数量

        // 控制并发访问的锁
        final ReentrantLock lock;
        // notEmpty条件对象，用于通知take方法队列已有元素，可执行获取操作
        final Condition notEmpty;
        // notFull条件对象，用于通知put方法队列未满，可执行添加操作
        final Condition notFull;

        // 自定义设置是否使用公平锁
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10, true);
    }

    // TODO: LinkedBlockingQueue底层实现
    // 1. 基于链表来存储数据，结点为单向结点Node<E>
    // 2. LinkedBlockingQueue是两把锁(put锁和take锁)，分别用来控制入队和出队
    // 3. LinkedBlockingQueue的count值(队列长度)是用AtomicInteger来修饰的，需要保证原子性
    // 4. LinkedBlockingQueue建议用有界的方式，如果用无界的方式来定义，在生产能力持续大于消费能力的时候，会无限的消耗内存
    public void testLinkedBlockingQueue() {
        final ReentrantLock takeLock = new ReentrantLock();
        final Condition notEmpty = takeLock.newCondition();
        final ReentrantLock putLock = new ReentrantLock();
        final Condition notFull = putLock.newCondition();

        // 无参默认会使用Integer.MAX_VALUE容量
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.add("item");
        String value = queue.poll();
    }

    // TODO: LinkedBlockingDeque底层实现
    // 1. 基于双向链表来存储数据，链表结点为双向Node<E>
    // 2. LinkedBlockingDeque推荐使用有界的队列，避免过度消耗内存
    // 3. 对队列的读写操作都会.lock()加锁和释放锁.unlock()
    public void testLinkedBlockingDeque() throws InterruptedException {
        // 使用ReentrantLock来保证线程安全，但是只能使用非公平锁
        ReentrantLock lock = new ReentrantLock();
        final Condition notEmpty = lock.newCondition();
        final Condition notFull = lock.newCondition();

        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>(10);
        queue.offer("item");
        queue.poll();

        // 在指定的timeout时间段中获取，反之返回null
        queue.poll(1, TimeUnit.MINUTES);
    }
}

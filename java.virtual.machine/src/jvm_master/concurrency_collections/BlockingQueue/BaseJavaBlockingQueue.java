package jvm_master.concurrency_collections.BlockingQueue;

// Java BlockingQueue:
// 1. 线程阻塞，线程安全，多线程场景，只有一个线程能够执行入队或出队
// 2. 阻塞队列如果存满, 只能进行出队列，所有入队列都会阻塞
// 3. 阻塞队列如果为空, 只能进行入队列，所有出队列都会阻塞

// SynchronousQueue
// LinkedTransferQueue
// ArrayBlockingQueue    数值组成，有界队列
// LinkedBlockingQueue   链表结构，有界阻塞
// PriorityBlockingQueue 优先级排序，无界阻塞
// DelayQueue            优先级队列，无界阻塞: 1. 缓存系统的设计 2. 定时任务调度器
public class BaseJavaBlockingQueue {

}

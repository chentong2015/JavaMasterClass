package java_locks.synchronize;

// synchronized <jdk1.6 实现锁是总重量级锁，"调用OS的函数": 状态的切换
// synchronized >jdk1.7 让线程同步在JVM级别解决，锁优化: 偏向锁，轻量级锁...
// synchronized 没有源码，由C++实现
//
// synchronized属于什么类型的锁 ?
// 1. 互斥锁: 始终会阻塞线程
// 2. 重量级锁: 比较消耗系统资源 ==> 线程阻塞, 上下文切换, 操作系统线程调度(用户态/内核态)
// 3. 同步锁：只有一个线程能够拿到锁，"同步"执行
// 4. 悲观锁 始终考虑会有线程竞争，始终加锁
// 5. 非公平锁: 不能有效的解决线程饥饿的问题 ==> 相当于ReentrantLock(false)
public class JavaSynchronized {

    // 共享数据区域
    private int num = 0;

    // 定义私有锁来实现同步, final可以放置被修改后导致的不同步
    private final Object lock = new Object();

    // synchronized 锁的是当前调用的这个方法的对象
    public synchronized void increase() {
        // synchronized (this) {  等效于给block加锁
        //     num++;
        // }
        // synchronized (lock) {
        //     num++;
        // }
        num++;
    }

    // 使用synchronized锁定Class类型
    private static int number = 0;

    public static synchronized void increase2() {
        // synchronized (DemoLockClass.class) {
        //     number++;
        // }
        number++;
    }
}

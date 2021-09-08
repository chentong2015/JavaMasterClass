package jvm_basics.chapter13_Lock_ThreadSafety.JavaLock.BaseLock;

// TODO: synchronized (互斥锁，重量级锁，同步锁，悲观锁) ===> >Java 6 已经优化得不错
// 1. 互斥锁  : 始终会阻塞线程
// 2. 重量级锁: 比较消耗系统资源 ==> 线程阻塞, 上下文切换, 操作系统线程调度(用户态/内核态)
// 3. 同步锁  ：只有一个线程能够拿到锁，"同步"执行
// 4. 悲观锁  : 始终考虑会有线程竞争，始终加锁
public class JavaSynchronized {

    private int num = 0;

    // synchronized 锁的是当前调用的这个方法的对象
    // 底层是给monitor对象加锁, 对于没有加锁成功的, 在队列中等待 (<java 6)
    // 底层C++使用ObjectMonitor类型，拥有两个等待的队列
    // 3 monitorenter ---> JVM执行到此处，会调用底层C++代码的实现 InterpreterRuntime::monitorenter(..)
    //                     方法中会调用锁升级的方法
    // 4 ............
    // 10 monitorexit ---> 通过底层代码的实现来释放掉锁
    public synchronized void increase() {
        // synchronized (this) {  等效于给block加锁
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

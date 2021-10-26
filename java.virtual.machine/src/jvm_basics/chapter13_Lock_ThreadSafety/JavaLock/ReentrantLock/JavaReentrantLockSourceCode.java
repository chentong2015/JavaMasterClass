package jvm_basics.chapter13_Lock_ThreadSafety.JavaLock.ReentrantLock;

// TODO: Reentrant中AQS队列(AbstractQueuedSynchronizer)所用到的技术
// 自旋 + park/unpark + CAS

// 谈谈对Reentrant的理解:
// 如果是单个线程，或者线程交替执行，则和队列无关，直接在JDK级别解决同步问题(和OS没有关系，性能非常快)
public class JavaReentrantLockSourceCode {

    // 使用CAS自定义实现一把锁
    // private volatile int lockStatus = 0;
    // public void lock() {
    //   自旋操作，消耗CPU，占用资源
    //   while(!compareAndSet(0,1)) {
    //      1. 如果拿不到锁，则使线程让出CPU，该方法被CPU调度，没有办法控制线程的执行顺序
    //         yield();
    //      2. sleep时间不确定
    //         sleep();
    //      3. park + 自旋
    //   }
    // }
    // public void unlock() {
    //   lockStatus = 0
    // }

    // class UnFairSync extends Sync
    final boolean initialTryLockUnFair() {
        Thread current = Thread.currentThread();
        // TODO: 不需要判断state状态，直接CAS ==> 非公平锁的实现
        // if (compareAndSetState(0, 1)) { // first attempt is unguarded
        //     setExclusiveOwnerThread(current);
        //     return true;
        // } else if (getExclusiveOwnerThread() == current) {
        //     int c = getState() + 1;
        //     if (c < 0) // overflow
        //         throw new Error("Maximum lock count exceeded");
        //     setState(c);
        //     return true;
        // } else
        //     return false;
        return false;
    }

    // class FairSync extends Sync
    final boolean initialTryLockFair() {
        Thread current = Thread.currentThread();
        // int c = getState(); 这个取值是原子操作
        // if (c == 0) { 判断int state;是否为自由状态
        //     TODO: 判断排队的线程队列，优先中队列中取线程 ==> 公平锁的实现，解决线程饥饿问题
        //           判断自己需不需要排队，再进行CAS
        //     if (!hasQueuedThreads() && compareAndSetState(0, 1)) {
        //         // 把当前线程设置到AbstractQueuedSynchronizer属性exclusiveOwnerThread;值
        //         setExclusiveOwnerThread(current); // current 当前持有锁的线程
        //         return true;
        //     }
        // TODO: 拿到当前持有锁的线程 ==> 可重入锁的设计
        // } else if (getExclusiveOwnerThread() == current) {
        //     重入之后state会增加
        //     if (++c < 0) // overflow
        //         throw new Error("Maximum lock count exceeded");
        //     setState(c);
        //     return true;
        // }
        return false;
    }
}

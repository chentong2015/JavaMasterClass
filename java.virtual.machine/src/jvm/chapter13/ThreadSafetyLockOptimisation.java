package jvm.chapter13;

public class ThreadSafetyLockOptimisation {

    // Java中线程安全是如何体现的 ? 那些操作是线程安全的 ?
    // 1. 不变类型
    // 2. 绝对线程安全：Vector<T>标注的线程安全的类型，不一定绝对的线程安全(不需要额外的同步措施) !!
    // 3. 相对线程安全
    // 4. 线程兼容
    // 5. 线程对立: Java需要支持多线程的特性

    // Java中线程安全的实现方法 ?
    // 1. 互斥同步 :  Critical Section, Mutex, Semaphore "悲观并发策略"，虚拟机会优化掉大部份不必要的加锁
    // 2. 非阻塞同步： Lock-Free无锁编程, CAS操作            "乐观并发策略"，发生冲突之后，在进行补偿
    // 3. 无同步方案:  Pure Code纯代码                     "可重入代码"，方法的返回结果可以预测

    // Java锁优化技术
    // 1. 自旋锁和自适应自旋：让线程自旋等待获得锁，避免"线程切换"的开销，但是需要持续占用处理器时间
    // 2. 锁消除
    // 3. 锁粗化
    // 4. 轻量级锁
    // 5. 偏向锁
}
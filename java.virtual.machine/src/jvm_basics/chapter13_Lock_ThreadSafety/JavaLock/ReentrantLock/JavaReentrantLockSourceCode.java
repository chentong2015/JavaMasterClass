package jvm_basics.chapter13_Lock_ThreadSafety.JavaLock.ReentrantLock;

public class JavaReentrantLockSourceCode {

    // 使用CAS自定义实现一把锁
    // private volatile int lockStatus = 0;
    // public void lock() {
    //   自旋操作，消耗CPU，占用资源
    //   while(!compareAndSet(0,1)) {
    //      TODO: 优化机制，如果拿不到锁，则使线程让出CPU
    //      该方法是属于CPU调度的
    //      yield();
    //   }
    // }
    // public void unlock() {
    //   lockStatus = 0
    // }
    

}

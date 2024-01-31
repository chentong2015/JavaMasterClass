package locks.cas;

// 使用CAS自定义实现一把锁
// CAS算法: 将一个预期值和内存值进行比较，如果相等则更换新的值，反之自旋
public class JavaCASImpl {

    private volatile int lockStatus = 0;

    public void lock() {
        // 自旋操作，消耗CPU，占用资源
        // while(!compareAndSet(0,1)) {
        //    1. 如果拿不到锁，则使线程让出CPU，该方法被CPU调度，没有办法控制线程的执行顺序
        //       yield();
        //    2. sleep时间不确定
        //       sleep();
        //    3. park + 自旋
        // }
    }

    public void unlock() {
        lockStatus = 0;
    }
}

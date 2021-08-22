package jvm_basics.chapter13_Lock_ThreadSafety.JavaLockOptimization;

// 通过给synchronized锁添加不同的状态来实现优化
public class LockOptimization {
    
    // Java锁优化技术
    // 1. 自旋锁和自适应自旋：让线程自旋等待获得锁，避免"线程切换"的开销，但是需要持续占用处理器时间
    // 2. 锁消除
    // 3. 锁粗化
    // 4. 轻量级锁
    // 5. 偏向锁

}

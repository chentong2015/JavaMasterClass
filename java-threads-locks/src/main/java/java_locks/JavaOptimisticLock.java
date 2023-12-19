package java_locks;

// OCC(Optimistic Concurrency Control): 乐观锁
// 通常用在数据争用不大，冲突较少的环境中，回滚的成本低于锁定数据的成本
public class JavaOptimisticLock {

    // 假设多用户"并发的事务"在处理时不会彼此相互影响，各事务能够在不产生锁的情况下处理各自的那部分数据
    // 在提交数据之前，每个事务会先检查在该事务读取数据之后(拿来操作的这部分时间内)，有没有其他的事务又修改了那部分数据
    // 如果其他的事务有更新的话，正在提交的事务会进行回滚
    public int maximum(int target, int value) {
        int startValue;
        int desiredValue;
        int currentValue = target;
        do {
            startValue = currentValue;
            desiredValue = Math.max(startValue, value);
            // 线程在这里可能会被抢占，以下代码不是原子性
            if (startValue == target) target = desiredValue;

            // 返回target在被方法修改之前的值，操作是原子性的
            // 确保在没有其他线程更改target的前提下更改为desiredValue
            // currentValue = Interlocked.CompareExchange(target,desiredValue, startValue);
        } while (startValue != currentValue);
        return desiredValue;
    }
}

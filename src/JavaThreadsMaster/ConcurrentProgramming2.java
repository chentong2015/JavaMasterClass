package JavaThreadsMaster;

public class ConcurrentProgramming2 {

    // TODO: OCC(Optimistic Concurrency Control): 乐观并发控制(乐观锁)
    //       通常用在数据争用不大，冲突较少的环境中，回滚的成本低于锁定数据的成本
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

    // TODO: CAS (Compare and Swap)比较交换: 类似数据库中的乐观锁定，比较判断的逻辑是一致的，会产生判断和回滚 !!
    private int variableBeingSet;

    // 使用CAS操作更新数据的方法，查看是否修改并持续尝试
    // while() 直到满足一致性时，才在被设置的值上面做修改
    public void simulateNonBlockingSet(int newValue) {
        int currentValue;
        do {
            currentValue = variableBeingSet;
        } while (currentValue != compareAndSwap(currentValue, newValue));
    }

    // 某方法试图更新一个共享变量时，CAS操作就会验证要赋值的变量variableBeingSet是否保有上一次的已知值currentValue
    // 如果是则更改，反之说明另外一个线程正在试图更新变量值
    private synchronized int compareAndSwap(int currentValue, int newValue) {
        if (variableBeingSet == currentValue) {
            variableBeingSet = newValue;
            return currentValue;
        }
        return variableBeingSet;
    }
}

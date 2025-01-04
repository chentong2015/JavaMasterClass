package concurrent_tools.mutex;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

public class CustomSynchronizer extends AbstractQueuedSynchronizer {

    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    // 1. 使用CAS的方式获取锁，当状态为0的时候获取锁，获取成功则设置成1
    // 2. 设置独占所有者线程为当前线程本身
    public boolean tryAcquire(int acquires) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    // 释放锁,将状态设置为0
    protected boolean tryRelease(int releases) {
        if (getState() == 0) {
            throw new IllegalMonitorStateException();
        }
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    // 返回一个Condition, 每个condition都包含condition队列
    Condition newCondition() {
        return new ConditionObject();
    }
}

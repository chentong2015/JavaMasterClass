package concurrent_apis.tools.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 使用自定义的同步器来实现Mutex的效果
public class CustomMutex implements Lock {

    private final CustomSynchronizer sync = new CustomSynchronizer();

    // 阻塞方法: 只有拿到了同步锁方法才会返回
    @Override
    public void lock() {
        System.out.println("Is it being held ?" + sync.isHeldExclusively());
        sync.acquire(1);
        System.out.println("Get lock successfully");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}

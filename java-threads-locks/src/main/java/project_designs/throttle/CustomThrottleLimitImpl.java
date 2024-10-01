package project_designs.throttle;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

// 设计一个限流的Throttle阀门，控制指定数量的Task并发运行
// - 使用阻塞队列存储要执行的所有Task
// - put() 控制入队列操作
// - runTake() 执行队列中的任务
//
// TODO. 如何模拟最多只有throttleLimit个线程在并发执行 ?
public class CustomThrottleLimitImpl {

    private final Object lock = new Object();
    private final int throttleLimit;

    // 信号量用于控制最大并发执行的线程数量
    private final Semaphore semaphore = new Semaphore(0);
    private final BlockingQueue<CustomTaskRunnable> runnableQueue;

    // TODO. 优先级队列必须提供比较器TaskComparator
    public CustomThrottleLimitImpl(int throttleLimit) {
        this.runnableQueue = new PriorityBlockingQueue<>(throttleLimit, new TaskComparator());
        this.throttleLimit = throttleLimit;
    }

    // 保证添加的TaskRunnable能够释放信号量
    public void put(CustomTaskRunnable taskRunnable) throws InterruptedException {
        synchronized (lock) {
            if (getPermitNum() >= this.throttleLimit) {
                // TODO. 如果释放的信号量过大，则需要等待Task被消费后Notify通知
                lock.wait();
            }
            this.runnableQueue.add(taskRunnable);
            this.semaphore.release();
        }
    }

    // take()在获取Task时如果队列为空，则阻塞线程
    // task任务的执行是异步的方式
    public void runTask() throws InterruptedException {
        this.semaphore.acquire();
        System.out.println("Get semaphore!");
        this.runnableQueue.take().run();
        System.out.println("Run Task finish !");

        // 通知所有添加的taskRunnable的线程，以便执行信号量的释放
        lock.notifyAll();
    }

    public int getPermitNum() {
        return this.semaphore.availablePermits();
    }

    private static class TaskComparator implements Comparator<CustomTaskRunnable> {

        @Override
        public int compare(CustomTaskRunnable o1, CustomTaskRunnable o2) {
            return 0;
        }
    }
}

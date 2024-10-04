package project_design.throttle_limit;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

// 项目场景:
// 设计一个限流的Throttle阀门，控制指定数量线程Task并发运行
// 根据Task任务的Priority优先级，执行顺序具有一定范围内的排序
// 不同Task任务执行的时间不同，先执行完的任务会释放下一个等待执行的任务
public class CustomThrottleLimitImpl {

    // Lock锁: 在添加Task之后实现一个Notify通知的效果
    private final Object lock = new Object();

    // 信号量: 设置初始值(一开始允许多少并发)，只允许指定数量的Task并发运行
    private final Semaphore semaphore;
    private final BlockingQueue<CustomTaskRunnable> runnableQueue;

    public CustomThrottleLimitImpl(int throttleLimit) {
        this.runnableQueue = new PriorityBlockingQueue<>(throttleLimit, new TaskComparator());
        this.semaphore = new Semaphore(throttleLimit);
    }

    // TODO. Task线程执行完毕之后释放Semaphore，唤醒Put线程继续添加Task
    public void releaseExecution() throws InterruptedException {
        this.semaphore.release();
    }

    // Put线程完成新的添加后，Notify通知Take线程取任务来执行
    public void put(CustomTaskRunnable taskRunnable) throws InterruptedException {
        this.semaphore.acquire();
        System.out.println("Get Put Semaphore!");

        synchronized (lock) {
            this.runnableQueue.add(taskRunnable);

            lock.notifyAll();
        }
    }

    // Take的线程会由于队列为空而CAS阻塞，直到接到Notify通知
    public CustomTaskRunnable take() throws InterruptedException {
        synchronized (lock) {
            while (this.runnableQueue.size() == 0) {
                lock.wait();
            }
            return this.runnableQueue.take();
        }
    }

    public void printSemaphorePermits() {
        int permits = this.semaphore.availablePermits();
        System.out.println("Permits: " + permits);
    }

    private static class TaskComparator implements Comparator<CustomTaskRunnable> {

        @Override
        public int compare(CustomTaskRunnable o1, CustomTaskRunnable o2) {
            return 0;
        }
    }
}

package JavaThreadsConcurrency.LiveLocks;

/**
 * Live lock: 类似于Deadlock (其中的线程是被blocked)
 * 1. 一个线程持续(Active)拥有一个锁, 并等待其他线程去完成它们的task
 * 2. 多个线程都同时处在looping之中, 在等待其他线程释放锁
 * ---------------------------------------------------
 * Live lock: 解决方案
 */
public class BaseLiveLocks {

    /**
     * worker1和worker2在交替的拥有sharedResource, 两个线程在不停的执行相同的交换操作
     * 最终导致两个线程都在执行looping, 但是都无法执行正在的操作，从未无法改变自身的active状态 !!
     */
    private void testLiveLocks() {
        final Worker worker1 = new Worker("worker 1", true);
        final Worker worker2 = new Worker("worker 2", true);
        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(() -> {
            try {
                worker1.work(sharedResource, worker2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                worker2.work(sharedResource, worker1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

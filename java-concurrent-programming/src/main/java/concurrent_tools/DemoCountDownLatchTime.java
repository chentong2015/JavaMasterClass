package concurrent_tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

// 多线程的并发执行定时(统计全部执行的时间)
public class DemoCountDownLatchTime {

    // 只有在全部的并发数量准备好之后，才开始记录
    // 只有当全部的并发数量结束完成后，才结束记录
    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown(); // Tell timer we're ready
                try {
                    start.await(); // Wait till peers are ready
                    action.run();  // 测试这里的运行时间最好超过1s
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();  // Tell timer we're done
                }
            });
        }

        ready.await();     // Wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown(); // And they're off!
        done.await();      // Wait for all workers to finish
        return System.nanoTime() - startNanos;
    }
}

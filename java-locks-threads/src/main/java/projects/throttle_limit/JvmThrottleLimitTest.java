package projects.throttle_limit;

// TODO. 模拟最多只有throttleLimit个线程在并发执行
// - 设置能并发运行的线程数限制THROTTLE_LIMIT
// - 使用有限的并发线程处理大量的Task任务，同一时刻运行的Task不超过LIMIT
public class JvmThrottleLimitTest {

    public static final int THROTTLE_LIMIT = 5;

    public static void main(String[] args) throws InterruptedException {
        CustomThrottleLimitImpl throttleLimitImpl = new CustomThrottleLimitImpl(THROTTLE_LIMIT);

        // 多线程的并发数量限制在THROTTLE_LIMIT数量
        for (int n = 0; n < THROTTLE_LIMIT; n++) {
            new Thread(() -> {
                try {
                    throttleLimitImpl.take().run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        // 提供多个Task任务交给并发线程执行
        for (int n = 0; n < 15; n++) {
            new Thread(() -> {
                try {
                    throttleLimitImpl.put(new CustomTaskRunnable(throttleLimitImpl));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}

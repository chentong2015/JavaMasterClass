package project_designs.throttle_limit;

// TODO. 模拟最多只有throttleLimit个线程在并发执行
public class JvmThrottleLimitTest {

    public static final int DEFAULT_THROTTLE_LIMIT = 3;

    public static void main(String[] args) throws InterruptedException {
        CustomThrottleLimitImpl throttleLimitImpl =
                new CustomThrottleLimitImpl(DEFAULT_THROTTLE_LIMIT);

        // 并发添加10个Task任务，测试按照设置的Limit批次运行
        for (int n=0; n<10; n++) {
            new Thread(() -> {
                try {
                    throttleLimitImpl.put(new CustomTaskRunnable(throttleLimitImpl));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        // 使用多个线程并发的取Task执行，每次最多只能取LIMIT数量
        for (int n=0; n<20; n++) {
             new Thread(() -> {
                 try {
                     throttleLimitImpl.take().run();
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }).start();
        }
    }
}

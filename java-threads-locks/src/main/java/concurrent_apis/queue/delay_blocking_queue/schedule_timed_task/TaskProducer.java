package concurrent_apis.queue.delay_blocking_queue.schedule_timed_task;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

public class TaskProducer implements Runnable {

    private final DelayQueue<DelayedTask> queue;
    private final Random random = new Random();

    public TaskProducer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // 生产者直接在延迟队列中添加消费者使用的task
        int delay = random.nextInt(10000);
        String uuid = UUID.randomUUID().toString();
        queue.put(new DelayedTask("task name", uuid, delay));
    }
}

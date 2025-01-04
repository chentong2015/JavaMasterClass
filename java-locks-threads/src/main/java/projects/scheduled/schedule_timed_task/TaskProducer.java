package projects.scheduled.schedule_timed_task;

import java.util.Random;
import java.util.concurrent.DelayQueue;

public class TaskProducer implements Runnable {

    private final DelayQueue<DelayedTask> queue;
    private final Random random;

    public TaskProducer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
        this.random = new Random();
    }

    // 生产者直接在延迟队列中添加消费者要执行的延迟任务Task
    @Override
    public void run() {
        queue.put(new DelayedTask("task name 1", random.nextInt(10000)));
        queue.put(new DelayedTask("task name 2", random.nextInt(10000)));
        queue.put(new DelayedTask("task name 3", random.nextInt(10000)));
        queue.put(new DelayedTask("task name 4", random.nextInt(10000)));
    }
}

package concurrent_api.queue.delay_blocking_queue;

import concurrent_api.queue.delay_blocking_queue.schedule_timed_task.DelayedTask;
import concurrent_api.queue.delay_blocking_queue.schedule_timed_task.TaskConsumer;
import concurrent_api.queue.delay_blocking_queue.schedule_timed_task.TaskProducer;

import java.util.concurrent.DelayQueue;

public class TimedTaskScheduler {

    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}

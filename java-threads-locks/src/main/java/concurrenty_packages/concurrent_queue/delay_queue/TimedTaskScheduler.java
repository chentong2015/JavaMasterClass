package concurrenty_packages.concurrent_queue.delay_queue;

import concurrenty_packages.concurrent_queue.delay_queue.schedule_timed_task.DelayedTask;
import concurrenty_packages.concurrent_queue.delay_queue.schedule_timed_task.TaskConsumer;
import concurrenty_packages.concurrent_queue.delay_queue.schedule_timed_task.TaskProducer;

import java.util.concurrent.DelayQueue;

public class TimedTaskScheduler {

    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}

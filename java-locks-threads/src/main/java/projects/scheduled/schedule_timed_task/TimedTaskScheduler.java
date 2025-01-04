package projects.scheduled.schedule_timed_task;

import java.util.concurrent.DelayQueue;

public class TimedTaskScheduler {

    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}

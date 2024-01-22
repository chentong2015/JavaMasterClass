package concurrenty_packages.concurrent_queue.delay_queue.schedule_timed_task;

import java.util.concurrent.DelayQueue;

public class TaskConsumer implements Runnable {

    private final DelayQueue<DelayedTask> queue;

    public TaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    // 消费者不需要关心时间差，直接在while(true)里不断take()
    // 当队列中没有任务时，TaskConsumer会无限等待，直到被唤醒，因此它不会消耗CPU
    @Override
    public void run() {
        while (true) {
            try {
                DelayedTask task = queue.take();
                System.out.println("Get task: " + task);
                if (task.getName().equals("end task")) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

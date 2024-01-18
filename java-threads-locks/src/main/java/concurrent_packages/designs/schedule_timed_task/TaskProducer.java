package concurrent_packages.designs.schedule_timed_task;

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
        while (true) {
            try {
                int delay = random.nextInt(10000);
                String uuid = UUID.randomUUID().toString();
                DelayedTask task = new DelayedTask("task name", uuid, delay);
                queue.put(task);
                Thread.sleep(3000);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

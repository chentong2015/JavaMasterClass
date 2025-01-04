package projects.scheduled.schedule_timed_task;

import java.util.concurrent.DelayQueue;

public class TaskConsumer implements Runnable {

    private final DelayQueue<DelayedTask> queue;

    public TaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    // 消费者不需要关心时间差，直接在while循环里不断获取“延迟结束”的任务
    // 当延迟队列中没有任务时，TaskConsumer会无限等待(阻塞)直到被唤醒，不会消耗CPU
    //
    // Get task: DelayedTask{name='task name 3', startTime=1735989602389}
    // Get task: DelayedTask{name='task name 1', startTime=1735989603447}
    // Get task: DelayedTask{name='task name 4', startTime=1735989607305}
    // Get task: DelayedTask{name='task name 2', startTime=1735989608651}
    @Override
    public void run() {
        while (true) {
            try {
                DelayedTask task = queue.take();
                System.out.println("Get task: " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

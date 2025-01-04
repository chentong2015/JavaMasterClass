package concurrent_api.queue.delay_blocking_queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// TODO. DelayQueue延迟队列: 特化版的PriorityBlockingQueue
// 线程安全的优先阻塞队列，定时任务调度器的实现
// 把"计算时间差"并让消费者"等待该时间差"的功能集成进了队列
public class JavaDelayQueue {

    public void testDelayQueue() throws InterruptedException {
        DelayQueue<MyDelayTask> queue = new DelayQueue<>();
        queue.add(new MyDelayTask());
        queue.put(new MyDelayTask());
        queue.offer(new MyDelayTask());

        MyDelayTask task = queue.take();
    }

    class MyDelayTask implements Delayed {
        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}

package projects.scheduled.schedule_timed_task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 表示在特定时刻(延迟的一个时间点)执行的任务Task
public class DelayedTask implements Delayed {

    private String name;
    private long startTime; // milliseconds

    public DelayedTask(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask) o;
        return (int) (this.startTime - task.startTime);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}

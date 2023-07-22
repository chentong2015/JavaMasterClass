package java_concurrent_packages.concurrent_queue.blocking_queue.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayTask implements Delayed {
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}

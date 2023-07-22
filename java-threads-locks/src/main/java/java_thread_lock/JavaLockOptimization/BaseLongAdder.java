package java_thread_lock.JavaLockOptimization;

import java.util.concurrent.atomic.LongAdder;

// TODO: 锁优化：分段CAS优化机制
//       JDK1.8 ConcurrentHashMap中count计数源码的实现 fullAddCount();
// 内部使用 transient volatile long base;
// 使用数组 cell1=0  cell2=0  cell3=0 多线程对数组中不同的段位进行自增操作，避免线程空转
// 通过数组来实现，分段CAS做增加，根据线程的多少会自动的缩容数组
// 最后使用sum()需要统计所有cell中value的总和
public class BaseLongAdder {

    private LongAdder longAdder = new LongAdder(); // > Java 8

    public long getLongAdder() {
        return longAdder.longValue();
    }

    public void increase() {
        longAdder.increment();
    }
}

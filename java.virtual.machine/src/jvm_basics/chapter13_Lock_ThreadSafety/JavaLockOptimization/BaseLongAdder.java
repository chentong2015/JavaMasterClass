package jvm_basics.chapter13_Lock_ThreadSafety.JavaLockOptimization;

import java.util.concurrent.atomic.LongAdder;

// TODO: 锁优化的机制：分段CAS优化机制
// 内部使用 transient volatile long base;
// 使用数组 cell1=0  cell2=0  cell3=0 多线程对数组中不同的段位进行自增操作，避免线程空转
// 通过数组来实现，分段CAS做增加，根据线程的多少会自动的缩容数组
// 最后使用sum()来求加起来的和
public class BaseLongAdder {

    private LongAdder longAdder = new LongAdder(); // > Java 8

    public long getLongAdder() {
        return longAdder.longValue();
    }

    public void increase() {
        longAdder.increment();
    }
}

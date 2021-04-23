package JavaThreadsConcurrency.TreatmentAtomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic Action: 如果statement的操作是atomic原子操作，则在操作过程中，线程是不能中断的
 * 1. Atomic Action
 * .  1.1 读写引用变量 object obj1 = obj2;
 * .  1.2 读写primitive type变量 myInt = 10;
 * .  1.3 读写所有声明"volatile"的变量
 * 2. Not Atomic Action
 * .  2.1 读写long, double类型的值, JVM需要两步操作去读写: one to each 32-bit half
 * Should declare shared 64-bit values as volatile or synchronize their programs to avoid possible complications !
 */
public class AtomicAction {

    private int intCounter;

    /**
     * "Increment" and "decrement" operations are not atomic:
     * 一个线程一个CPU Cache缓存, 一个线程可能在3个steps中的任何一步暂停, 然后调度给其他的线程 !!
     * 1. read the value of counter form memory
     * 2. Add 1 to the value
     * 3. Write the new value back to counter to its CPU cache
     */
    private void testAtomicAction() {
        intCounter++;
        System.out.println("This is not an atomic action");
    }

    /**
     * Volatile variable:
     * JVM writes the value back to main memory immediately after a thread updates the value in its CPU cache
     * Because the thread CPU caches may get out of sync with the value in main memory
     * 1. 确保线程在CPU cache中更新的数据能够立即回写到主内存，确保(对别线程)数据一致性, 确保变量从Volatile variable获取最近值 !!
     * 2. 确保在reading and writing double and long类型数据时，只用一步操作 !!
     */
    public volatile long counter;

    /**
     * 使用Volatile并不意味着不需要再使用synchronized同步化
     * 1. The value of counter is 1 in main memory and in Thread1 and Thread2 CPU caches
     * 2. Thread1 reads the value counter and gets 1
     * 3. Thread2 reads the value counter and gets 1
     * 4. Thread1 adds the value and gets 2; it writes 2 to its cache, JVM immediately writes 2 to main memory
     * 5. Thread2 adds the value and gets 2; it writes 2 to its cache, JVM immediately writes 2 to main memory
     * 6. The value counter should be 3 !!
     */
    public synchronized void testVolatileValue() {
        counter++;
    }

    /**
     * java.util.concurrent.atomic package "原子操作类型" ===> 不再需要synchronized同步化, 更新数据的操作是线程安全
     * 1. Ensure the reading and writing variables is atomic 使得增加和减少都是线程安全的
     * 2. Support lock-free thread-safe programming on single variables, do not worry about thread interference
     */
    private void testAtomicPackage() {
        AtomicInteger counter = new AtomicInteger(10);
        counter.incrementAndGet(); // +1
        counter.decrementAndGet(); // -1
        int value = counter.get();
        // 可以在确保线程具有指定的初始值的情况下，再执行更新的操作
        // 1. if equal the expected value, set and return true
        // 2. if not equal the expected value, set doesn't take place and return false
        counter.compareAndSet(10, 15);
    }
}

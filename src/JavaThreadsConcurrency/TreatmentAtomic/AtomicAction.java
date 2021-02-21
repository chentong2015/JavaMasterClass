package JavaThreadsConcurrency.TreatmentAtomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic Action: 原子操作
 * 如果statement的操作是atomic原子操作，则在操作的过程中，线程是不能中断的 ! TODO 17.7. Non-Atomic Treatment ?????
 * 1. Atomic Action :
 * ___ 1.1 读写引用变量 object obj1 = obj2;
 * ___ 1.2 读写primitive type变量 myInt = 10;
 * ___ 1.3 读写所有声明"volatile"的变量
 * 2. Not Atomic Action :
 * ___ 2.1 读写long, double类型的值, JVM需要两步操作去完成 (使用volatile或者AtomicLong & AtomicDouble)
 */
public class AtomicAction {

    /**
     * 非原子操作的基本3步骤 !!
     */
    private void testAtomicAction() {
        // println() 输出操作不是原子操作，可能出现中断
        System.out.println("This is not an atomic action");
    }

    /**
     * Volatile variable: JVM writes the value back to main memory immediately after a thread updates the value in its CPU cache
     * 1. 确保线程在CPU cache中更新的数据能够自动的回写到主内存中，确保(对于别的线程)数据的一致性
     * 2. 确保每次变量是从Volatile variable变量中读取，获取的是最近的值
     * ------------------------------------------------
     * 1. Volatile variable通常是作用在double或者是long类型
     * 2. 使用Volatile并不意味着不需要再使用synchronized同步化，这取决于多线程的具体操作 ===> 两种机制所解决的问题不同 !!
     */
    public volatile int counter;

    /**
     * java.util.concurrent.atomic package
     * 1. Ensure the reading and writing variables is atomic
     * 2. Support lock-free thread-safe programming on single variables, do not worry about thread interference
     */
    private void testAtomicPackage() {
        AtomicInteger counter = new AtomicInteger(10);
        counter.incrementAndGet(); // +1
        counter.decrementAndGet(); // -1
        int value = counter.get();
        // 可以在确保线程具有指定的初始值的情况下，再执行更新的操作
        counter.compareAndSet(10, 15);
    }
}

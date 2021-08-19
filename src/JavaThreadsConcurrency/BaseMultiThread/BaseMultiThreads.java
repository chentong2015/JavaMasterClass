package JavaThreadsConcurrency.BaseMultiThread;

/**
 * 1. 大部分的JVM instance实例都启动一个process进程
 * 2. Java Application在运行的时候，拥有独自的堆内存空间(Heap Memory), 且相互独立
 * 3. Thread线程是process进程的一个执行单元，至少有一个默认的主线程，可创建额外的线程
 * 4. Thread线程的创建会共享Process的堆内存空间和Files, 同时拥有自己独立的Thread Stack(线程栈)空间
 * 5. 多线程用于执行耗时的操作或者Task, 避免阻塞main thread, 可以放到后台执行, "同时"执行多个操作
 * 6. These threads independently execute code that operates on values and objects residing in a shared main memory.
 * 7. Each thread has a CPU cache, which can contain copies fo values that are in main memory 在cache缓存中读取数据更快
 */
public class BaseMultiThreads {

    /**
     * 造成多线程问题的来源:
     * 1. 不同的线程共享数据(对象), 并发操作问题
     * 2. 多线程在操作共同数据时, 读取判断和操作上面, 由于线程调度造成数据一致性问题或者损坏
     * 3. 线程在执行非原子操作, 在执行的过程中中断造成问题
     * 4. 在多核CPU机器上, 不同线程可能在不同的CPU上运行，其中各自CPU Cache数据可能和主内存中的数据出现一致性问题
     */
    private void testMultiCPU() {
        // Thread1 is running on CPU1
        // Thread2 is running on CPU2
        // Thread1 and Thread2 reads and writes the same counter (init 0 in main memory)
        // 以下执行错误: 在不同的CPU cache出现数据不一致性
        // 1. Thread1 reads the value 0 from main memory
        // 2. Thread1 adds 1 to the value
        // 3. Thread1 writes the value of 1 to its CPU cache on CPU1
        // 4. Thread2 reads the value of counter 0 from main memory, not the latest value 1
    }

    /**
     * TODO: Java application thread number limit 最大负载线程树的限制 ==> StackOverflowError
     * 1. 主要受限于硬件配置，OS为当个进程分配的最大内存空间
     * 2. 虚拟机栈和本地方法栈所分配到的内存空间是有限的
     * 3. 多线程在创建时，为每个线程分配的栈内存越大(~1M per thread)，可创建的线程数量就越少
     * 4. HotSpot虚拟机默认参数，栈深度在大多数情况下可以支撑(1000-2000)范围的数量 !!
     */
}
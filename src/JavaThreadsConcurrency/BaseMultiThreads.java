package JavaThreadsConcurrency;

/**
 * 1. 大部分的JVM instance实例都启动一个process进程
 * 2. Java Application在运行的时候，拥有独自的Heap(堆)内存空间, 且相互独立
 * 3. Thread线程是process进程的一个执行单元，至少有一个默认的主线程，可创建额外的线程
 * 4. Thread线程的创建会共享Process的Heap内存空间和Files, 同时拥有自己独立的Thread Stack(线程栈)空间
 * 5. 多线程用于执行耗时的操作或者Task, 避免阻塞main thread, 可以放到后台执行, "同时"执行多个操作
 * 6. These threads independently execute code that operates on values and objects residing in a shared main memory.
 * 7. Each thread has a CPU cache, which can contain copies fo values that are in main memory 在cache中读取数据更快
 */

public class BaseMultiThreads {

    /**
     * 造成多线程问题的来源:
     * 1. 不同的线程共享数据(对象), 并发操作是带来问题
     * 2. 多线程在操作共同数据时, 在读取, 判断和操作上面, 由于线程调度造成数据一致性问题或者损坏
     * 3. 线程在执行非原子操作，在执行的过程中中断造成问题
     * 4. 在多核CPU机器上，不同线程可能在不同的CPU上运行，其中cache数据可能和主内存中的数据出现一致性问题
     */
    private void testMultiCPU() {

    }

    // TODO: optimistic locking and pessimistic locking 乐观锁，悲观锁
    // https://docs.oracle.com/javaee/7/tutorial/persistence-locking001.htm
    // https://www.objectdb.com/java/jpa/persistence/lock#Optimistic_Locking

}

package JavaThreadsConcurrency.BaseSynchronisation.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 提供线程局部变量
 * DemoThreadLocal 类生成每个线程本地的"唯一标识符", 线程的ID是在第一次调用DemoThreadLocal.get()时分配的，并且在以后的调用中保持不变
 */
public class DemoThreadLocal {

    // 常量, 且对该int值的增减都是线程安全的，中间不会中断，切换到别的线程
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    // 避免内存泄漏
    public static void remove() {
        threadId.remove();
    }
}

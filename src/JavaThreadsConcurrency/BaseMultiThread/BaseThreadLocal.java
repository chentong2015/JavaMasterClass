package JavaThreadsConcurrency.BaseMultiThread;

import java.util.concurrent.atomic.AtomicInteger;

// ThreadLocal类型: 变量只被某个线程所独享，实现一个线程的本地存储
// 每一个线程Thread对象都有一个ThreadLocalMap对象，存储<ThreadLocalHashCode, 本地线程变量>的键值对
// ThreadLocal对象就是当前线程的ThreadLocalMap的入口，它包含唯一的HashCode值，用于在K-V中找到本地的线程变量
public class BaseThreadLocal {

    // 常量且对该int值的增减都是线程安全的，中间不会中断，不会切换到别的线程
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    public static int get() {
        // 第一次调用BaseThreadLocal.get()时分配线程ID"唯一标识符"，并且在以后的调用中保持不变
        // Returns the current thread's unique ID, assigning it if necessary
        return threadId.get();
    }

    public static void remove() {
        // 避免内存泄漏
        threadId.remove();
    }
}

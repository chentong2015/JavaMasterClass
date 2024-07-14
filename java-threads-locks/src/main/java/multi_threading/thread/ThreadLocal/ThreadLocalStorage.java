package multi_threading.thread.ThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

// 实战应用场景:
// 连接管理: 一个线程持有一个连接，在线程的不同方法之间传递并使用，独立于其他线程
public class ThreadLocalStorage {

    private final ThreadLocal<Integer> threadLocalKey1 = new ThreadLocal<>();
    private final ThreadLocal<String> threadLocalKey2 = new ThreadLocal<>();

    // 为Thread对象中的ThreadLocalMap对象添加两个键值<Key, Value>
    // key: ThreadLocal对象, ThreadLocalHashCode
    // value: 本地线程变量
    private void initThreadLocal() {
        threadLocalKey1.set(10);
        threadLocalKey2.set("name");
    }

    // 同一个线程可以在任意时刻，任意方法中获取线程本地缓存的值
    // 该缓存的数据不在线程之间共享
    private void testMethod1() {
        int numberCache = threadLocalKey1.get();
        System.out.println(numberCache);
    }

    private void testMethod2() {
        String name = threadLocalKey2.get();
        System.out.println(name);
    }

    // 如果线程不被回收，则需要手动清除ThreadLocalMap中的键值对象，避免内存泄漏
    private void removeThreadLocal() {
        threadLocalKey1.remove();
        threadLocalKey2.remove();
    }

    // TODO: 以下测试为每一个线程生成唯一线程ID
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}

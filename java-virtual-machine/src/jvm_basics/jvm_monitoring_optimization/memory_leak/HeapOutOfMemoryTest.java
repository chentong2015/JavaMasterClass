package jvm_basics.jvm_monitoring_optimization.memory_leak;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// TODO. Heap OutOfMemory解决方法
// - Check source code where potential memory issues can happen.
// - Use Java Monitoring Tool, dump heap memory
// - Set Maximum heap size by JVM parameter -Xmx8g
public class HeapOutOfMemoryTest {

    // 1. Java Runtime Memory 运行时内存会由于JVM设置过小而造成堆空间的OOM
    public static void main(String[] args) {
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory());
        // Allocate 256m 在堆中分配指定大小的空间
        // OOM 测试堆空间分配的设置是否过小，是否有溢出的情况
        byte[] array = new byte[256 * 1024 * 1024];
    }

    // 2. 长期产生无法GC的对象，数据结构中还存留着对象的过期引用(Obsolete Reference)
    // 无法被GC回收的对象有多种可能，例如线程所拿到的对象的锁没有被释放
    private static Set<String> container = new HashSet<>();

    public static String getUniqueId() {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            if (!container.contains(uuid)) {
                container.add(uuid);
                return uuid;
            }
        }
    }

    // 3. 连接的泄漏，必须确保建立的连接关闭，造成最后内存溢出
    public String process(String bizKey) {
        try {
            // Jedis jedis = JedisUtils.getJedis();
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            // close jedis connection
        }
    }
}

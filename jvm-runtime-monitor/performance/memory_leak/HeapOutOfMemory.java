package garbage_collection.memory_leak;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HeapOutOfMemory {

    // 1. Java Runtime Memory
    // 运行时内存会由于JVM设置过小而造成堆空间的OOM
    // 如果Heap Size堆内存无法满足程序的运行需求，则需要进行扩充
    public static void printRuntimeMemory() {
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory());
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

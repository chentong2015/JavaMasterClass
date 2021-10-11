package jvm_basics.chapter02_Momery_Section.MemoryProblems;

import jvm_basics.base.DemoClass;

import java.util.*;

// 关于内存溢出OOM的测试
// Java因为有垃圾回收机制，一般不会存在内存泄露问题
public class MemoryLeakTest {

    // 案例01:
    // 做判断的时候，由于比较的是对象，默认使用.equals()方法进行比较
    // 如果没有重新类型的.equals() & hashCode()方法，则无法判断key的相同，可能造成最后内存溢出
    public static Map<DemoClass, String> map = new HashMap<>();

    public void testMapMemoryLeak() {
        map.put(new DemoClass(1, "test"), "item");
        map.put(new DemoClass(1, "test"), "item");
        if (map.containsKey(new DemoClass(1, "test"))) {
            System.out.println("Find the same key");
        }
        int size = map.size(); // 2
    }

    // 案例02：连接的泄漏，必须确保建立的连接关闭，造成最后内存溢出
    public String process(String bizKey) {
        try {
            // Jedis jedis = JedisUtils.getJedis();
            // String value = jedis.get(bizKey);
            // String result = doSomethingWork(value);
            // jedis.set(bizKey, result);
            // jedis.close();
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
            // close jedis
        }
    }

    // 案例03：长期产生没有办法回收的数据，使用的数据结构中还存留着对象的过期引用(obsolete reference)
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

    // 案例04：线程所拿到的对象的锁没有被释放，导致对象没有被回收
    // 案例05：Hibernate的Session(一级缓存)中的对象属于持久态, 需要及时关闭或flush一级缓存
}

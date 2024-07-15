package garbage_collection;

import java.util.HashMap;
import java.util.Map;

// GC Overhead Limit
// java.lang.OutOfMemoryError: GC overhead limit exceeded
// Java Virtual Machine garbage collector not being able to reclaim memory.
public class GCOverheadLimit {

    // TODO. 低版本的Java存在GC垃圾回收的开销限制
    // 当达到GC的开销极限的条件时(如下两个条件)，JVM认为无法继续执行GC，于是程序会抛出异常
    // 1. JVM spends more than 98% of its time in the garbage collection
    // 2. 5 consecutive garbage collections can reclaim less than 2% of the heap
    //
    // Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
    //    at java.base/java.lang.Long.valueOf(Long.java:1211)
    //    at memory.GCOverheadLimit.main(GCOverhead.java:23)
    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<>();
        for (long i = 1L; i < Long.MAX_VALUE; i++) {
            map.put(i, i);
        }
        System.out.println(map.get(1l));
    }
}

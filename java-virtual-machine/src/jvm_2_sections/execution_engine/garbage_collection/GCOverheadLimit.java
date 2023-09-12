package jvm_2_sections.execution_engine.garbage_collection;

import java.util.HashMap;
import java.util.Map;

// GC Overhead Limit
// java.lang.OutOfMemoryError: GC overhead limit exceeded
// Java Virtual Machine garbage collector not being able to reclaim memory.
public class GCOverheadLimit {

    // 使用低版本的Java存在GC垃圾回收的开销限制:
    // 当大致满足如下条件时，JVM认为无法继续执行GC，GC的开销达到极限
    // - JVM spends more than 98% of its time in the garbage collection
    // - 5 consecutive garbage collections can reclaim less than 2% of the heap
    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<>();
        for (long i = 1L; i < Long.MAX_VALUE; i++) {
            map.put(i, i);
        }
        System.out.println(map.get(1l));
    }
}

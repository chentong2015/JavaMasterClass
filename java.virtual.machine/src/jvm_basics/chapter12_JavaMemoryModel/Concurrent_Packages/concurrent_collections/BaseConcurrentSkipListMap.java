package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_collections;

import java.util.concurrent.ConcurrentSkipListMap;

public class BaseConcurrentSkipListMap {

    // ConcurrentSkipListMap实现原理 ?
    public void testConcurrentSkipListMap() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "item");
        map.remove(1);
        String value = map.get(1);
    }
}

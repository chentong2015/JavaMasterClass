package java_concurrent_packages.concurrent_collections;

import java.util.concurrent.ConcurrentSkipListMap;

// 基于跳表实现的并发数据结构ConcurrentSkipListMap:
// TODO: 内部实现 https://cloud.tencent.com/developer/article/1013646
public class BaseConcurrentSkipListMap {

    public void testConcurrentSkipListMap() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "item");
        map.remove(1);
        String value = map.get(1);
    }
}

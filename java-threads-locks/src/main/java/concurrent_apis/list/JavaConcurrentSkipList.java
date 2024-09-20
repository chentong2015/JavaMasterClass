package concurrent_apis.list;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class JavaConcurrentSkipList {

    public void testConcurrentSkipListSet() {
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
        skipListSet.add("item");
        boolean isContains = skipListSet.contains("item");
    }

    // 基于跳表实现的并发数据结构ConcurrentSkipListMap:
    // 内部实现 https://cloud.tencent.com/developer/article/1013646
    public void testConcurrentSkipListMap() {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(1, "item");
        map.remove(1);
        String value = map.get(1);
    }
}

package concurrent_apis.collections.skiplist;

import java.util.concurrent.ConcurrentSkipListSet;

public class BaseConcurrentSkipListSet {

    public void testConcurrentSkipListSet() {
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
        skipListSet.add("item");
        boolean isContains = skipListSet.contains("item");
    }
}

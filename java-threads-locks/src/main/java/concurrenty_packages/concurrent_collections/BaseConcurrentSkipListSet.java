package concurrenty_packages.concurrent_collections;

import java.util.concurrent.ConcurrentSkipListSet;

public class BaseConcurrentSkipListSet {

    public void testConcurrentSkipListSet() {
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
        skipListSet.add("item");
        boolean isContains = skipListSet.contains("item");
    }
}

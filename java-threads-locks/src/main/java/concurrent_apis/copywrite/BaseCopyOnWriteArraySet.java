package concurrent_apis.copywrite;

import java.util.concurrent.CopyOnWriteArraySet;

public class BaseCopyOnWriteArraySet {

    // CopyOnWriteArraySet实现原理
    // 1. 直接使用CopyOnWriteArrayList的方法和功能
    //    private final CopyOnWriteArrayList<E> al;
    public void testCopyOnWriteArraySet() {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("item");
        set.remove("item");
    }
}

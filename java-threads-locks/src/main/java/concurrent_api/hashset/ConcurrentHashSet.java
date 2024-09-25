package concurrent_api.hashset;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 更换HashSet的底层实现原理，并不能保证线程安全
// 通常使用Collections.synchronizedSet()方法“包装”该集
public class ConcurrentHashSet {

    private static Set<String> set = new HashSet<>();
    private static Set<String> setSafe = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(100);
        for (int index = 1; index < 10000; index++) {
            int finalIndex = index % 100;
            executors.execute(() -> {
                // set.add("item" + finalIndex); 195 多线程情况造成重复item
                setSafe.add("item" + finalIndex); // 100 保证重复的item不会被添加
            });
        }
        executors.shutdown();

        Thread.sleep(5000);
        System.out.println(setSafe.size());
        System.out.println("Done");
    }
}

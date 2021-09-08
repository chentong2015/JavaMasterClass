package JavaDataStructure;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

// Array  []
// List   ArrayList 列表 / LinkedList 链表,双向链表
// Stack  (Last In First Out)
// Queue  (First In First Out) / Deque双端队列
// Map    (键值对应关系):
//         HashMap<K, V> 不保证迭代的顺序，顺序不固定
//         LinkedHashMap<K, V> 迭代顺序和插入映射中顺序一致
//         SortedMap  严格排序
//         TreeMap<K, V> 通过tree实现key的严格排序
// Sets   (无序且不重复的元素集合)
//         HashSet<V> 不保证迭代的顺序，顺序不固定
//         LinkedSet<V> 迭代顺序和插入映射中顺序一致
//         SortedSet  严格排序
//         TreeSet<E> 通过Tree实现对key的自然排序
// Tree   (多种树数据结构)
// Graphs (构建指定路径的通路, 图形网络)
// Heap   (完全二叉树, 可非平衡, 有大小堆)
// Vector (类型安全的集合, C++老版本类型)
public class DataStructure {

    // 将集合"数据结构"设置成只读                     =====> C#区别：ReadOnlyCollections<T>, ReadOnlyDictionary<TKey, TValue>
    private static void testReadOnlyCollections() {
        // Collections.unmodifiableList()
        // Collections.unmodifiableMap()
        // Collections.unmodifiableSortedMap()
        // Collections.unmodifiableSet()
        // Collections.unmodifiableSortedSet()
        Map<String, String> realMap = new HashMap<>();
        realMap.put("A", "Value A");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(realMap); // 提供一个可读不可改的视图View
        // unmodifiableMap.put("C", "D"); throw an UnsupportedOperationException
        realMap.put("B", "Value B");
        unmodifiableMap.get("B"); // 源Map的修改对View Map也可见
    }

    // 使用Thread-safe线程安全的集合"数据结构"         =====> C#区别：ConcurrentDictionary<K, V>, ConcurrentQueue<T>, ConcurrentStack<T>
    // 1. 使用Collections.synchronized方法来创建
    // 2. 使用线程安全的类型，执行并发编程
    private static void testThreadSafeCollections() throws InterruptedException {
        List<String> array = Collections.synchronizedList(new ArrayList<>());

        // Vector类型安全: public synchronized boolean add(E e) {}
        // BlockingQueue 线程安全队列：应用在多线程的调度 put(); peek(); take(); remove();
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(6);
        queue.put("Item");
        String value = queue.peek();
        queue.remove();
    }

    // ConcurrentHashMap 如何实现线程安全 ?
    // TODO: HashMap和ConcurrentHashMap的区别？treemap和HashMap的区别
    // 1. 首先在解决Hash冲突的问题上，使用"Chaining"法，对于相同的添加元素计算哈希并求模，对应到不同的Bucket(单链表或者是双链表)
    // 2. 在对每个桶加锁的逻辑上，将所有的桶均匀的划分成各个部分segment，然后对segment加锁，降低锁的数量
    // 3. 在锁分离的基础上，大量的利用CAS指令
    // 4. 在存储时如果链过大(查找慢)，会自动转成"红黑树"(查找快)
    private void testConcurrentHashMap() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "secure information");
    }
}

package JavaDataStructure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Java基础数据结构类型
public class DataStructure {

    // Array  []
    // List   ArrayList 列表 / LinkedList 链表,双向链表
    // Stack  (Last In First Out)
    // Queue  (First In First Out) / Deque双端队列
    // Map    (键值对应关系):
    //         HashMap<K, V>      不保证迭代的顺序，顺序不固定
    //         LinkedHashMap<K, V> 迭代顺序和插入映射中顺序一致
    //         SortedMap<K, V>     严格排序
    //         TreeMap<K, V>       通过tree实现key的严格排序
    // Set    (无序且不重复的元素集合)
    //         HashSet<V>   不保证迭代的顺序，顺序不固定
    //         LinkedSet<V> 迭代顺序和插入映射中顺序一致
    //         SortedSet<V> 严格排序
    //         TreeSet<E>   通过Tree实现对key的自然排序
    // Tree   (多种树数据结构)
    // Graphs (构建指定路径的通路, 图形网络)
    // Heap   (完全二叉树, 可非平衡, 有大小堆)
    // Vector (类型安全的集合, C++老版本类型)

    // 将集合"数据结构"设置成只读(的视图View)
    private static void testReadOnlyCollections() {
        // Collections.unmodifiableList()
        // Collections.unmodifiableMap()
        // Collections.unmodifiableSortedMap()
        // Collections.unmodifiableSet()
        // Collections.unmodifiableSortedSet()
        Map<String, String> realMap = new HashMap<>();
        realMap.put("A", "Value A");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(realMap);
        // unmodifiableMap.put("C", "D"); throw an UnsupportedOperationException
        realMap.put("B", "Value B");
        unmodifiableMap.get("B"); // 源Map的修改对View Map也可见
    }
}

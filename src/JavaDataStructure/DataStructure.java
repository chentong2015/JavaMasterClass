package JavaDataStructure;

/**
 * Java Collections Framework 集合框架：包含接口和抽象出来的类型
 * ----------------------------------------
 * List
 * -> AbstractList<E> -> ArrayList<E>
 * -> AbstractSequentialList<E> -> LinkedList<E> ===> C#: LinkedList<T>, LinkedListNode<T> 链表节点
 * ----------------------------------------
 * Queue, Deque双端队列
 * ----------------------------------------
 * -> Set<E>
 * -> AbstractSet
 * -> HashSet<E>无序 -> LinkedHashSet<E>有序      ====> 实现有序的过程必须提供compareTo()方法  <- 自定义的类型: 实现有序需要消耗一定的性能 !!
 * ----------------------------------------
 * Dictionary<K,V>
 * -> abstract抽象类型，并非严格属于Collection      ====> C#区别: public class Dictionary<TKey,TValue> 是一个实例类型
 * -> key对value值的单一映射关系
 * ----------------------------------------     ====> Map的存在替代上述废弃的类型 Dictionary<key, value>
 * Map<K,V>
 * -> AbstractMap
 * -> HashMap<K, V>无序 -> LinkedHashMap<K, V>有序
 * ----------------------------------------
 * SortedMap, SortedSet排序的
 * -> AbstractMap<K,V>, NavigableSet<E>
 * -> TreeMap<K, V>有序, TreeSet<E>
 */

// 静态数组 Array []
// 动态数组 : List -> ArrayList 列表 / LinkedList 链表
// 栈 Stack (Last In First Out)        ====> 应用在树的非递归遍历...(暂存节点, 直到while穷尽), Stack Trace异常情况下的栈追踪
// 队列 Queue (first in first out)     ====> 应用在多线程的调度...
// 二叉树 Binary Tree (平衡)            ====> 在存储大量值，排序，搜索，增加，删除，等具有很高的遍历层级优势 !!!
// 图 Map (体现一种map对应关系)
// Sets (无序, 且不重复的元素集合)
// 堆 Heap (完全树, 可非平衡)            ====> 最大堆和最小堆：根据母节点和子节点的大小判断
public class DataStructure {

    // 1. 数据结构通常和算法结合在一起使用

    // 2. 数据结构和dao持久层/JDBC的链接

}

package hashmap;

// JDK 1.8: 数组+链表+红黑树
public class BaseHashmap8 {

    // TODO: 链表+红黑树共同实现，Node数组中可能同时存在两种数据结构
    // 1. Node -> Node ... 冲突的时候，需要挂链表  O(n)
    // 2. 当链表长度大于8时,自动转成红黑树,优化查询   O(log(n))
    // 3. 如果长度小于6，则转回成链表

    // final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    //     Node<K,V>[] tab;
    //     Node<K,V> p;
    //     int n, i;
    //     if ((tab = table) == null || (n = tab.length) == 0)
    //         resize()包括对数组的初始化和扩容
    //         n = (tab = resize()).length;
    //     if ((p = tab[i = (n - 1) & hash]) == null)
    //         如果算出来index位置为空，则直接创建node，并设置对应位置的值
    //         tab[i] = newNode(hash, key, value, null);
    //     else {
    //         Node<K,V> e;
    //         K k;
    //         if (p.hash == hash &&((k = p.key) == key || (key != null && key.equals(k))))
    //             e = p; 如果有相同的key，则直接覆盖
    //         else if (p instanceof TreeNode)
    //             如果结点是红黑树的结点，在红黑树中插入结点
    //             e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
    //         else {
    //             走链表的逻辑，遍历链表
    //             for (int binCount = 0; ; ++binCount) {
    //                 if ((e = p.next) == null) {
    //                     TODO: 这里使用的是"尾插法"
    //                     p.next = newNode(hash, key, value, null);
    //                     if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
    //                         TODO: 如果链表的长度超过8(第9个元素进来时)，则转成红黑树
    //                         treeifyBin(tab, hash);
    //                     break;
    //                 }
    //                 if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
    //                     break;
    //                 p = e; 移动到下一个结点
    //             }
    //         }
    //         if (e != null) { // existing mapping for key
    //             V oldValue = e.value;
    //             if (!onlyIfAbsent || oldValue == null)
    //                 e.value = value;
    //             afterNodeAccess(e);
    //             return oldValue;
    //         }
    //     }
    //     ++modCount;
    //     if (++size > threshold) TODO: JDK1.7 扩容时还需要判断(table[bucketIndex]!=null)位置非空
    //         resize(); 扩容
    //     afterNodeInsertion(evict);
    //     return null;
    // }

    // 从链表转成红黑树
    // final void treeifyBin(Node<K,V>[] tab, int hash) {
    //     int n, index; Node<K,V> e;
    //     if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) MIN_TREEIFY_CAPACITY=64
    //         resize(); 进行扩容
    //     else if ((e = tab[index = (n - 1) & hash]) != null) {
    //         TreeNode<K,V> hd = null, tl = null;
    //         TODO: 第一步：将单链表转换成双向链表，然后再转成红黑红树
    //         do {
    //             从链表的Node转换成红黑树的TreeNode(双向链表)
    //             TreeNode<K,V> p = replacementTreeNode(e, null);
    //             if (tl == null)
    //                 hd = p;
    //             else {
    //                 p.prev = tl;
    //                 tl.next = p;
    //             }
    //             tl = p;
    //         } while ((e = e.next) != null);
    //         if ((tab[index] = hd) != null)
    //             hd.treeify(tab); 真正的树化: 依次在红黑树中插入结点
    //             在红黑树中插入，需要先找到插入的位置，比较的因素：
    //             1. hashcode
    //             2. key compareto() 如何key实现接口，则不调用
    //             3. key getClass().getName()
    //             4. System.identifyHashCode(); 获取没有被重写的hashcode方法返回的值
    //     }
    // }

    //  final void treeify(Node<K,V>[] tab) {
    //     ...
    //     TreeNode<K,V> xp = p;
    //     判断在红黑树中的左移或者右移，直到走到null
    //     if ((p = (dir <= 0) ? p.left : p.right) == null) {
    //         x.parent = xp;
    //         if (dir <= 0)
    //            xp.left = x;
    //         else
    //            xp.right = x;
    //         再调用红黑树调整(平衡)的逻辑，改变root结点
    //         root = balanceInsertion(root, x);
    //         break;
    //     }
    //     ...
    //     moveRootToFront(tab, root);
    //     1. 将红黑树树移动到Node数组相应的位置 tab[index] = root;
    //     2. 同时保证红黑树的root结点是双向链表的头结点(会使用到双向链表的next, prev双向指针)
    //     3. 最后验证红黑树的规则
    // }

    // resize() {
    //    在转移链表数据的时候，先遍历链表将所有转移后index位置一致的结点连接在一起
    //    转移到的位置要么在原来位置，要么在+oldTable.length的位置
    // }
}
